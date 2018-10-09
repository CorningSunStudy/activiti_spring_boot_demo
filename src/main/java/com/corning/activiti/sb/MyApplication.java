package com.corning.activiti.sb;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.postgresql.Driver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(final RepositoryService repositoryService, final RuntimeService runtimeService, final TaskService taskService) {
        return args -> {
            log.info("Number of process definitions : " + repositoryService.createProcessDefinitionQuery().count());
            log.info("Number of tasks : " + taskService.createTaskQuery().count());
            runtimeService.startProcessInstanceByKey("oneTaskProcess");
            log.info("Number of tasks after process start: " + taskService.createTaskQuery().count());
        };
    }

    @Bean
    public DataSource database() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/activiti_sb")
                .username("sunkangning932")
                .password("")
                .driverClassName(Driver.class.getName())
                .build();
    }
}
