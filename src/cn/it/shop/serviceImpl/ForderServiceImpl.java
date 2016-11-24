package cn.it.shop.serviceImpl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import cn.it.shop.model.Forder;
import cn.it.shop.model.Sorder;
import cn.it.shop.service.ForderService;

/*
 * 模块自身的业务逻辑
 */
@Service("forderService")
public class ForderServiceImpl extends BaseServiceImpl<Forder> implements ForderService {

	@Override
	public BigDecimal cluTotal(Forder forder) {
		BigDecimal total = new BigDecimal(0.00);
		for(Sorder temp:forder.getSorderSet()){
			total=total.add(temp.getPrice().multiply(new BigDecimal(temp.getNumber())));
		}
		return total;
	}

	@Override
	public void updateStatusById(int id, int sid) {
		forderDao.updateStatusById(id, sid);
	}


}
