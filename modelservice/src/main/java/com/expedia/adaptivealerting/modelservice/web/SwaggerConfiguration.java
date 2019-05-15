/*
 * Copyright 2018-2019 Expedia Group, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.expedia.adaptivealerting.modelservice.web;

import static com.google.common.base.Predicates.or;

import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

import com.google.common.base.Predicate;

import com.fasterxml.classmate.TypeResolver;

import lombok.Generated;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@EnableSwagger2
@Configuration
@Generated //(exclude from code coverage)
@SuppressWarnings("unused")
public class SwaggerConfiguration {

    @Value("${swagger.service.version}")
    private String serviceVersion;

    @Value("${swagger.service.title}")
    private String serviceTitle;

    @Value("${swagger.service.description}")
    private String serviceDescription;

    @Value("${swagger.service.termsPath}")
    private String serviceTermsPath;

    @Value("${swagger.service.contact.email}")
    private String serviceEmail;

    @Value("${swagger.service.licenceType}")
    private String serviceLicenceType;

    @Value("${swagger.service.licencePath}")
    private String serviceLicencePath;

    private TypeResolver typeResolver;

    @Value("${swagger.service.contact.name}")
    private String contactName;

    @Value("${swagger.service.contact.email}")
    private String contactEmail;

    /**
     * @return Returns a Swagger 2.0 Specification for the current application
     */
    @Bean
    @SuppressWarnings("javadoc")
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(paths())
                .build()
                .apiInfo(apiInfo())
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class,
                        String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(
                        newRule(typeResolver.resolve(DeferredResult.class,
                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                                typeResolver.resolve(WildcardType.class))
                )
                .tags(new Tag("default", "Basic endpoints for adaptive-alerting-detector-mapper"))
                .useDefaultResponseMessages(false);
    }

    private Predicate<String> paths() {
        return or(
                // regex("/additional_endpoints/.*?"),
                regex("/api/.*?")
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(serviceTitle)
                .description(serviceDescription)
                .version(serviceVersion)
                .termsOfServiceUrl(serviceTermsPath)
                .contact(new Contact(contactName, "", contactEmail))
                .license(serviceLicenceType)
                .licenseUrl(serviceLicencePath)
                .build();
    }

    @Bean
    UiConfiguration uiConfig() {
        // To turn off Swagger UI validation
        return new UiConfiguration(null);
    }

    @Autowired
    public void setTypeResolver(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }
}