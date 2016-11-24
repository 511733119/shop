package cn.it.shop.action;

import java.io.ByteArrayInputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.it.shop.model.Forder;
import cn.it.shop.model.Product;
import cn.it.shop.model.Sorder;
@Controller
@Scope("prototype")
public class SorderAction extends BaseAction<Sorder> {

	private static final long serialVersionUID = 1L;

	public String addSorder(){
		//根据product.id获取相应的商品数据
		Product product = productService.get(model.getProduct().getId());
		//判断当前session是否有购物车，如果没有则创建
		if(session.get("forder")==null){
			//创建新购物车，存储到session中
			session.put("forder", new Forder(new HashSet<Sorder>()));
		}
		Forder forder = (Forder)session.get("forder");
		//获得购物数量
		int num = model.getNumber();
//		System.out.println("num:"+num);
		//把商品信息转化为sorder,并且添加到购物车(判断购物项是否重复)
		forder=sorderService.addSorder(forder,product,num);
		//计算购物的总价格
		forder.setTotal(forderService.cluTotal(forder));
		//把新的购物车存储到session中
		session.put("forder", forder);
		int number=0;
		for(Sorder sorder : forder.getSorderSet()){
			number += sorder.getNumber();
		}
		session.put("number",number);
		return "showCar";
	}
	
	//根据商品编号更新商品的数量
	public String updateSorder(){
		Forder forder = (Forder)session.get("forder");
		forder = sorderService.updateSorder(forder, model);
		forder.setTotal(forderService.cluTotal(forder));
		session.put("forder", forder);
		inputStream = new ByteArrayInputStream(forder.getTotal().toString().getBytes());
		return "stream";
	}
	
	public String querySale(){
		List<Object> jsonList = sorderService.querySale(model.getNumber());
		ActionContext.getContext().getValueStack().push(jsonList);
		return "jsonList";
	}
	
}
