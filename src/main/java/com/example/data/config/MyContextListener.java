package com.example.data.config;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.data.tools.WebTool;
import com.example.data.tools.interfejs.WebToolInterface;

@Component
public class MyContextListener implements ServletContextListener {
	
	@Autowired
	private ScheduledExecutorService scheduler;
	@Autowired
	private WebTool webTool;
	
	public class RunningTask implements Runnable{
		private long frequency;
		private String camUrl;
		private String path;
		private WebToolInterface wti;
		
		public RunningTask(int f, String cu, String p){
			frequency = f;
			camUrl = cu;
			path = p;
			wti = webTool;
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
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		RunningTask rt1 = new RunningTask(1 , "http://109.206.96.230:8080/cam_8.jpg?uniq=0.09287289965557965", 
				"./camera1");
		RunningTask rt2 = new RunningTask(2 , "http://109.206.96.96:8080/cam_18.jpg?uniq=0.7322573720813867", 
				"./camera2");
		
		scheduler.scheduleWithFixedDelay(rt1, 0, rt1.getFrequency(), TimeUnit.MINUTES);
		scheduler.scheduleWithFixedDelay(rt2, 0, rt2.getFrequency(), TimeUnit.MINUTES);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		scheduler.shutdown();
	}

}
