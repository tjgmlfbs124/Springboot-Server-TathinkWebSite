package com.tathink.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;

import com.tathink.TathinkApplication;

public class MyFileUtil {
	
	private static MyFileUtil self = null;
	private String rootPath = null;
	public static MyFileUtil getInstance(HttpServletRequest request)
	{
		if(self == null)
		{
			self = new MyFileUtil(request);
		}
		
		return self;
	}
	
	public MyFileUtil(HttpServletRequest request)
	{
		rootPath = request.getSession().getServletContext().getRealPath("/");
	}
	
	public String getRootPath()
	{
		return rootPath;
	}
	public void fileOut(HttpServletRequest request, HttpServletResponse response, String filePath, String fileLogicName)
	{
		String fileName = null;
		File file = null;
		
		try
		{    
			file = new File(filePath);
         
	        response.setContentType(request.getContentType());
	        response.setContentLength((int) file.length());
        
	        fileName = URLEncoder.encode(fileLogicName, "utf-8");
 
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
	        response.setHeader("Content-Transfer-Encoding", "binary");
	        OutputStream out = response.getOutputStream();
 
	        FileInputStream fis = null;
 
	        try 
	        {
	            fis = new FileInputStream(file);
	            FileCopyUtils.copy(fis, out);
	        } 
	        finally 
	        {
	        	if(fis != null)
	        	{
	        		try 
	        		{
	        			fis.close();
	        		} 
	        		catch(IOException ex) 
	        		{
	        		}
	        	}
	        	out.flush();

	        } 
		}
	    catch (Exception e) 
	    {
	    	// TODO Auto-generated catch block
	       	e.printStackTrace();
	    }
		
		return;
	}
	
	public void zipFileOut(HttpServletRequest request, HttpServletResponse response, List<String> fileList)
	{
		
		List<String> zipFileList = new ArrayList<String>();
		String zipFileName = "homework.zip";
		
		for(int i=0;i<fileList.size();i++){
			//File tempFile = new File(MyFileUtil.getInstance(request).getRootPath() + fileList.get(i));			
			//String filePath = tempFile.getParent();
			String filePath = MyFileUtil.getInstance(request).getRootPath() + fileList.get(i);
			zipFileList.add(filePath);
		}		
		
		try
		{   
			File zipFile = new File(rootPath + TathinkApplication.classReportFileRealPath + zipFileName);
			// ZipOutputStream을 FileOutputStream 으로 감쌈
		    FileOutputStream fout = new FileOutputStream(zipFile);
		    ZipOutputStream zout = new ZipOutputStream(fout);

		    for(int i=0; i < zipFileList.size(); i++){

		        //본래 파일명 유지, 경로제외 파일압축을 위해 new File로 
		        //ZipEntry zipEntry = new ZipEntry(new File(zipFileList.get(i)).getName());
		        //zout.putNextEntry(zipEntry);
		        //경로포함 압축
		    	
		    	int idx = zipFileList.get(i).lastIndexOf("/");		    	
		    	zout.putNextEntry(new ZipEntry(zipFileList.get(i).substring(idx-2)));

		        FileInputStream fin = new FileInputStream(zipFileList.get(i));
		        byte[] buffer = new byte[1024];
		        int length;

		        // input file을 1024바이트로 읽음, zip stream에 읽은 바이트를 씀
		        while((length = fin.read(buffer)) > 0){
		            zout.write(buffer, 0, length);
		        }

		        zout.closeEntry();
		        fin.close();
		    }
		    zout.close();
		    
		} catch(IOException ioe){
			ioe.printStackTrace();
			System.out.println(ioe.getMessage());
		}
		
		String fileName = null;
		File file = null;
		String filePath = rootPath + TathinkApplication.classReportFileRealPath + zipFileName;
		try
		{    
			file = new File(filePath);
         
	        response.setContentType(request.getContentType());
	        response.setContentLength((int) file.length());
        
	        fileName = URLEncoder.encode(zipFileName, "utf-8");
 
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
	        response.setHeader("Content-Transfer-Encoding", "binary");
	        OutputStream out = response.getOutputStream();
 
	        FileInputStream fis = null;
 
	        try 
	        {
	            fis = new FileInputStream(file);
	            FileCopyUtils.copy(fis, out);
	        } 
	        finally 
	        {
	        	if(fis != null)
	        	{
	        		try 
	        		{
	        			fis.close();
	        		} 
	        		catch(IOException ex) 
	        		{
	        		}
	        	}
	        	out.flush();

	        } 
		}
	    catch (Exception e) 
	    {
	    	// TODO Auto-generated catch block
	       	e.printStackTrace();
	    }
		
		return;
	}
	
	public void zipfunction(){
		String zipFileName = "c:/file.zip";
		 
		String[] files = new String[3];
		 
		files[0] = "c:/a.so";
		files[1] = "c:/b.dat";
		files[2] = "c:/c.cfg";
		        
		byte[] buf = new byte[4096];
		 
		try {
		    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
		 
		    for (int i=0; i<files.length; i++) {
		        FileInputStream in = new FileInputStream(files[i]);
		        Path p = Paths.get(files[i]);
		        String fileName = p.getFileName().toString();
		                
		        ZipEntry ze = new ZipEntry(fileName);
		        out.putNextEntry(ze);
		          
		        int len;
		        while ((len = in.read(buf)) > 0) {
		            out.write(buf, 0, len);
		        }
		          
		        out.closeEntry();
		        in.close();
		 
		    }
		          
		    out.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}	
	}	
}
