package kr.co.neteacher.eclass.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan({"kr.co.neteacher.eclass"})
/**
 * Java configuration file that is used for Spring MVC and Thymeleaf
 * configurations
 */
public class WebMVCConfig implements WebMvcConfigurer, ApplicationContextAware {

    private ApplicationContext applicationContext;


    @Bean
    public InterceptorConfig interceptorConfig() {
        return new InterceptorConfig();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(deviceResolverHandlerInterceptor());
        registry.addInterceptor(interceptorConfig())
                .excludePathPatterns("/api/**")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/assets/**")
                .excludePathPatterns("/file/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/class-images/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/error/**")
                .excludePathPatterns("/actuator/**")
                .addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(deviceHandlerMethodArgumentResolver());
    }

    @Bean
    public DeviceResolverHandlerInterceptor deviceResolverHandlerInterceptor() {
        return new DeviceResolverHandlerInterceptor();
    }

    @Bean
    public DeviceHandlerMethodArgumentResolver deviceHandlerMethodArgumentResolver() {
        return new DeviceHandlerMethodArgumentResolver();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public ViewResolver htmlViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine(htmlTemplateResolver()));
        resolver.setContentType("text/html");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setViewNames(new String[]{"*.html"});
        return resolver;
    }

    @Bean
    public ViewResolver javascriptViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine(javascriptTemplateResolver()));
        resolver.setContentType("application/javascript");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setViewNames(new String[]{"*.js"});
        return resolver;
    }

    private ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.addDialect(new LayoutDialect());
        engine.addDialect(new Java8TimeDialect());
        engine.setTemplateResolver(templateResolver);
//        engine.setTemplateEngineMessageSource(messageSource());
        return engine;
    }

    private ITemplateResolver htmlTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("/resources/templates");
        resolver.setCacheable(false);
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }

    private ITemplateResolver javascriptTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("/static/js/");
        resolver.setCacheable(false);
        resolver.setTemplateMode(TemplateMode.JAVASCRIPT);
        return resolver;
    }

    @Bean
    public FilterRegistrationBean httpMethodFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new HiddenHttpMethodFilter());
        filter.setName("httpMethodFilter");
        filter.addUrlPatterns("/*");
        return filter;
    }

//    @Bean
//    @Description("Spring Message Resolver")
//    public ResourceBundleMessageSource messageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("messages");
//        return messageSource;
//    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login").setViewName("/views/login");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**",
                        "/assets/**",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/fonts/**")
                .addResourceLocations("classpath:/static/",
                        "classpath:/static/assets/",
                        "classpath:/static/css/",
                        "classpath:/static/js/",
                        "classpath:/static/images/",
                        "classpath:/static/fonts/");
    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS")
                .maxAge(3600)
                .exposedHeaders("Origin", "Content-Type", "X-Total-Count", "X-Limit", "Access-Control-Allow-Origin")
                .allowedHeaders("Origin", "Content-Type", "X-Total-Count", "X-Limit", "Access-Control-Allow-Origin");
    }
}