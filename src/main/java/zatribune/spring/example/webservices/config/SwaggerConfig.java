package zatribune.spring.example.webservices.config;


import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /*
      Swagger is an Web Services/Interface Description Language like WSDL in SOAP
      for describing RESTful APIs expressed using JSON.
      Swagger is used together with a set of open-source software tools to design,
      build, document, and use RESTful web services.
      Swagger includes automated documentation, code generation, and test-case generation.
    */
    @Autowired
    TypeResolver typeResolver;
    @Bean
    public Docket api() {
        //we can control to expose certain apis
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(metaData())
                .tags(new Tag("Customers", "Company's customers.")
                        , new Tag("Categories", "Categories of products provided by the company.")
                        , new Tag("Vendors", "Vendors that offer products in the company.")
                        , new Tag("Products", "Products that are presented in the company."));
    }


    private ApiInfo metaData() {
        Contact contact = new Contact("Muhammad Ali", "https://www.linkedin.com/in/zatribune/", "muhammadali40k@gmail.com");
        return new ApiInfo(
                "ZaTribune webservices",
                "Dummy webservices for testing purposes",
                "1.0",
                "You can mess it up. I don't care.",
                contact,
                "MIT license",
                "",
                new ArrayList<>()
        );
    }

    //this is just in case you had problems with the swagger ui
    //let the class extends WebMvcConfigurationSupport and implement this method
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/swagger-ui.html")
//        .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
}
