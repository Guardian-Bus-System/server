package com.gachi.gb.common.annotation

import org.springframework.security.core.annotation.AuthenticationPrincipal

//유지
@Retention(AnnotationRetention.RUNTIME) //유지됨 -> 런타임까지 Annotation이 유지가 됨
//어노테이션이 지정될 대상을 선택
@Target(AnnotationTarget.VALUE_PARAMETER) //함수 또는 생성자의 값 매개변수에 적용할 수 있음을 의미
@AuthenticationPrincipal(expression = "#this != 'anonymousUser' ? #this : null ")
//expression = SpEL의 표현식을 사용, 현재 인증된 사용자의 정보를 처리하는 방법을 지정
//이때 this는 사용자고 anonymousUser는 익명의 사용자기 때문에 익명의 사용자면 Null을 넘겨준다.


annotation class AuthUserId
