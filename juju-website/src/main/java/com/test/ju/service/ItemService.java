package com.test.ju.service;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.test.ju.domain.dto.items.CategoryDTO;
import com.test.ju.domain.dto.items.ItemInsertDTO;

public interface ItemService {

	String save(ItemInsertDTO dto);

	String tempFileupload(MultipartFile file);

	String list(Model model);

	String indexList(Model model);

	String detail(long ino, Model model);

	List<CategoryDTO> categoryList(long caNo);

	void itemsListByCategory(long caNo, Model model);

	void itemsListByCaNo(long caNo, Model model);



}
