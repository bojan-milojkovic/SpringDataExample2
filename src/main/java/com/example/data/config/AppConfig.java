package com.example.data.config;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.data.tools.WebTool;

@Configuration
public class AppConfig {

	@Bean
	public WebTool webTool(){
		return new WebTool();
	}
	
	@Bean(name = "scheduler")
	public ScheduledExecutorService scheduledExecutorService (){
		return Executors.newScheduledThreadPool(15);
	}
}