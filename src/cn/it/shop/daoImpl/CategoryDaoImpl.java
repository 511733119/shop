package cn.it.shop.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import cn.it.shop.dao.CategoryDao;
import cn.it.shop.model.Category;
import cn.it.shop.model.Pager;
import cn.it.shop.model.Product;
import cn.it.shop.service.CategoryService;

/*
 * 模块自身的业务逻辑
 */
@SuppressWarnings("unchecked")
@Repository("categoryDao")
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao {

	
	public CategoryDaoImpl(){
		super();
	}

	//查询热点类别
	@Override
	public List<Category> queryJoinAccount(String type,int page,int size) {
		String hql = "FROM Category c LEFT JOIN FETCH c.account WHERE c.type LIKE :type";
		return getSession().createQuery(hql)
			.setString("type", "%"+type+"%")
			.setFirstResult((page-1)*size)
			.setMaxResults(size)
			.list();
	}

	@Override
	public Long getCount(String type) {
		String hql = "SELECT count(c) FROM Category c WHERE c.type LIKE :type";
		return (Long) getSession().createQuery(hql)
			.setString("type", "%"+type+"%")
			.uniqueResult();
	}

	@Override
	public void deleteByIds(String ids) {
		String hql="DELETE FROM Category WHERE id in ("+ids+")";
		getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public List<Category> queryByHot(boolean hot) {
		String hql ="FROM Category c WHERE c.hot LIKE :hot"; 
		return getSession().createQuery(hql)
				.setBoolean("hot", hot).list();
	}
	
	@Override
	public Pager<Product> findProduct( int pageNum,int pageSize,int id) {
		
		Pager<Product> result = null;
		
		int fromIndex = pageSize * (pageNum-1);
		
		String hql ="FROM Product p JOIN FETCH p.category WHERE p.commend=true AND p.open=true AND p.category.id=:cid ORDER BY p.date DESC  limit "+fromIndex+", "+pageSize;
		//计算总记录条数
		String countHql = "SELECT count(id) FROM Product WHERE cid=:cid";
		
		//存放product
		List<Product> productList = new ArrayList<Product>();
		productList = getSession().createQuery(hql)
						.setInteger("cid", id)
						.setFirstResult(fromIndex)
						.setMaxResults(pageSize)
						.list();
		
		//计算总记录条数
		List countResult = getSession().createQuery(countHql).setInteger("cid", id).list();
		int totalRecord = ((Number)countResult.get(0)).intValue();
		
		//获取总页数
		int totalPage = totalRecord / pageSize;
		if(totalRecord % pageSize !=0){
			totalPage++;
		}
		result = new Pager<Product>(pageSize,pageNum
				,totalRecord,totalPage,productList);
		return result;
	}

}
