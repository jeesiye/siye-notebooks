```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xsi:schemaLocation="http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">


	<mvc:annotation-driven />


	<context:component-scan
		base-package="ocn.site.keepmoving" />
	<mvc:annotation-driven /><!--该标签会注册默认的映射器和适配器. -->
	<!--从spring4.0开始,若是没有显式的配置处理映射器和处理适配器,spring容器会配置默认的.这在以前是必须手动配置的. -->
	<bean id="handlerMapping"
		class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping" />
	<bean id="handlerAdapter"
		class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter" />
	<bean id="viewResolver"
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/" />
		<property name="suffix" value=".jsp" />
	</bean>


</beans>
```  

```java
package ocn.site.keepmoving;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // 从spring2.5开始支持
@RequestMapping("/") // 从spring2.5开始支持
public class ManiController {
	private Logger logger = Logger.getLogger(this.getClass());

	@RequestMapping("test")
	public ModelAndView handler() {
		ModelAndView mav = new ModelAndView("success");
		mav.addObject("name", "hack");
		logger.info("executing...");
		return mav;
	}

}
```  

```java
package ocn.site.keepmoving;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/ocn/site/keepmoving/app.xml")
public class RunMain {
	@Autowired
	private WebApplicationContext wac;

	@Test
	public void run() throws Exception {

		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		ResultActions ra = mockMvc.perform(MockMvcRequestBuilders.get("/test"));
		ra.andExpect(MockMvcResultMatchers.status().isOk());
		ra.andExpect(MockMvcResultMatchers.view().name("success"));
		ra.andExpect(MockMvcResultMatchers.model().attribute("name", "hack"));

	}

}
```  
