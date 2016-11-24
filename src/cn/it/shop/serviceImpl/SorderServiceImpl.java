package cn.it.shop.serviceImpl;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import cn.it.shop.model.Forder;
import cn.it.shop.model.Product;
import cn.it.shop.model.Sorder;
import cn.it.shop.service.SorderService;

/*
 * 模块自身的业务逻辑
 */
@Service("sorderService")
public class SorderServiceImpl extends BaseServiceImpl<Sorder> implements SorderService {

	@Override
	public Forder addSorder(Forder forder,Product product,int num) {
		boolean isHave = false;
		Sorder sorder = productToSorder(product,num);
		//判断当前购物项是否重复，如果重复则增加数量即可
		for(Sorder old: forder.getSorderSet()){
			if(old.getProduct().getId().equals(sorder.getProduct().getId())){
				//购物项有重复，增加数量即可
				old.setNumber(old.getNumber() + sorder.getNumber());
				isHave=true;
				break;
			}
		}
		if(!isHave){
			//建立购物项与购物车的关联，此时forder。id为null，但是在入库的时候是先入库购物车，在入库购物项，那时就有forder就有主键
			sorder.setForder(forder);
			forder.getSorderSet().add(sorder);
		}
		return forder;
	}

	@Override
	public Sorder productToSorder(Product product,int num) {
		Sorder sorder = new Sorder();
		sorder.setName(product.getName());
		sorder.setNumber(num);
		sorder.setPrice(product.getPrice());
		sorder.setProduct(product);
		return sorder;
	}

	@Override
	public Forder updateSorder(Forder forder,Sorder sorder) {
		for(Sorder temp : forder.getSorderSet()){
			if(temp.getProduct().getId().equals(sorder.getProduct().getId())){
				temp.setNumber(sorder.getNumber());
			}
		}
		return forder;
	}

	@Override
	public List<Object> querySale(int number) {
		return sorderDao.querySale(number);
	}

}
