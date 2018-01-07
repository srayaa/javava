package com.jeorgio.javava.users;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EntityScan("com.jeorgio.javava.users.entity")
@DubboComponentScan("com.jeorgio.javava.users.service")
@EnableAsync
public class UsersProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersProviderApplication.class, args);
	}

	@Bean
	public TimeBasedGenerator timeBasedGenerator() {
		return Generators.timeBasedGenerator(EthernetAddress.fromInterface());
	}

	@Bean
	public MapperFacade mapperFacade() {
		DefaultMapperFactory.Builder builder = new DefaultMapperFactory.Builder();
		builder.mapNulls(false).useAutoMapping(true);
		return builder.build().getMapperFacade();
	}

	/*@Bean
	public CommandLineRunner init() {
		return (String... string) -> {
			System.out.println("aaaaaa");
			System.out.println("bbbbbb");
		};
	}*/
}
