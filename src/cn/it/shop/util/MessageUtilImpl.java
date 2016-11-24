package cn.it.shop.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Component;

/*
 * 实现短信的发送功能
 */
@Component("messageUtil")
public class MessageUtilImpl implements MessageUtil {

	/* (non-Javadoc)
	 * @see cn.it.shop.util.MessgaeUtil#sendMessage(java.lang.String, java.lang.String)
	 */
	@Override
	public void sendMessage(String phone,String id){
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn/");
		post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		
		post.setParameter("Uid", "chjcal");
		post.setParameter("Key", "00b3d43b1bf428792eb8");
		post.setParameter("smsMob", phone);
		post.setParameter("smsText", "订单"+ id +"已经支付成功");
		
		int code =0;
		try {
			code = client.executeMethod(post);
			System.out.println("状态码:"+code);
			//获取服务器返回的数据信息
			String result = post.getResponseBodyAsString();
			System.out.println("结果为："+result);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			post.releaseConnection();
		}
	}
//	public static void main(String[] args) throws Exception {
//		HttpClient client = new HttpClient();
//		PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn/");
//		post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
//		
//		post.setParameter("Uid", "chjcal");
//		post.setParameter("Key", "00b3d43b1bf428792eb8");
//		post.setParameter("smsMob", "15626401344");
//		post.setParameter("smsText", "订单已经支付成功");
//		
//		int code = client.executeMethod(post);
//		
//		System.out.println("状态码:"+code);
//		String result = post.getResponseBodyAsString();
//		System.out.println("结果为："+result);
//	}
}
