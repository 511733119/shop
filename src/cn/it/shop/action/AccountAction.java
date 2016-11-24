package cn.it.shop.action;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.Account;

/*
 *ModelDriven 此接口必须要实现getModel（）方法，此方法会把返回的对象，压到值栈中 
 */
@Controller("accountAction")
@Scope("prototype")
public class AccountAction extends BaseAction<Account> {

	public String query(){
		jsonList = accountService.query();
		return "jsonList";
	}

	
}
