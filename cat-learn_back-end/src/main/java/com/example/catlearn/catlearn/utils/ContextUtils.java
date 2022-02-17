package com.example.catlearn.catlearn.utils;

public class ContextUtils {
	
	
	private final String CAT_LEARN_PATH = "/Users/luizfelipeciochetti/cat_learn_files/";
	
	private final String COURSE = "course/";
	
	private static ContextUtils contextUtils = new ContextUtils();
	

	
	private ContextUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static ContextUtils getInstance(){
		return contextUtils;
	}
	
	
	public String getPathFilesAttachedsCatLearnCourse() {
		return CAT_LEARN_PATH + COURSE ;
		
	}
	
	
}
