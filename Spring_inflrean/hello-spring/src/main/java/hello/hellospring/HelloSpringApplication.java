package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
		/*
		스프링 부트 어플리케이션 실행
		톰캣을 내장 하고 있어서 같이 올라감.
		 */
	}

}
