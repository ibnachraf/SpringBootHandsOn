package ibn.achraf.demo;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class BforbankinterviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(BforbankinterviewApplication.class, args);
	}

	@Bean
	public NewTopic topic2(@Value("${inputTopic}") String inputTopic) {
		return TopicBuilder.name(inputTopic).partitions(1).replicas(1).build();
	}
	@Bean
	public FlywayMigrationStrategy cleanMigrateStrategy() {
	    return flyway -> {
	        System.out.println("🔧 Flyway migration starting...");
	        flyway.migrate();
	        System.out.println("✅ Flyway migration completed.");
	    };
	}
}
