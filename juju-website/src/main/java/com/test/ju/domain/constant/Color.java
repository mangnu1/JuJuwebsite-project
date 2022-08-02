package com.test.ju.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Color {
	
	BLACK("검정"),
	WHITE("흰색");
	

	final String korColor;
	
	
}
