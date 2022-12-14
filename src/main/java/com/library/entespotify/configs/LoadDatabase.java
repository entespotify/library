package com.library.entespotify.configs;

import com.library.entespotify.models.Track;
import com.library.entespotify.models.User;
import com.library.entespotify.repositories.TrackRepository;
import com.library.entespotify.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDatabase(TrackRepository trackRepository) {

        return args -> {
            log.info("Preloading " + trackRepository.save(new Track("exceptions 1", "artist 1", "album 1")));
            log.info("Preloading " + trackRepository.save(new Track("exceptions 2", "artist 2", "album 2")));
        };
    }

    @Bean
    CommandLineRunner initUserDatabase(UserRepository userRepository) {

        return args -> {
            log.info("Preloading " + userRepository.save(new User("admin", passwordEncoder.encode("admin"), true, "ADMIN")));
            log.info("Preloading " + userRepository.save(new User("ecom", passwordEncoder.encode("ec0m"), true, "USER")));
        };
    }
}