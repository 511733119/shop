package cn.it.shop.service;

import java.util.List;

import cn.it.shop.model.Forder;
import cn.it.shop.model.Product;
import cn.it.shop.model.Sorder;

public interface SorderService extends BaseService<Sorder> {

	//添加购物项
	public Forder addSorder(Forder forder,Product product,int num);
	//把product转化为sorder
	public Sorder productToSorder(Product product,int num);
	//根据商品编号更新商品的数量
	public Forder updateSorder(Forder forder,Sorder sorder);
	//查询热点商品的销售量
	public List<Object> querySale(int number);
	
}
