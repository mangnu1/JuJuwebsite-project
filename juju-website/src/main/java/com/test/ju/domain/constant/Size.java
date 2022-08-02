package com.test.ju.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Size {

	S("소"),
	M("중"),
	L("대");
	
	final String korSize;
	
	
}
