package cn.it.shop.action;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.Forder;
import cn.it.shop.model.Status;
import cn.it.shop.model.User;
@Controller
@Scope("prototype")
public class ForderAction extends BaseAction<Forder> {

	@Override
	public Forder getModel() {
		model = (Forder) session.get("forder");
		return model;
	}
	//实现购物车（订单）与购物项（订单项）级联入库
	public String save(){
		//把session中的购物项，交给当前的model对象
		model.setUser((User)session.get("user"));
		model.setStatus(new Status(1));
		//级联入库,需要
		forderService.save(model);
		//订单已经生成，删除session中的购物车
		session.put("oldForder", session.remove("forder"));
		session.put("forder", new Forder());
		return "bank";
	}
	
}
