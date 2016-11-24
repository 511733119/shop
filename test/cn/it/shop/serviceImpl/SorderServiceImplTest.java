package cn.it.shop.serviceImpl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.it.shop.model.Product;
import cn.it.shop.service.ProductService;
import cn.it.shop.service.SorderService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-*.xml")
public class SorderServiceImplTest {

	@Resource
	private SorderService sorderService;
	
	@Test
	public void queryByCid() {
	   for(Object temp : sorderService.querySale(5)){
		   System.out.println(temp);
	    }
	}
	
}
