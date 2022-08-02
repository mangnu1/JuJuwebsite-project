package com.test.ju.domain.entity;

import lombok.Builder;
import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class NoticeEntity {
	
	@Column
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long noticeNo;
	
	@Column(nullable = false)
	private String title;
	
	@Column(columnDefinition = "text not null")
	private String content;

	@Column(nullable = false)
	private String writer;
	
	@Column(nullable = false)
	private int readCount;
	
	@Column(name = "divs")
	@Enumerated(EnumType.STRING)
	private Division division;
	
	@Builder.Default
	@JoinColumn(name="noticeNo")//fk이름 설정 가능 :// 설정 안하면 default: ItemsFileEntity_itemNo
	//fk 이름 설정 다쪽(N) entity에 생성 files_ino ??
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<NoticeFile> files=new ArrayList<>();
	
	public NoticeEntity addFile(NoticeFile file) {
		files.add(file);
		return this;
	}
	
	
}
