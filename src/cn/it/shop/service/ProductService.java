package cn.it.shop.service;

import java.util.List;

import cn.it.shop.model.Forder;
import cn.it.shop.model.Pager;
import cn.it.shop.model.Product;

public interface ProductService extends BaseService<Product> {

	public List<Product> queryJoinCategory(String name,int page,int size);
	
	//根据关键字查询总记录数
	public Long getCount(String name);
	
	//根据类别查询商品(首页展示)
	public List<Product> queryByCid(int cid);
	
	//根据类别查询商品(首页查看更多展示)
	public List<Product> queyAllPro(int cid);
	
	//删除购物项
	public Forder deleteById(Forder forder,int id);

	//复选框删除购物项
	public Forder deleteByCheck(Forder forder, Integer[] ids);
	
	//获得商品名称
	public List<Product> getProName(String name);
}
