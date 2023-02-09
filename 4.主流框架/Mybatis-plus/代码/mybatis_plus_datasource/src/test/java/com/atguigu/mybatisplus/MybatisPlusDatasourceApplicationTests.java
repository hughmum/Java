package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.service.ProductService;
import com.atguigu.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisPlusDatasourceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;

	@Test
	public void test(){
		System.out.println(userService.getById(1));
		System.out.println(productService.getById(1));
	}

}
