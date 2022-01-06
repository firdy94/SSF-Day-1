package ibf.ssf.day1workshop;

import java.util.Collections;
import java.util.Properties;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
public class Day1workshopApplication {
	private static final String DEFAULT_PORT = "3000";

	public static void main(String[] args) {
		SpringApplication myApp = new SpringApplication(Day1workshopApplication.class);
		// SpringApplication.run(Day1workshopApplication.class, args);
		String port = "null";
		ApplicationArguments cliOptions = new DefaultApplicationArguments(args);
		if (cliOptions.containsOption("port")) {
			port = cliOptions.getOptionValues("port").get(0);
			if ((port == null || port.isEmpty())) {
				port = System.getenv("PORT");
				if (port == null) {
					port = DEFAULT_PORT;
				}
			}
			myApp.setDefaultProperties(Collections.singletonMap("server.port", port));
		}

		// myApp.setDefaultProperties(Collections.singletonMap("server.port", port));
		System.out.printf("Application started on port %s\n", port);
		myApp.run(args);

	}

	@Bean
	public CommonsRequestLoggingFilter log() {
		CommonsRequestLoggingFilter logger = new CommonsRequestLoggingFilter();
		logger.setIncludeClientInfo(true);
		logger.setIncludeQueryString(true);
		return logger;
	}

}
