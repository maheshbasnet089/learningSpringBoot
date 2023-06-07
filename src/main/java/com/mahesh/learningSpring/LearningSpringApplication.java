package com.mahesh.learningSpring;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mahesh.learningSpring.model.Content;
import com.mahesh.learningSpring.repository.ContentRepository;
import com.mahesh.learningSpring.model.Status;

import com.mahesh.learningSpring.model.Type;

@SpringBootApplication
public class LearningSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ContentRepository repository) {
		return args -> {
			System.out.println("Inside commandlinerunner");
			Content c = new Content(null, "test world", "test", Status.IDEA,  Type.VIDEO, LocalDateTime.now(), null,
					"");
			repository.save(c);
		};
	}

}
