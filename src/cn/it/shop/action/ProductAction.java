package cn.it.shop.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;

import cn.it.shop.model.Forder;
import cn.it.shop.model.Pager;
import cn.it.shop.model.Product;
import net.sf.json.JSONObject;

/*
 *ModelDriven 此接口必须要实现getModel（）方法，此方法会把返回的对象，压到值栈中 
 */
@Controller("productAction")
@Scope("prototype")
public class ProductAction extends BaseAction<Product> {
	
	 private String result;
     public String getResult() {
         return result;
     }
     public void setResult(String result) {
         this.result = result;
     }
     
	public String queryJoinCategory(){
		//用来存储分页的数据
		pageMap = new HashMap<String, Object>();
		//根据关键字和分页的参数查询相应的数据
		List<Product> productList = productService.queryJoinCategory(model.getName(), page, rows);
		pageMap.put("rows", productList);
		pageMap.put("total", productService.getCount(model.getName()));
		return "jsonMap";
	}
	
	public void save(){
		String pic = fileUpload.uploadFile(fileImage);
		model.setPic(pic);
		//商品信息入库
		productService.save(model);
	}
	
	public String get(){
		request.put("product", productService.get(model.getId()));
		HttpServletRequest req = ServletActionContext.getRequest();
		int typeid = (Integer.parseInt(req.getParameter("typeid")));
		session.put("similar_product",productService.queryByCid(typeid));
		return "detail";
	}
	//删除单个购物项
	public String deleteById(){
		int id = model.getId();
		//获得session里的购物车
		Forder forder = (Forder)session.get("forder");
		forder = productService.deleteById(forder, id);
		forder.setTotal(forderService.cluTotal(forder));
		session.put("forder", forder);
		return "backCar";
	}
	
	//删除单个购物项
	public String deleteByCheck(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//获取选中复选框参数
		String param = request.getParameter("ids");
		//分割字符串
		String ids[] = param.split(",");
		Integer id[] =null;
		if(ids.length==1){
			id = new Integer[ids.length];
			for (int i = 0; i < ids.length; i++) {
				id[i] = Integer.parseInt(ids[i]);
			}
		}else {
			id = new Integer[ids.length];
			for (int i = 0; i < ids.length; i++) {
				id[i] = Integer.parseInt(ids[i]);
			}
		}
		for(int i : id){
			System.out.println("i:"+i);
		}
		//获得session里的购物车
		Forder forder = (Forder)session.get("forder");
		forder = productService.deleteByCheck(forder, id);
		forder.setTotal(forderService.cluTotal(forder));
		session.put("forder", forder);
		
		// 以流的形式
		inputStream = new ByteArrayInputStream("true".getBytes());
		
		return "stream";
	}
		
	//删除整个购物车
	public String deleteAll(){
		if(session.get("forder")!=null){
			session.remove("forder");
		}
		// 以流的形式
		inputStream = new ByteArrayInputStream("true".getBytes());
		
		return "stream";
	}
	
	/*
	 * 根据搜索关键字获取相关商品名称
	 */
	public String getProName(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = null;
		try {
			name = URLDecoder.decode(request.getParameter("name"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<String> listName = productService.getProName(name);
		Map<String, Object> nameMap = new HashMap<String, Object>();
		nameMap.put("name", listName);
		Gson gson = new Gson();
		String json = gson.toJson(listName);
        result = json.toString();//给result赋值，传递给页面
		return "nameJsonMap";
		
	}
	
	/*\
	 * 根据输入的关键字搜索相关商品
	 */
	public String getSearchProduct(){
		HttpServletRequest req = ServletActionContext.getRequest();
		Pager<Product> proList = productService.getSearchProduct(1,16,req.getParameter("name"));
		request.put("proList", proList);
		session.put("name", req.getParameter("name"));
		return "getSearchPage";
	}
	/*
	 * 根据输入的关键字搜索相关商品(分页用)
	 */
	public String getSearchProduct2(){
		HttpServletRequest req = ServletActionContext.getRequest();
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		Pager<Product> proList = productService.getSearchProduct(pageNum,16,String.valueOf(session.get("name")));
		request.put("proList", proList);
		return "getSearchPage";
	}
	
}
