package com.leonard.applicance.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.leonard.applicance.domain"})
@EnableJpaRepositories(basePackages = {"com.leonard.applicance.repo"})
@ComponentScan({"com.leonard.applicance.controller"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
