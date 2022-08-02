package com.test.ju.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.test.ju.domain.dto.items.ItemInsertDTO;
import com.test.ju.domain.entity.CategoryA;
import com.test.ju.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService service;
	

	@GetMapping("/common/categorys/{caNo}/items")//2차 카테고리
	public String itemsListByCano(@PathVariable long caNo, Model model) {
		service.itemsListByCaNo(caNo, model);
		return "items/list";
	}
	
	
	
	
	
	//ajax로 요청
	//@ResponseBody//성공시 결과값으로 return값을 body로 보내줌 html에
	@GetMapping("/admin/category/{caNo}")
	public String category(@PathVariable long caNo , Model model) {
		model.addAttribute("option", service.categoryList(caNo));
		return "admin/items/category-data";
	}
	
	
	//상품 등록 페이지 이동
		@GetMapping("/admin/items/write")
		public String items(Model model) {
			model.addAttribute("cateA", CategoryA.values());
			for(CategoryA cate : CategoryA.values()) {
				System.out.println(cate.getKoName());
			}
			return "/admin/items/write";
		}
		
	
	
	//상품 파일 업로드
	@ResponseBody
	@PostMapping("/admin/items/fileupload")
	public String tempFileupload(MultipartFile file) {
		
		return service.tempFileupload(file);
	}
	
	
	//상품 정보 저장
	@PostMapping("/admin/items")
	public String itemsave(ItemInsertDTO dto) {
		
		return service.save(dto);
	}
	

	//상품 아이템 리스트
	@GetMapping("/admin/items")
	public String List(Model model) {
		return service.list(model);
	}
	
	//인덱스 리스트
	@GetMapping("/common/items")
	public String indexList(Model model) {
		return service.indexList(model);
	}
	
	
	
	//상품 디테일 페이지
	@GetMapping(path= "/common/items/{ino}")
	public String detail(@PathVariable long ino, Model model) {
		return service.detail(ino, model);
	}
	
	
	
	
	
	
}
