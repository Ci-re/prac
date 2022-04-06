package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
	
	@Bean
	CommandLineRunner commandlineRunner(StudentRepository repository) {
		 return args -> {
			Student cire = new Student(
				"Cletus", LocalDate.of(2000, Month.JANUARY, 5), "cire@gmail.com"
			);
			Student john = new Student(
					"john", LocalDate.of(2000, Month.FEBRUARY, 5), "john@gmail.com"
			);
			repository.saveAll(List.of(cire, john));
		};
		
	}
}
