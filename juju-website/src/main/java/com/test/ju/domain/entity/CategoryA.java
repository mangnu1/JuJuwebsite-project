package com.test.ju.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategoryA {
	SPECIAL("스페셜",1100,"special"),
	BEANS("원두",1200,"beans"),
	TEA("차",1300,"tea"),
	PRODUCT("상품",1600,"product"),
	DRINK("음료",1400,"drink"),
	FOOD("음식",1500,"food");

	final String koName;
	final long code;
	final String lower;
}
