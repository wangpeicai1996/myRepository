package com.pro.domain;

/**
 * 购物项
 * @author Administrator
 *
 */
public class CartItem {
	//商品对象
	private Product product;
	//购买数量
	private Double subtotal;
	//小计
	private Integer count;
	
	public CartItem() {
		
	}
	
	public CartItem(Product product, Integer count) {
		super();
		this.product = product;
		this.count = count;
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Double getSubtotal() {
		return product.getShop_price()*count;
	}
	/*public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}*/
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

}

