package com.test.ju.domain.entity;

import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.test.ju.domain.dto.review.ReviewUpdateDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Review extends BaseTimeEntity{
	
	@Column(name = "review_no")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
	@Column(nullable = false)
	private String title;
	
	@Column(columnDefinition = "text not null")
	private String content;

	@Column(nullable = false)
	private String writer;
	
	@Column(nullable = false)
	private int ReadCount;
	
//	@JoinColumn(name = "memberNo")
//	@ManyToOne(fetch = FetchType.LAZY)
//	private Member member;
	
	@Builder.Default
	@OneToMany(mappedBy = "review")
	private List<FileEntity> files=new Vector<FileEntity>();
	
	@Builder.Default
	@OneToMany(mappedBy = "review")
	private List<ReplyEntity> replies=new Vector<ReplyEntity>();
	
	public Review update(ReviewUpdateDTO dto) {
		this.title=dto.getTitle();
		this.content=dto.getContent();
		return this;
	}
	
}
