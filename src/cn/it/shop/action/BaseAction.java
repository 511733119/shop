package cn.it.shop.action;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.FileImage;
import cn.it.shop.service.AccountService;
import cn.it.shop.service.CategoryService;
import cn.it.shop.service.ForderService;
import cn.it.shop.service.PayService;
import cn.it.shop.service.ProductService;
import cn.it.shop.service.SorderService;
import cn.it.shop.service.UserService;
import cn.it.shop.util.EmailUtil;
import cn.it.shop.util.FileUpload;
import cn.it.shop.util.MessageUtil;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller("baseAction")
@Scope("prototype")
public class BaseAction<T> extends ActionSupport implements ApplicationAware,SessionAware,RequestAware,ModelDriven<T>{

	protected T model;
	//要删除的id
	protected String ids;
	
	protected Integer page;
	
	protected Integer rows;

	protected InputStream inputStream;
	
	protected List<T> jsonList=null;
	
	protected Map<String, Object> pageMap = null;
	
	protected FileImage fileImage;
	
	@Resource
	protected CategoryService categoryService;
	@Resource
	protected AccountService accountService;
	@Resource
	protected ProductService productService;
	@Resource
	protected ForderService forderService;
	@Resource
	protected SorderService sorderService;
	@Resource
	protected UserService userService;
	@Resource
	protected FileUpload fileUpload;
	@Resource
	protected PayService payService;
	@Resource
	protected EmailUtil emailUtil;
	@Resource
	protected MessageUtil messageUtil;
	
	protected Map<String, Object> application;
	protected Map<String, Object> session;
	protected Map<String, Object> request;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public T getModel() {   
		
		ParameterizedType type= (ParameterizedType)this.getClass().getGenericSuperclass();
		Class clazz = (Class)type.getActualTypeArguments()[0];
		try {
			model = (T)clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return model;
	}
	
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public List<T> getJsonList() {
		return jsonList;
	}

	public Map<String, Object> getPageMap() {
		return pageMap;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public FileImage getFileImage() {
		return fileImage;
	}

	public void setFileImage(FileImage fileImage) {
		this.fileImage = fileImage;
	}
	
}
