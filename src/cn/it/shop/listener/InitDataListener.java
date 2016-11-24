package cn.it.shop.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.it.shop.util.FileUpload;
import cn.it.shop.util.ProdoctTimerTask;
/*
 * 用于项目启动的时候数据初始化
 */
public class InitDataListener implements ServletContextListener {

	private ApplicationContext context = null;
	
	private ProdoctTimerTask productTimerTask=null;
	
	private FileUpload fileUpload=null;
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
	    context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		productTimerTask = (ProdoctTimerTask)context.getBean("productTimerTask");
		fileUpload = (FileUpload)context.getBean("fileUpload");
		//把内置对象交给productTimerTask
		productTimerTask.setApplication(event.getServletContext());
		//通过设置定时器，让首页的数据每隔一小时同步一次（配置为守护线程）
		new Timer(true).schedule(productTimerTask, 0, 1000*60*60);
		
		//项目启动时加载银行图标
		event.getServletContext().setAttribute("bankList", fileUpload.getBankImage());
	}

}
