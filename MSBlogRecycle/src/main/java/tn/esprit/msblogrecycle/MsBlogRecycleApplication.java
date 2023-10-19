package tn.esprit.msblogrecycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EntityScan(basePackages = {"tn.esprit.msblogrecycle.Entity"})
@ComponentScan(basePackages = {"tn.esprit.msblogrecycle.Controller","tn.esprit.msblogrecycle.Service"})
@EnableJpaRepositories(basePackages = {"tn.esprit.msblogrecycle.Repository"})
public class MsBlogRecycleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBlogRecycleApplication.class, args);
	}

}
