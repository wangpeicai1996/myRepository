package math;


/**
 * 选择排序
 * @author Administrator
 *
 */
public class ChoiceSort {

	/**
	 * 生成指定大小无需的1-10的数组
	 * @param k
	 * @return
	 */
	public int[] createArray(int k) {
		int[] sortArray=new int[k];
		for(int i=1;i<=sortArray.length;i++) {
			sortArray[i-1]=(int) (Math.random()*10);
			System.out.print("sortArray["+(i-1)+"]="+sortArray[i-1]+" ");
			}
		
		return sortArray;
	}
	
	
	
	public int findMin(int number) {
		int [] array=this.createArray(number);
		int min=array[0];
		int min_index=0;
		for(int i=0;i<array.length;i++) {
			if(array[i]<min) {
				min=array[i];
				min_index=i;
			}
		}
		System.out.println("最小的是="+min+"索引为="+min_index);
		return min;
	}
	
	
	public int[] selectSort() {
		/**
		 * 选择排序基本思想： 在要排序的一组数中，寻出最小的一个数与第一个位置的数交换，然后在剩下的数当中找最小的与第二个位置(每一轮比较的为止是固定的)的数交换，
		 * 如此循环到倒数第二个数和最后一个数比较为止。
		 * 
		 * @param args
		 */
		
			int[] a = { 3, 26, 2, 14, 1996 };
			for (int i = 1; i < a.length; i++) {
				for (int j = i; j < a.length; j++) {
					if (a[i - 1] > a[j]) {
						int temp = a[i - 1];
						a[i - 1] = a[j];
						a[j] = temp;
					}
				}
			}
			System.out.println("排序后的数组:");
			for (int i = 0; i < a.length; i++) {
				System.out.print(a[i] + " ");
			}
			return a;
	}
	
	
}
