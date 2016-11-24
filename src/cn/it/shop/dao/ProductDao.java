package cn.it.shop.dao;

import java.util.List;

import cn.it.shop.model.Pager;
import cn.it.shop.model.Product;

public interface ProductDao extends BaseDao<Product> {

	public List<Product> queryJoinCategory(String name,int page,int size);
	
	//根据关键字查询总记录数
	public Long getCount(String name);
	
	//根据类别查询商品
	public List<Product> queryByCid(int cid);
	
	//根据类别查询商品
	public List<Product> queryAllPro(int cid);
	
	public List<Product> getProName(String name) ;
}
