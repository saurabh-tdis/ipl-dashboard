//package com.app.ipl.config;
//
//import lombok.Builder;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.StringUtils;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
///**
// * @Author saurabh vaish
// * @Date 05-07-2022
// */
//
////@EnableSwagger2
//@Configuration
//public class SwaggerConfig  {
////    private final String baseUrl;
////
////    public SwaggerConfig() {
////        this.baseUrl = baseUrl;
////    }
////
////    @Override
////    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        String baseUrl = StringUtils.trimTrailingCharacter(this.baseUrl, '/');
////        registry.
////                addResourceHandler(baseUrl + "/swagger-ui/**")
////                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
////                .resourceChain(false);
////    }
////
////    @Override
////    public void addViewControllers(ViewControllerRegistry registry) {
////        registry.addViewController(baseUrl + "/swagger-ui/")
////                .setViewName("forward:" + baseUrl + "/swagger-ui/index.html");
////    }
//
//    @Builder
//    public Docket swagger(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.app.ipl.controller"))
//                .paths(PathSelectors.regex("/api/*"))
//                .build()
//                .useDefaultResponseMessages(true)
//                .apiInfo(new ApiInfoBuilder().title("Ipl Dashboard").description("ipl dashboard api").version("1.0").build());
//    }
//}
