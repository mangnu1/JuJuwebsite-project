package com.test.ju.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Division {

	USE("사이트 이용","uses"),
	NOTICE("공지사항", "notice"),
	QNA("질문", "qna"),
	
	
	POINT("포인트","points"),
	MEMBER("회원","members"),
	SHIP("멤버십","ships"),
	ONLINE("온라인","onlines"),
	BENEFIT("할인혜택","benefits"),
	TICKET("관람권","ticket"),
	STORE("스토어","store"),
	SPECIAL("스페셜관","special");
	
	final String title;
	final String url;
	
	
	
}
