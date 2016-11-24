package cn.it.shop.action;

import java.util.Map;


import org.apache.struts2.interceptor.ParameterAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.BackData;
import cn.it.shop.model.Forder;
import cn.it.shop.model.SendData;
import cn.it.shop.model.User;

/*
 * Struts处理流程：获取请求后，
 */
@Controller
@Scope("prototype")
public class PayAction extends BaseAction<Object> implements ParameterAware{

	private Map<String, String[]> parameters;
	
	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}
	
	@Override
	public Object getModel() {
		if(parameters.get("pd_FrpId")!=null){
			model = new SendData();
		}else {
			model = new BackData();
		}
		return model;
	}

	public String goBank(){
		SendData sendData =(SendData)model;
		//补全参数，从session中获取
		Forder forder = (Forder)session.get("oldForder");
		User user = (User)session.get("user");
		sendData.setP2_Order(forder.getId().toString());
		sendData.setP3_Amt(forder.getTotal().toString());
		sendData.setPa_MP(user.getEmail()+","+user.getPhone());
		
		payService.saveDataToRequest(request, sendData);
		System.out.println(request.get("hmac"));
		//跳到支付页面
		return "pay";
	}
	public void backBank(){
		
		BackData backData = (BackData)model;
		boolean isOK = payService.checkBackData(backData);
		if(isOK){
			//更新订单状态
			forderService.updateStatusById(Integer.parseInt(backData.getR6_Order()), 2);
			//根据user邮箱地址，发送邮件
			String address = backData.getR8_MP().split(",")[0];
			emailUtil.sendEmail(address, backData.getR6_Order());
			//发送短信
			String phone = backData.getR8_MP().split(",")[1];
			messageUtil.sendMessage(phone, backData.getR6_Order());
			System.out.println("--success--");
		}else {
			System.out.println("---fail--");
		}
	}
	
}
