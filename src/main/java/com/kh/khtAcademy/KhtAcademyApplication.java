package com.kh.khtAcademy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kh.khtAcademy.mapper")
public class KhtAcademyApplication {

	public static void main(String[] args) {
		SpringApplication.run(KhtAcademyApplication.class, args);
	}

}
