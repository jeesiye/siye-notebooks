package ocn.site.springmvc.setup;

import java.util.List;

import javax.xml.transform.Source;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.util.UrlPathHelper;

@Configuration
@EnableWebMvc // 开启mvc注解驱动
@ComponentScan(basePackages = "ocn.site.springmvc.code")
public class Webconfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver getViewResolver() {
		return new InternalResourceViewResolver("/WEB-INF/", ".jsp");
	}

	@Override // 开启矩阵变量功能
	public void configurePathMatch(PathMatchConfigurer configurer) {
		// TODO Auto-generated method stub
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		urlPathHelper.setRemoveSemicolonContent(false);
		configurer.setUrlPathHelper(urlPathHelper);
	}

	@Override // 此处手写指出,若使用jackson转换json,spring默认加载该对应的转换组件
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
		stringHttpMessageConverter.setWriteAcceptCharset(false);
		converters.add(new ByteArrayHttpMessageConverter());// 将请求信息转换为byte[]类型二进制数据
		converters.add(stringHttpMessageConverter);// 将请求信息转换为字符串
		converters.add(new ResourceHttpMessageConverter());// 读写Resource类型的数据
		converters.add(new SourceHttpMessageConverter<Source>());// 读写Source类型的数据
		// FormHttpMessageConverter 组件,将表单数据读取到MultiValueMap中.
		// AllEncompassingFormHttpMessageConverter组件扩展上面的组件,增加了对xml和json接口的支持
		converters.add(new AllEncompassingFormHttpMessageConverter());

		// 此处添加需要使用的HttpMessageConverter信息转换组件
		converters.add(new MappingJackson2HttpMessageConverter());
	}

	@Override // 处理静态资源
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
