package cn.it.shop.daoImpl;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import cn.it.shop.dao.ForderDao;
import cn.it.shop.model.Forder;
import cn.it.shop.model.Sorder;
import cn.it.shop.service.ForderService;

/*
 * 模块自身的业务逻辑
 */
@Repository("forderDao")
public class ForderDaoImpl extends BaseDaoImpl<Forder> implements ForderDao {

	@Override
	public void updateStatusById(int id, int sid) {
		String hql = "UPDATE Forder f SET f.status.id=:sid WHERE f.id=:id";
		getSession().createQuery(hql)
					.setInteger("sid", sid)
					.setInteger("id", id)
					.executeUpdate();
	}

}
