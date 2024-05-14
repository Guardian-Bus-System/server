//package com.gachi.gb.config
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.web.context.support.RequestHandledEvent
//import springfox.documentation.builders.ApiInfoBuilder
//import springfox.documentation.builders.PathSelectors
//import springfox.documentation.builders.RequestHandlerSelectors
//import springfox.documentation.service.ApiInfo
//import springfox.documentation.spi.DocumentationType
//import springfox.documentation.spring.web.plugins.Docket
//import springfox.documentation.swagger2.annotations.EnableSwagger2
//
//@Configuration
////@EnableSwagger2
//class SwaggerConfiguration {
//  @Bean
//  fun api(): Docket {
//    return Docket(DocumentationType.OAS_30)
//      .useDefaultResponseMessages(false) //기본 응답 메세지를 사용하지 않기로 설정
//      .select()
//      .apis(RequestHandlerSelectors.any())//해당 패키지에 있는 controller만 문서화
//      .paths(PathSelectors.any()) //위 패키지의 모든 경로를 문서화로 결정
//      .build()//설정 완료
//      .apiInfo(apiInfo()) //api 정보 설정
//  }
//
//  fun apiInfo(): ApiInfo {
//    return ApiInfoBuilder()
//      .title("GoBus API 문서") //문서의 제목 설정
//      .description("경소고 버스 백엔드 API") //문서의 설명 설정
//      .version("1.0")
//      .build()
//  }
//}