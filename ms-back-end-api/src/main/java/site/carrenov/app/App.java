package site.carrenov.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.google.common.base.Predicates;

import site.carrenov.app.service.IUserService;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Configuration
@EnableSwagger2
@EnableScheduling
@EntityScan(basePackages = "site.carrenov.app.model.${engine}")
@EnableJpaRepositories(basePackages = "site.carrenov.app.repository.${engine}")
public class App {

	public static final String USER_TAG = "USER";
	
	@Value( "${engine}" )
	private String engine;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(Predicates.not(PathSelectors.regex("/error.*"))).build().enableUrlTemplating(false)
				.tags(new Tag(USER_TAG, USER_TAG));
	}
	
    @Bean
    public IUserService userService() {
    	if("mysql".equalsIgnoreCase(engine)) {
    		return new site.carrenov.app.service.mysql.UserService();
    	}
    	
    	if("oracle".equalsIgnoreCase(engine)) {
    		return new site.carrenov.app.service.oracle.UserService();
    	}
        return null;
    }


}
