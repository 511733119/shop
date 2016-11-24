package cn.it.shop.daoImpl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import cn.it.shop.dao.AccountDao;
import cn.it.shop.dao.BaseDao;
import cn.it.shop.dao.CategoryDao;
import cn.it.shop.dao.ForderDao;
import cn.it.shop.dao.ProductDao;
import cn.it.shop.dao.SorderDao;
import cn.it.shop.dao.UserDao;
import cn.it.shop.model.Product;
import cn.it.shop.service.BaseService;

@SuppressWarnings("unchecked")
@Repository("baseDao")
@Lazy(true)
public class BaseDaoImpl<T> implements BaseDao<T> {

	private Class clazz;  //clazz中存储了当前操作的类型
	
	@Resource
	private SessionFactory sessionFactory;
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public  BaseDaoImpl(){
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class)type.getActualTypeArguments()[0];
	}
	
	@Override
	public void save(T t) {
		getSession().save(t);
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public void delete(int id) {
		String hql = "DELETE "+ clazz.getSimpleName()+ " WHERE id=:id";
		getSession().createQuery(hql)
				.setInteger("id", id)
				.executeUpdate();
	}

	@Override
	public T get(int id) {
		return (T) getSession().get(clazz, id);
	}

	@Override
	public List<T> query() {
		String hql = "FROM "+ clazz.getSimpleName();
		return getSession().createQuery(hql).list();
	}


}
