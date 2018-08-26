package com.pro.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.pro.constant.Constant;
import com.pro.dao.ProductDao;
import com.pro.domain.Category;
import com.pro.domain.Page;
import com.pro.domain.Product;
import com.pro.utils.DataSourceUtils;

public class ProductDaoImpl implements ProductDao {

	/**
	 * 查询热门商品
	 * 
	 * @throws Exception
	 */
	public List<Product> findHot() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where is_hot=? and pflag=? order by pdate desc limit 9";
		return qr.query(sql, new BeanListHandler<>(Product.class), Constant.PRODUCT_IS_HOT, Constant.PRODUCT_IS_UP);
	}

	/**
	 * 查询最新商品
	 */
	public List<Product> findNew() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pflag=? order by pdate desc limit 9";
		return qr.query(sql, new BeanListHandler<>(Product.class), Constant.PRODUCT_IS_UP);
	}

	/**
	 * 查询单个商品
	 */
	@Override
	public Product getById(String pid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql1="select c.cid,c.cname from category c left join product p on c.cid=p.cid where p.pid=?";
		Category category=qr.query(sql1, new BeanHandler<>(Category.class),pid);
		
		String sql2="select * from product where pid=? limit 1";
		Product product=qr.query(sql2, new BeanHandler<>(Product.class), pid);
		product.setCategory(category);
		return product;
	}

	/**
	 * 查询当前页的数据
	 */
	@Override
	public List<Product> findByPage(Page<Product> pb, String cid,String keyword) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		List<Product> productList=new ArrayList<Product>();
		if(cid!=null) {
			String sql = "select * from product where cid=? and pflag=? order by pdate desc limit ?,?";
			 productList= qr.query(sql, new BeanListHandler<>(Product.class),cid,Constant.PRODUCT_IS_UP,pb.getStartIndex(),pb.getPageSize());
		}else if(keyword!=null) {
			String sql = "select * from product where pname like ? order by pdate desc limit ?,?";
			productList= qr.query(sql, new BeanListHandler<>(Product.class),"%"+keyword+"%",pb.getStartIndex(),pb.getPageSize());
		}
		return productList;
	}

	/**
	 * 获取总记录数
	 */
	@Override
	public int getTotalRecord(String cid,String keyword) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		int count=0;
		if(cid!=null) {
			String sql="select count(*) from product where cid=? and pflag=?";
			count=((Long)qr.query(sql, new ScalarHandler(),cid,Constant.PRODUCT_IS_UP)).intValue();
		}else if(keyword!=null) {
			String sql="select count(*) from product where pname like ?";
			count= ((Long)qr.query(sql, new ScalarHandler(),"%"+keyword+"%")).intValue();
		}
		return count;
	}

	/**
	 * 后台展示上架商品
	 */
	@Override
	public List<Product> findAll(String state) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		List<Product> list=new ArrayList<Product>();
		if("onsale".equals(state)) {
			String sql="select * from product where pflag=? order by pdate desc";
			list=qr.query(sql, new BeanListHandler<>(Product.class),Constant.PRODUCT_IS_UP);
		}else if("offsale".equals(state)) {
			String sql="select * from product where pflag=? order by pdate desc";
			list=qr.query(sql, new BeanListHandler<>(Product.class),Constant.PRODUCT_IS_DOWN);
		}else if("hotsale".equals(state)) {
			String sql="select * from product where is_hot=? order by pdate desc";
			list=qr.query(sql, new BeanListHandler<>(Product.class),Constant.PRODUCT_IS_HOT);
		}
		return list;
	}

	/**
	 * 保存商品
	 */
	@Override
	public void save(Product p) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into product values(?,?,?,?,?,?,?,?,?,?)";
		qr.update(sql,p.getPid(),p.getPname(),p.getMarket_price(),p.getShop_price(),
						p.getPimage(),p.getPdate(),p.getIs_hot(),p.getPdesc(),
						p.getPflag(),p.getCategory().getCid());
	}

	/**
	 * 编辑商品
	 */
	@Override
	public void update(Product p) throws Exception {
		if(p.getPimage()!=null) {
			//图片路径不为空时才更新图片路径
			QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
			String sql="update product set pname=?,market_price=?,shop_price=?,"
					+ "pimage=?,pdate=?,is_hot=?,pdesc=?,pflag=?,cid=? where pid=?";
			qr.update(sql,p.getPname(),p.getMarket_price(),p.getShop_price(),
							p.getPimage(),p.getPdate(),p.getIs_hot(),p.getPdesc(),
							p.getPflag(),p.getCategory().getCid(),p.getPid());
		}else if(p.getPimage()==null){
			//图片路径为空时不跟新图片路径
			QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
			//先获取原有路径
			String sql1="select * from product where pid=?";
			Product product=qr.query(sql1, new BeanHandler<>(Product.class),p.getPid());
			
			String sql="update product set pname=?,market_price=?,shop_price=?,"
					+ "pimage=?,pdate=?,is_hot=?,pdesc=?,pflag=?,cid=? where pid=?";
			qr.update(sql,p.getPname(),p.getMarket_price(),p.getShop_price(),
					product.getPimage(),p.getPdate(),p.getIs_hot(),p.getPdesc(),
							p.getPflag(),p.getCategory().getCid(),p.getPid());
		}
	}

	/**
	 * 删除商品
	 */
	@Override
	public int delete(String pid) throws Exception {
		int rowAffect=0;
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="delete from product where pid=?";
		rowAffect=qr.update(sql,pid);
		return rowAffect;
		
	}

}
