package enactApp.enactAPI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EnactApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnactApiApplication.class, args);
	}

	/**
	 * @return the method to be executed
	 */
	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			//This line can be commented out after data has been inserted.
			System.out.println("APP RUNNING");
		};
	}

}
