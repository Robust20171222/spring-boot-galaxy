package com.galaxy.scala.config

import org.springframework.context.annotation.{Bean, Configuration}
import springfox.documentation.builders.{ApiInfoBuilder, PathSelectors, RequestHandlerSelectors}
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.{UiConfiguration, UiConfigurationBuilder}
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
  * Swagger配置
  *
  * Created by wangpeng
  * Date: 2019-02-28
  * Time: 14:43
  */
@Configuration
@EnableSwagger2
class SwaggerConfig {

  def apiInfo: ApiInfo = {
    new ApiInfoBuilder()
      .title("Scala-galaxy REST API")
      .build()
  }

  @Bean
  def api: Docket = {
    new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.galaxy.scala")).paths(PathSelectors.any()).build().apiInfo(apiInfo)
  }

  @Bean
  def uiConfig: UiConfiguration = {
    UiConfigurationBuilder.builder().displayRequestDuration(true).validatorUrl("").build()
  }
}
