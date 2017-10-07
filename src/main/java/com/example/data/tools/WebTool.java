package com.example.data.tools;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;
import com.example.data.tools.interfejs.WebToolInterface;

@Component
public class WebTool implements WebToolInterface {
	
	public synchronized void saveScreenshot(final String camUrl, final String path){
		DataInputStream is = null;
		//BufferedWriter out = null;
		
		try {
			URLConnection uc = (new URL(camUrl)).openConnection();
			uc.setDoOutput(false);
			uc.setDoInput(true);
			
			is = new java.io.DataInputStream(uc.getInputStream());
			
			File targetFile = new File(path, 
					"screen_" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + ".jpg"
					);
			targetFile.createNewFile();
			OutputStream os = new FileOutputStream(targetFile);
			
			byte[] buffer = new byte[8 * 1024];
		    int bytesRead;
		    while ((bytesRead = is.read(buffer)) != -1) {
		        os.write(buffer, 0, bytesRead);
		    }
		    IOUtils.closeQuietly(is);
		    IOUtils.closeQuietly(os);
			
			/*out = new BufferedWriter(new FileWriter(new File(
						path + "screenshot_" + (new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss")).format(new Date()) + ".jpg"
					)));
			
			int data;
			while((data=is.read()) != -1){
				out.write(data);
			}*/
		} catch (MalformedURLException e) {
			System.err.println("\n\tBad url.\n");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("\n\tBad file path.\n");
			e.printStackTrace();
		} /*finally{
			try {
				if(is != null){
					is.close();
				}
				if(out != null){
					out.close();
				}
			} catch (IOException e) {
				System.err.println("\n\tFailed to close something.\n");
				e.printStackTrace();
			}
		}*/
	}
}
