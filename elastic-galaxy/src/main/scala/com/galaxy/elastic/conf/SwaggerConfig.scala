package com.galaxy.elastic.conf

import org.springframework.context.annotation.{Bean, Configuration}
import springfox.documentation.builders.{ApiInfoBuilder, PathSelectors, RequestHandlerSelectors}
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
  * Created by wangpeng
  * Date: 2018/8/3
  * Time: 11:47
  */
@Configuration
@EnableSwagger2
class SwaggerConfig {
  @Bean
  def createRestApi: Docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo).select.apis(RequestHandlerSelectors.basePackage("com.galaxy.elastic.controller")).paths(PathSelectors.any).build

  private def apiInfo = new ApiInfoBuilder().title("Elastic").description("Elastic-Galaxy API").version("1.0").build
}
