package com.test.ju.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.test.ju.domain.dto.customer.FaqListDTO;
import com.test.ju.domain.dto.customer.NoticeInsertDTO;
import com.test.ju.domain.entity.Division;
import com.test.ju.domain.entity.ItemsEntity;
import com.test.ju.domain.entity.ItemsFile;
import com.test.ju.domain.entity.NoticeEntity;
import com.test.ju.domain.entity.NoticeFile;
import com.test.ju.domain.repository.FaqEntityRepository;
import com.test.ju.domain.repository.NoticeEntityRepository;
import com.test.ju.service.CustomerService;


@Service
public class CustomerServiceProc implements CustomerService {

	@Autowired
	private NoticeEntityRepository noticeEntityRepository;
	
	@Autowired
	private NoticeEntityRepository repository;
	
	@Override
	public void list(Model model, String div) {
		Division division = null;
		Division[] divs=Division.values();
		
		for(Division di :divs) {
			if(div.equals(di.getUrl())) division=di; 
		}
		
		model.addAttribute("list",   repository.findAllByDivision(division).stream()
				.map(FaqListDTO::new)
				.collect(Collectors.toList()) );
		
	}
	
	
	@Override
	public void list(Model model, int divno) {
		Division division = null;
		Division[] divs=Division.values();
		
		for(Division di :divs) {
			if(divno==di.ordinal())division=di; 
		}
		model.addAttribute("divno", divno);
		model.addAttribute("list",   repository.findAllByDivision(division).stream()
				.map(FaqListDTO::new)
				.collect(Collectors.toList()) );
		
	}

	
	@Override
	public ModelAndView list(ModelAndView mv, int divno) {
		Division division = null;
		Division[] divs=Division.values();
		
		for(Division di :divs) {
			if(divno==di.ordinal())division=di; 
		}
		
		mv.addObject("list",   repository.findAllByDivision(division).stream()
				.map(FaqListDTO::new)
				.collect(Collectors.toList()) );
		mv.setViewName("/cus/faq/list");
		return mv;
	}


	@Override
	public String save(int divno, NoticeInsertDTO dto) {
		System.out.println(dto);
		//?????????
		String def=dto.getDefImgName();
		String add=dto.getAddImgName();
		String path="/images/cus/temp/";
		ClassPathResource cpr=new ClassPathResource("static"+path);
		
		// NoticeEntity entity=dto.toEntity();//NoticeEntity
		
		
		try {
			File root=cpr.getFile();
			//File defFile=new File(root, def);
			//ClassPathResource target=new ClassPathResource("static"+"/images/cus/");
			
			/////////////////////////////////////////////////////////////////
			File defFile=new File(root, def);
			defFile.renameTo(new File(root.getParent(), def));
			String name=defFile.getName();
			//System.out.println(name);
			long size=defFile.length();
			//System.out.println(size);
			
			NoticeFile defNoticeFile= NoticeFile.builder()
			.newName(name).orgName(name).size(size).isDefImg(true).url("/images/cus/")//??? true
			.build();
			
			////////////////////////////////////////////////////////////////////
			
			File addFile=new File(root, add);
			addFile.renameTo(new File(root.getParent(), add));
			name=addFile.getName();
			//System.out.println(name);
			size=addFile.length();
			//System.out.println(size);
			
			NoticeFile addNoticeFile= NoticeFile.builder()
			.newName(name).orgName(name).size(size).isDefImg(false).url("/images/cus/")//??? true
			.build();
			
			if(divno==0) {
				dto.setDivision(Division.USE);
			}else if(divno==1) {
				dto.setDivision(Division.NOTICE);
			}else {
				dto.setDivision(Division.QNA);
			}
			
			
			noticeEntityRepository.save(dto.toEntity()
						.addFile(defNoticeFile)
						.addFile(addNoticeFile));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "cus/faq/index";// ?????? /??? ??????????????? ??? ????????? ???????????? ??? ???
	
	}


	@Override
	public String tempFileupload(MultipartFile file) {
		
		String path="/images/cus/temp/";//???????????? ????????? ??????! ???????????? //?????? ?????? ????????? ????????????
		ClassPathResource cpr=new ClassPathResource("static"+path);
		
		try {
			File location=cpr.getFile();
			//???????????? ????????? ?????????????????
			File targetFile=new File(location, file.getOriginalFilename());//???????????? url?????????? parents??? child
			//?????? ????????? ????????? ?????? ??????
			file.transferTo(targetFile);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path+file.getOriginalFilename(); // /images/cus/notice/tmep/????????????.jpg
		//??? return ?????? ?????? controller??? ??????. 
	}

	
	
}
