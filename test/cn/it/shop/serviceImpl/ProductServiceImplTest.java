package cn.it.shop.serviceImpl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.it.shop.model.Product;
import cn.it.shop.service.ProductService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class ProductServiceImplTest {

	@Resource
	private ProductService productService;
	
	@Test
	public void queryByCid() {
	   for(Product product : productService.queryByCid(1)){
		   System.out.println(product);
	    }
	}
	@Test
	public void query(){
		System.out.println(productService.query());
	}
	
	@Test
	public void getName(){
		System.out.println(productService.getProName("圣"));
	}
}
