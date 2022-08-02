package com.test.ju.domain.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.ju.domain.entity.VisualFile;


@Repository
public interface VisualFileRepository extends JpaRepository<VisualFile, Long>{

	List<VisualFile> findAllByIsShowOrderByNum(boolean isShow);
	
//	//마지막번호 //아 실제로 현재 등록되어있는 순서를 알기 위해서.. 삭제되고 생기고 막 이케저케 되던거 남아있는 애들 순서
//	@Query("select max(v.num) from VisualFile v")
//	long getLastNum();


	


}
