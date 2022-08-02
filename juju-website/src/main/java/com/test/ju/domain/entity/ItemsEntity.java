package com.test.ju.domain.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.test.ju.domain.constant.Color;
import com.test.ju.domain.constant.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class ItemsEntity extends BaseTimeEntity{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ino;
	
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private int price;// 판매가 가격, 정가
	@Column(nullable = false)
	private int sale;//할인가 or 할인율
	@Column(nullable = false)
	private int stock;//재고
	
	@Column(columnDefinition = "text", nullable = false)
	private String content;
	
	
	//색상//커피 머그컵
	@Builder.Default
	@Enumerated(EnumType.STRING)
	//@CollectionTable(name="colors")
	@ElementCollection
	private Set<Color> colors=new HashSet<>();
	
	//사이즈
	@Builder.Default
	@Enumerated(EnumType.STRING)
	//@CollectionTable(name="size")
	@ElementCollection
	private Set<Size> size=new HashSet<>();//원두 사이즈 몇백그램씩
	
	//1:N 단방향설정 연관테이블 생성시키지 않기 위해 @JoinColumn 해주어야 한다
	@Builder.Default
	@JoinColumn(name="ino")//fk이름 설정 가능 :// 설정 안하면 default: ItemsFileEntity_itemNo
	//fk 이름 설정 다쪽(N) entity에 생성 files_ino ??
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<ItemsFile> files=new ArrayList<>();
	
	public ItemsEntity addFile(ItemsFile file) {
		files.add(file);
		return this;
	}
	
	@Builder.Default
	@JoinColumn(name="caNo")//categorys_ca_no
	@ManyToMany(cascade = CascadeType.ALL)
	Set<ItemsCategory> categorys=new HashSet<>();
	
	public ItemsEntity addCategory(ItemsCategory category) {
		categorys.add(category);
		return this;
	}
	
	
//	@Builder.Default
//	@OneToMany(mappedBy = "item")
//	List<Cart> carts=new Vector<Cart>(); 
//	
//	@Builder.Default
//	@OneToMany(mappedBy = "item")
//	List<CategoryItem> categoryItems=new Vector<CategoryItem>();
	

}
