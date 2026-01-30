package com.example.blog_jpa;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogJpaApplication {

	public static void main(String[] args) {
		Dotenv env = Dotenv.configure().ignoreIfMissing().load();

		env.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue())
		);

		SpringApplication.run(BlogJpaApplication.class, args);
	}

}
