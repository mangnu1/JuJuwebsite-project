package com.test.ju.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.test.ju.domain.dto.FileData;
import com.test.ju.domain.dto.visual.VisualInsertDTO;
import com.test.ju.domain.dto.visual.VisualListDTO;
import com.test.ju.domain.dto.visual.VisualUpdateDTO;
import com.test.ju.domain.entity.VisualFile;
import com.test.ju.domain.repository.VisualFileRepository;
import com.test.ju.service.VisualService;
import com.test.ju.utils.MyFileUtils;

@Service
public class VisualServiceProc implements VisualService {

	@Autowired
	private VisualFileRepository visualFileRepository;

	@Override
	public String save(MultipartFile vimg, VisualInsertDTO dto) {
		
		//파일업로드
		String url="/images/visual/";//업로드할 서버위치
		FileData fileData=MyFileUtils.upload(vimg, url);
		dto.addFileData(fileData);
		//db저장
//		long lastNum=visualFileRepository.getLastNum();
//		dto.setNum(++lastNum);
		
		visualFileRepository.save(dto.toVisualFile());
		return "redirect:/admin/visuals";
	}

	@Override
	public String list(Model model) {
		model.addAttribute("list", visualFileRepository.findAll()
				.stream().map(VisualListDTO::new).collect(Collectors.toList()));
		
		//long lastNum=visualFileRepository.getLastNum();
		//System.out.println(lastNum);
		
		return "/admin/visual/list";
	}
	
	
	@Override
	public String indexList(Model model) {
		model.addAttribute("list", visualFileRepository.findAllByIsShowOrderByNum(true)
				.stream().map(VisualListDTO::new).collect(Collectors.toList()));
		return "/visual/list";//
	}

	@Override
	public boolean updateIsShow(long vno, boolean isShow) {
		
		Optional<VisualFile> result=visualFileRepository.findById(vno);
		if(result.isEmpty())return false;
		visualFileRepository.save(result.get().updateIsShow(isShow));
		return true;
	}

	@Override
	public boolean updateTitle(long vno, String title) {
		Optional<VisualFile> result=visualFileRepository.findById(vno);
		if(result.isEmpty())return false;
		visualFileRepository.save(result.get().updateTitle(title));
		return true;
	}

	@Override
	public boolean updateSub(long vno, String sub) {
		Optional<VisualFile> result=visualFileRepository.findById(vno);
		if(result.isEmpty())return false;
		visualFileRepository.save(result.get().updateSub(sub));
		return true;
	}

	@Override
	public boolean updateData(long vno, VisualUpdateDTO dto) {
		Optional<VisualFile> result=visualFileRepository.findById(vno);
		if(result.isEmpty())return false;
		
		visualFileRepository.save(result.get().updateData(dto));
		return true;
	}

	@Override
	public boolean delete(long vno) {
		visualFileRepository.deleteById(vno);
		//파일도 삭제
		
		return true;
	}
	
	

}
