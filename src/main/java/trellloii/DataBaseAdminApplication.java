package trellloii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@SpringBootApplication
public class DataBaseAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataBaseAdminApplication.class, args);
	}
}
