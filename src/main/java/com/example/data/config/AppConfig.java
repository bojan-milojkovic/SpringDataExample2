package com.example.data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.example.data.tools.WebTool;
import com.example.data.tools.interfejs.WebToolInterface;

@Configuration
public class AppConfig {

	public class RunningTask implements Runnable{
		private long frequency;
		private String camUrl;
		private String path;
		private WebToolInterface wti;
		
		public RunningTask(int f, String cu, String p){
			frequency = f;
			camUrl = cu;
			path = p;
			wti = webTool();
		}
		
		public String getCamUrl() {
			return camUrl;
		}
		public void setCamUrl(String camUrl) {
			this.camUrl = camUrl;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public long getFrequency() {
			return frequency;
		}
		public void setFrequency(long frequency) {
			this.frequency = frequency;
		}

		@Override
		public void run() {
			wti.saveScreenshot(camUrl, path);
		}
	}
	
	@Bean
	@Scope("singleton")
	public WebTool webTool(){
		return new WebTool();
	}
	
	@Bean(name = "threadPoolTaskScheduler")
	@Scope("singleton")
	public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
		ThreadPoolTaskScheduler tps = new ThreadPoolTaskScheduler();
		tps.setThreadNamePrefix("captureScreenshot_");
		tps.setPoolSize(25);
		tps.initialize();
		
		RunningTask rt1 = new RunningTask(1 , "http://109.206.96.230:8080/cam_8.jpg?uniq=0.09287289965557965", 
				"./camera1");
		RunningTask rt2 = new RunningTask(2 , "http://109.206.96.96:8080/cam_18.jpg?uniq=0.7322573720813867", 
				"./camera2");
		
		tps.scheduleWithFixedDelay(rt1, rt1.getFrequency() * 60000);
		tps.scheduleWithFixedDelay(rt2, rt2.getFrequency() * 60000);/**/
		
		return tps;
	}
}