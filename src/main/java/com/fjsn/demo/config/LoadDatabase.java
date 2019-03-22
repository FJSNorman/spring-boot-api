package com.fjsn.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fjsn.demo.userapi.entity.User;
import com.fjsn.demo.userapi.repo.UserRepo;

@Configuration
public class LoadDatabase {
	private final Logger LOG = LoggerFactory.getLogger(LoadDatabase.class);
	
	@Bean
	CommandLineRunner initDatabase(UserRepo repository) {
		return args -> {
			LOG.info("Preloading " + repository.save(new User("Fulano", "Martinez", "1990-01-01")));
			LOG.info("Preloading " + repository.save(new User("Sutano", "Lopez", "1990-02-02")));
			LOG.info("Preloading " + repository.save(new User("Mengano", "Rodriguez", "1990-03-03")));
			LOG.info("Preloading " + repository.save(new User("Perengano", "Godinez", "1990-04-04")));
		};
	}
}
