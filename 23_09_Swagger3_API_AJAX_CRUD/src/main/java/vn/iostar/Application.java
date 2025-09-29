package vn.iostar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import vn.iostar.config.StorageProperties;
import vn.iostar.service.IStorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Application {
public static void main(String[] args) {
SpringApplication.run(Application.class, args);
}


@Bean
CommandLineRunner init(IStorageService storageService) {
return args -> storageService.init();
}
}
