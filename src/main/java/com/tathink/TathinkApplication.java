package com.tathink;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class TathinkApplication extends SpringBootServletInitializer implements WebMvcConfigurer {
		
	public static String memberImageRealPath = "/extResource/images/member/";
	public static String memberImageUrl = "/images/member/";
	public static String noticeFileRealPath = "/extResource/files/notice/";
	public static String noticeFileUrl = "/files/notice/";
	public static String classNoticeFileRealPath = "/extResource/files/classNotice/";
	public static String classNoticeFileUrl = "/files/classNotice/";
	public static String classHomeworkFileRealPath = "/extResource/files/classHomework/";
	public static String classHomeworkFileUrl = "/files/classHomework/";
	public static String classReportFileRealPath = "/extResource/files/classReport/";
	public static String classReportFileUrl = "/files/classReport/";	
	public static String classFileFileRealPath = "/extResource/files/classFile/";
	public static String classFileFileUrl = "/files/classFile/";
	public static String eventImageRealPath = "/extResource/images/event/";
	public static String eventImageUrl = "/images/event/";
	public static String courseImageRealPath = "/extResource/images/course/";
	public static String courseImageUrl = "/images/course/";
	public static String kitImageRealPath = "/extResource/images/kit/";
	public static String kitImageUrl = "/images/kit/";
	
	public static void main(String[] args) {
		SpringApplication.run(TathinkApplication.class, args);
	}
	
	@Override public void addResourceHandlers(ResourceHandlerRegistry registry) { 
		registry.addResourceHandler(memberImageUrl+"**").addResourceLocations(memberImageRealPath); 
		registry.addResourceHandler(noticeFileUrl+"**").addResourceLocations(noticeFileRealPath);
		registry.addResourceHandler(classNoticeFileUrl+"**").addResourceLocations(classNoticeFileRealPath);
		registry.addResourceHandler(classHomeworkFileUrl+"**").addResourceLocations(classHomeworkFileRealPath);
		registry.addResourceHandler(classReportFileUrl+"**").addResourceLocations(classReportFileRealPath);
		registry.addResourceHandler(classFileFileUrl+"**").addResourceLocations(classFileFileRealPath);
		registry.addResourceHandler(eventImageUrl+"**").addResourceLocations(eventImageRealPath);
		registry.addResourceHandler(courseImageUrl+"**").addResourceLocations(courseImageRealPath);
		registry.addResourceHandler(kitImageUrl+"**").addResourceLocations(kitImageRealPath);
	}
	
	@Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new MemberHandlerMethodArgumentResolver());
    }	
}
