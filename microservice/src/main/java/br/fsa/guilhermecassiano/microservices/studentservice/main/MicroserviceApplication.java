package br.fsa.guilhermecassiano.microservices.studentservice.main;

import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
@ComponentScan(basePackages = "br.fsa.guilhermecassiano.microservices.studentservice")
@EnableMongoRepositories(basePackages = "br.fsa.guilhermecassiano.microservices.studentservice.repository")
public class MicroserviceApplication {

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(MongoClients.create("mongodb+srv://guilhermemalfara:rkbK8naPXm5fvdJa@cluster1.6esfa.mongodb.net/?retryWrites=true&w=majority"), "student");
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MicroserviceApplication.class, args);
		listEndpoints(ctx);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// Método para listar os endpoints registrados
	public static void listEndpoints(ApplicationContext ctx) {
		RequestMappingHandlerMapping requestMappingHandlerMapping = ctx.getBean(RequestMappingHandlerMapping.class);
		requestMappingHandlerMapping.getHandlerMethods().forEach((key, value) ->
				System.out.println("Mapped URL: " + key + " -> " + value)
		);
	}
}
