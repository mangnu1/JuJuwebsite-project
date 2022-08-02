package com.test.ju.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.test.ju.domain.dto.items.CategoryDTO;
import com.test.ju.domain.dto.items.ItemInsertDTO;
import com.test.ju.domain.dto.items.ItemsDetailDTO;
import com.test.ju.domain.dto.items.ItemsListDTO;
import com.test.ju.domain.entity.CategoryA;
import com.test.ju.domain.entity.ItemsCategory;
import com.test.ju.domain.entity.ItemsEntity;
import com.test.ju.domain.entity.ItemsFile;
import com.test.ju.domain.repository.ItemsCategoryRepository;
import com.test.ju.domain.repository.ItemsRepository;
import com.test.ju.domain.repository.ReviewRepository;
import com.test.ju.service.ItemService;

@Service
public class ItemServiceProc implements ItemService {

	@Autowired
	private ItemsRepository repository;
	private ReviewRepository reviewRepository;
	@Autowired
	ItemsCategoryRepository categoryRepository;
	
	
	
	
	
	
	@Override
	public void itemsListByCaNo(long caNo, Model model) {
		
		//상위 카테고리인지 하위카테고리인지 구분 가능
		if(caNo%100==0) {//상위카테고리 이면
			model.addAttribute("list",  repository.findAllByCategorysCaNoBetween(caNo, caNo+99).stream()
					.map(ItemsListDTO::new)
					.collect(Collectors.toList()));
			//2가지 방법이 있음 위에는 숫자코드로 찾아보는 방법  아래는 문자코드로 비교해서찾아 오는방법 
			for(CategoryA catea : CategoryA.values()) {
				if(catea.getCode()==caNo) {
					model.addAttribute("catea", catea);
				}
			}
			return;//메서드 종료// 아래문장 실행하지 않습니다
		}
		
		//하우 ㅣ카테고리인 경우 
		CategoryDTO cateInfo=categoryRepository.findById(caNo).map(CategoryDTO::new).get();
		model.addAttribute("cateInfo", cateInfo);
				
		
		model.addAttribute("list",
				repository.findAllByCategorysCaNo(caNo).stream()
				.map(ItemsListDTO::new)
				.collect(Collectors.toList()));
		
	}

	//카테고리별 상품 목록
	@Override
	public void itemsListByCategory(long caNo, Model model) {
		
		model.addAttribute("list",
								repository.findAllByCategorysCaNoBetween(caNo, caNo+99).stream()
								.map(ItemsListDTO::new)
								.collect(Collectors.toList()));//itemsentity에서 categrys를 통해서 접근
		
	}
	
	@Override
	public String tempFileupload(MultipartFile file) {
		
		String path="/images/items/temp/";//시작부터 닫는거 까지! 물리경로 //여기 경로 파일이 업로드됨
		ClassPathResource cpr=new ClassPathResource("static"+path);
		
		try {
			File location=cpr.getFile();
			//폴더명이 아니라 파일명이다??
			File targetFile=new File(location, file.getOriginalFilename());//파일명과 url분리중? parents와 child
			//파일 업로드 경로와 파일 이름
			file.transferTo(targetFile);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path+file.getOriginalFilename(); // /images/goods/temp/파일이름.jpg
		//이 return 값은 다시 controller로 간다. 
	}
	
	
	
	@Override
	public String save(ItemInsertDTO dto) {
		System.out.println("서비스 : " + dto.getDefImgName());
		System.out.println("서비스 : " +dto.getAddImgName());
		//서버에
		String def=dto.getDefImgName();
		String add=dto.getAddImgName();
		String path="/images/items/temp/";
		ClassPathResource cpr=new ClassPathResource("static"+path);
		
		ItemsEntity entity=dto.toEntity();//itemsentity
		
		
		try {
			File root=cpr.getFile();
			//File defFile=new File(root, def);
			//ClassPathResource target=new ClassPathResource("static"+"/images/Items/");
			
			/////////////////////////////////////////////////////////////////
			File defFile=new File(root, def);
			defFile.renameTo(new File(root.getParent(), def));
			String name=defFile.getName();
			//System.out.println(name);
			long size=defFile.length();
			//System.out.println(size);
			
			ItemsFile defItemsFile= ItemsFile.builder()
			.newName(name).orgName(name).size(size).isDefImg(true).url("/images/items/")//true=1//대표 이미지
			.build();
			
			////////////////////////////////////////////////////////////////////
			
			File addFile=new File(root, add);
			addFile.renameTo(new File(root.getParent(), add));
			String name2 = addFile.getName();
			//System.out.println(name);
			long size2 = addFile.length();
			//System.out.println(size);
			
			ItemsFile addItemsFile= ItemsFile.builder()
			.newName(name2).orgName(name2).size(size2).isDefImg(false).url("/images/items/")//false=0//대표 이미지
			.build();
			
			//파일 추가
			entity
			.addFile(defItemsFile)
			.addFile(addItemsFile);
			
			
			//다중 카테고리 추가
			for(long cano: dto.getCaNo()) {//카테고리 번호 배열 안에 카테고리 번호 뽑아오기
				entity.addCategory(categoryRepository.findById(cano).get());
				
			}
			
			repository.save(entity);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:items";// 뒤에 /가 안붙어져서 그 뒷부분 사라지고 items로 감
	}

	@Override
	public String list(Model model) {
		//<List<ItemsEntity> -> List<ItemsListDTO>
				List<ItemsListDTO> result=repository.findAll().stream()
						.map(ItemsListDTO::new)
						.collect(Collectors.toList());
				
				model.addAttribute("list", result);
				
				return "admin/items/list";
	}

	@Override
	public String indexList(Model model) {
		//<List<ItemsEntity> -> List<ItemsListDTO>
		List<ItemsListDTO> result=repository.findAll().stream()
				.map(ItemsListDTO::new)
				.collect(Collectors.toList());
		
		model.addAttribute("list", result);
		
		return "admin/items/list-data";
	}

	@Override
	public String detail(long ino, Model model) {
		model.addAttribute("detail", repository.findById(ino).map(ItemsDetailDTO::new).get());
		return "admin/items/detail";
	}
	
	
	
	@Override
	public List<CategoryDTO> categoryList(long caNo) {
		//categoryRepository.findByNoBetween(caNo, caNo+99); //시작값과 끝값 Between 1100-> 1101~1199
		
		
//		//2가지 방법이 있음 위에는 숫자코드로 찾아보는 방법  아래는 문자코드로 비교해서찾아 오는방법 
//		for(CategoryA catea : CategoryA.values()) {
//			if(catea.getCode()==caNo) {
//				categoryRepository.findByCateA(catea);
//			}
//		}
		return categoryRepository.findByCaNoBetween(caNo, caNo+99)
				.stream().map(CategoryDTO::new)
				.collect(Collectors.toList());
	}


	
	
	

}
