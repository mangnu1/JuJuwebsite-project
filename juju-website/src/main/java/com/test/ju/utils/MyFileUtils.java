package com.test.ju.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import com.test.ju.domain.dto.FileData;


public class MyFileUtils {
	
ApplicationContext ac;
	
	public static FileData upload(MultipartFile mulitpartfile, String url) {
		
		String orgName=mulitpartfile.getOriginalFilename();
		String newName = null;
		long size=mulitpartfile.getSize();
		
		//ClassPathResource cpr=(ClassPathResource) new DefaultResourceLoader().getResource("static"+url);//"static/images/visual/""
		ClassPathResource cpr=new ClassPathResource("static"+url);//"static/images/visual/""
		
		try {
			File location=cpr.getFile();
			mulitpartfile.transferTo(new File(location,orgName));
			//log.info(">>>파일 업로드 완료!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return FileData.builder()
				.url(url).orgName(orgName).size(size).newName(newName)
				.build();
	}
	
}
