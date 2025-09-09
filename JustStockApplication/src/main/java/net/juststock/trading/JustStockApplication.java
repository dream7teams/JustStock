package net.juststock.trading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class JustStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(JustStockApplication.class, args);
	}

}
