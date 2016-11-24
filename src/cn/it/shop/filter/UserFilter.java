package cn.it.shop.filter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.constructs.web.filter.Filter;

public class UserFilter extends Filter{

	@Override
	protected void doDestroy() {
		
	}

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain) throws Throwable {
		//判断当前session是否有用户信息
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		if(req.getSession().getAttribute("user")==null){
			
			String goURL = req.getServletPath();
			String param = req.getQueryString();
			if(param!=null){
				goURL = goURL + "?" + param;
			}
			//把当前客户想要访问的地址，存储到session
			req.getSession().setAttribute("goURL", goURL);
			//非法请求，跳转到登录页面
			req.getSession().setAttribute("error", "请先登录！");
			res.sendRedirect(req.getContextPath()+"/ulogin.jsp");
			
		}else{
			// 如果有下一个过滤器则跳转，否则直接到目标页面
			chain.doFilter(request, response);
		}
	}

	@Override
	protected void doInit() throws Exception {
		
	}

}
