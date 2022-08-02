package com.test.ju;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.ju.domain.entity.CategoryA;
import com.test.ju.domain.entity.ItemsCategory;
import com.test.ju.domain.entity.ItemsEntity;
import com.test.ju.domain.repository.ItemsCategoryRepository;
import com.test.ju.domain.repository.ItemsEntityRepository;

@SpringBootTest
class JujuWebsiteApplicationTests {
	
	@Autowired
	ItemsCategoryRepository itemsCategoryRepository;
	
	@Autowired
	ItemsEntityRepository itemsEntityRepository;


//	@Commit
//	@Transactional
//	@Test
	void 상품_카테고리_연결() {
		ItemsEntity entity=	itemsEntityRepository.findById(1L).get();//1번 아이템 찾아
		ItemsCategory category=itemsCategoryRepository.findById(1101L).get();//1101번 찾아
		
		itemsEntityRepository.save(entity.addCategory(category));//편의메서드 통해서 아이템엔티티에 카테고리 넣어줘
		
//		itemsEntityRepository.save(entity.addCategory(ItemsCategory.builder()
//				.caNo(1101)
//				.build()));
		
//		itemsEntityRepository.save(ItemsEntity.builder().ino(1)
//				.build().addCategory(ItemsCategory.builder()
//						.caNo(1101)
//						.build()));
		
	}
	
	
	//@Test
	void 카테고리_생성() {
		CategoryA cateA=CategoryA.FOOD;
		long count=itemsCategoryRepository.countByCateA(cateA);
		
		System.out.println("count : " +count);
		
		itemsCategoryRepository.save(ItemsCategory.builder()
				.name("샌드위치 & 샐러드")
				.code(++count)
				.cateA(cateA)
				.build().createNo());
		
	}
	
	
	

}
