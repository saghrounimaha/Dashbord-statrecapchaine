package com.company.Security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfig implements WebMvcConfigurer {
    private final ImageCorsInterceptor imageCorsInterceptor;
    /*
     * private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
     * "classpath:/META-INF/resources/", "classpath:/resources/",
     * "classpath:/static/uploads/", "classpath:/public/" };
     */

    public WebConfig(ImageCorsInterceptor imageCorsInterceptor) {
        this.imageCorsInterceptor = imageCorsInterceptor;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/content/**")
                .addResourceLocations("file:/var/www/uploads/");
        /*
         * registry.addResourceHandler("/content/**")
         * .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
         */
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Apply the interceptor to URL patterns for images
        registry.addInterceptor(imageCorsInterceptor).addPathPatterns("/content/**");
    }
}