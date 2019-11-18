package ru.ejmentor.SpringBootExample.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "ru.ejmentor")
@EnableJpaRepositories(basePackages = "ru.ejmentor.SpringBootExample.repository")
@EntityScan(basePackages = "ru.ejmentor.SpringBootExample.model")
public class SpringBootExampleApplication {

	public static void main(String[] args) { SpringApplication.run(SpringBootExampleApplication.class, args); }

}
