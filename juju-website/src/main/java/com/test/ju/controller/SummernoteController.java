package com.test.ju.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SummernoteController {
	
	@ResponseBody
	@PostMapping("/admin/uploadSummernoteImg")
	public String uploadSummernoteImg(MultipartFile file) {
		//서비스 안받고 여기서 만든다요?
		//System.out.println(file.getOriginalFilename());
		//만약에 업로드를 할건데엥..컨테인이 이미지라면
		if(!file.getContentType().contains("image")) return null;//이미지가 아니라면 종료 여기서 끝내라
		
		//System.out.println(file.getContentType());
		String url="/images/summernote/";
		ClassPathResource cpr=new ClassPathResource("static"+url);
		String orgName=file.getOriginalFilename();
		String saveName=UUID.randomUUID()+"_"+orgName;
		//System.out.println(saveName);
		
		try {
			File location=cpr.getFile();
			file.transferTo(new File(location, saveName));

			
		} catch (IOException e) {
				e.printStackTrace();
		}
		return url+saveName;
	}
	
}
