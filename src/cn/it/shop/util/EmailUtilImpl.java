package cn.it.shop.util;

import java.util.Properties;

import javassist.bytecode.stackmap.BasicBlock.Catch;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

/*
 * 用来完成邮件的发送
 */
@Component("emailUtil")
public class EmailUtilImpl implements EmailUtil{

	/* (non-Javadoc)
	 * @see cn.it.shop.util.MessageUtil#sendEmail(java.lang.String, java.lang.String)
	 */
	public void sendEmail(String address,String id){

		Session session = null;
		Message message = null;
		Transport transport = null;
		try{
			//登录邮件客户端
			Properties prop = new Properties();
			prop.setProperty("mail.transport.protocol", "smtp");
			session = Session.getDefaultInstance(prop);
			session.setDebug(true);
			//创建邮件对象
			message =  new MimeMessage(session);
			//写信
			message.setSubject("订单支付成功邮件!");
			//正文内容
			message.setContent("订单"+ id +"支付成功", "text/html;charset=utf-8");
			//发件人地址
			message.setFrom(new InternetAddress("chjcal511733119@sina.com"));
			transport = session.getTransport();
			//和链接邮件服务器的认证信息
			transport.connect("smtp.sina.com", "chjcal511733119", "ss6667628111");
			//设置收件人地址
			transport.sendMessage(message, new Address[]{new InternetAddress(address)});
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			try {
				transport.close();
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}	
	}

}
