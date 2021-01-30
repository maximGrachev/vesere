package ru.godsonpeya.vesereapi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@Configuration
class SwaggerConfig {
    @Bean
    fun productApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.godsonpeya.vesereapi"))
                .paths(PathSelectors.regex("/api.*"))
                .build()
                .apiInfo(metaInfo())
    }

    private fun metaInfo(): ApiInfo {
        return ApiInfoBuilder()
                .description("Идея приложения заключается в записи всех ремонтных работ, связанных с автомобилем, а также напоминанием о предстоящих событиях. На данный момент все события хранятся в базе данных прямо на мобильном устройстве. В идеале, конечно, хранить все на сервере.")
                .title("Vesere")
                .version("0.0.1")
                .build()
    }
}
