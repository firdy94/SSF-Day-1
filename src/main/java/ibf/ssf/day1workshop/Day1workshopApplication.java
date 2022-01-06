package ibf.ssf.day1workshop;

import java.util.Collections;
import java.util.Properties;
import java.util.List;

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
		ApplicationArguments cliOptions = new DefaultApplicationArguments(args);
		String port = null;
		List<String> inputPort = cliOptions.getOptionValues("port");
		if ((inputPort == null || inputPort.get(0) == null)) {
			port = System.getenv("PORT");
			if (port == null) {
				port = DEFAULT_PORT;
			}
		} else {
			port = inputPort.get(0);
		}
		myApp.setDefaultProperties(Collections.singletonMap("server.port", port));
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
