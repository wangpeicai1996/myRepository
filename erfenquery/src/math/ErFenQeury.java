package math;

/**
 * 二分法查找有序数组
 * @author Administrator
 *
 */
public class ErFenQeury {

	/**
	 * 生成指定大小的数组
	 * @param k
	 * @return
	 */
	public int[] createArray(int k) {
		int[] sortArray=new int[k];
		for(int i=1;i<=sortArray.length;i++) {
			sortArray[i-1]=i;
		}
		return sortArray;
	}
	
	/**
	 * 二分法查找指定数字（包含小数）
	 * @param guessNumber
	 * @return
	 */
	public double QeuryNumber(int length,double guessNumber) {
		int[] array=this.createArray(length);
		double head=0;
		double end=array.length;
		double mid=0;
		int i=0;
		while(head<=end) {
			mid=(head+end)/2;
			if(mid>guessNumber) {
				end=mid;
				System.out.println("end="+end);
			}else if(mid<guessNumber) {
				head=mid;
				System.out.println("head="+head);
			}else if(mid==guessNumber) {
				System.out.println("你要找的数字是："+mid+",找了"+i+"次");
			}
			i++;
			if(mid==guessNumber) {
				break;
			}
		}
		
		return mid;

	}
	
	/**
	 * 针对于找整数
	 * @param geuss
	 * @return
	 */
	public int getIntNumber(int length,int geuss) {
		int []  array=this.createArray(length);
		int head=0;//有序数列的第一个数字
		int end=array.length;//有序数列的结尾数字
		int mid=0;//中间数
		/**
		 * 如果猜的数字是7
		 * 【1】 head=0  end=10 mid=5
		 * 【2】 7>5 head=5+1=6 end=10 mid=8
		 * 【3】 7<8 head=6 end=10-1=9 mid=15/2=7
		 */
		int i=0;//计数器	
		while(head<end) {
			
			mid=(head+end)/2;
			int item=array[mid];
			System.out.println("第"+(i+1)+"次，"+"mid="+mid);
			if(geuss<mid) {
				end=mid-1;
				i++;
				System.out.println(geuss+"<"+mid+",head="+head+",end="+end);
			}
			if(geuss>mid) {
				head=mid+1;
				i++;
				System.out.println(geuss+">"+mid+",head="+head+",end="+end);
			}
			if(item==geuss) {
				System.out.println(geuss+">"+mid+",head="+head+",end="+end+",item="+item);
				System.out.println("你要找的数字是:"+array[mid]+",数组索引的位置是："+mid);
				return item;
			}
			
		}
		
		return mid;
	}
	
	
	
	
}
