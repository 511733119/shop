package cn.it.shop.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.User;
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	public String login(){
		//进行登录验证
		model = userService.login(model);
		if(model==null){
			session.put("error", "登录失败");
			return "ulogin";
		}else {
			//登录成功，则根据情况返回相应的页面
			session.put("user", model);
			//根据session中goURL是否有值而决定页面的跳转
			if(session.get("goURL")==null){
				return "index";
			}else{
				return "goURL";
			}
			
		}
	}
}
