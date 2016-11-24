package cn.it.shop.serviceImpl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import cn.it.shop.model.Forder;
import cn.it.shop.model.Product;
import cn.it.shop.model.Sorder;
import cn.it.shop.service.ProductService;

/*
 * 模块自身的业务逻辑
 */
@SuppressWarnings("unchecked")
@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {

	@Override
	public List<Product> queryJoinCategory(String name, int page, int size) {
		return productDao.queryJoinCategory(name, page, size);                            
	}
	
	@Override
	public Long getCount(String name) {
		return productDao.getCount(name);
	}

	@Override
	public List<Product> queryByCid(int cid) {
		return productDao.queryByCid(cid);
	}

	@Override
	public Forder deleteById(Forder forder,int id) {
		
		//获得购物车里的购物项
		Set<Sorder> set = forder.getSorderSet();
		//遍历
		Iterator<Sorder> it = set.iterator();
		//新建一个购物项集合，存放删除后的购物项
		Set<Sorder> set2 = new HashSet<Sorder>();
		while(it.hasNext()){
			Sorder sorder = it.next();
			if(!(sorder.getProduct().getId()).equals(id)){
				set2.add(sorder);
			}
		}
		//将购物项放进购物车
		forder.setSorderSet(set2);
		return forder;
	}


	@Override
	public List<Product> queyAllPro(int cid) {
		List<Product> list = productDao.queryAllPro(cid);
		return list;
	}

	@Override
	public Forder deleteByCheck(Forder forder, Integer[] ids) {
		//获得购物车里的购物项
		Set<Sorder> set = forder.getSorderSet();
		//遍历
		Iterator<Sorder> it = set.iterator();
		//新建一个购物项集合，存放删除后的购物项
		Set<Sorder> newset = new HashSet<Sorder>();
		Sorder sorder = null;
		int c= 0;
		
		while(it.hasNext()){
			c =0;
			sorder = it.next();
			for(int i=0;i<ids.length;i++){
				if(!(sorder.getProduct().getId()).equals(ids[i])){
					c++;
				}
			}
			if(c==ids.length){
				newset.add(sorder);
			}
		}
		//将购物项放进购物车
		forder.setSorderSet(newset);
		return forder;
	}

	@Override
	public List<Product> getProName(String name) {
		return productDao.getProName(name);
	}
}
