package tn.esprit.spring.AhmedGuedri;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
public class AhmedGuedriApplication {

	public static void main(String[] args)  {
		SpringApplication.run(AhmedGuedriApplication.class, args);


	}
}


