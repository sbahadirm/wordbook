package com.tr.wordbook;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * @author Koray PEKER
 * @since 0.0.1
 */
@SpringBootApplication
public class WordBookApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WordBookApplication.class, args);
	}

    @Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf){
        return hemf.getSessionFactory();
    }

}
