package com.sandeep.multipledbtest;

import com.sandeep.multipledbtest.mysql.entities.Product;
import com.sandeep.multipledbtest.mysql.repo.ProductRepo;
import com.sandeep.multipledbtest.oracle.entities.User;
import com.sandeep.multipledbtest.oracle.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class MultipleDbTestApplicationTests {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
	}

	@Test
	void dbInsertTest()
	{
		Product product = Product.builder()
				.price(125800)
				.name("iPhone")
				.description("An apple product")
				.build();

		User user = User.builder()
				.firstName("Sandeep")
				.lastName("Kumar")
				.build();

		productRepo.save(product);
		userRepo.save(user);
		System.out.println("Database inserted successfully");
	}

	@Test
	void dbGetTest()
	{
		Product product = productRepo.findById(1).get();
		System.out.println(product);

		User user = userRepo.findById(1).get();
		System.out.println(user);

	}

	@Test
	void dbGetTest2()
	{
		productRepo.findAll().forEach(System.out::println);
		userRepo.findAll().forEach(System.out::println);
	}

}
