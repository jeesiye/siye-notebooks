package ocn.site.springmvc.code;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath*:/conf/application.xml")
public class Runtest {

	@Autowired
	private WebApplicationContext wac;

	private Logger logger = Logger.getLogger(this.getClass());

	@Test
	public void run() throws Exception {

		String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> "
				+ "<book><id>1</id><name>从入门到放弃</name><author>某某某</author></book>";
		DefaultMockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(this.wac);
		// url路径映射中，查看此方法的doc说明，默认为/*;若设置为/,在模拟环境下会失效的.
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("utf-8", true);
		mockMvcBuilder.addFilters(encodingFilter);
		MockMvc mockMvc = mockMvcBuilder.build();
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/test");
		requestBuilder.content(xmlContent);
		requestBuilder.contentType(MediaType.APPLICATION_XML_VALUE);
		ResultActions ra = mockMvc.perform(requestBuilder);
		InputStream is = this.getClass().getResourceAsStream("/ocn/site/springmvc/domain/data.xml");
		byte[] bytes = new byte[is.available()];
		is.read(bytes);
		ra.andExpect(MockMvcResultMatchers.content().xml(new String(bytes)));
		ra.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void run2() {
		String subtype = MediaType.TEXT_HTML.getSubtype();
		logger.info(subtype);
		logger.info(MediaType.TEXT_HTML.toString());
		logger.info(MediaType.APPLICATION_JSON_UTF8.toString());
	}

	@Test
	public void run3() throws Exception {
		InputStream is = this.getClass().getResourceAsStream("/ocn/site/springmvc/domain/data.xml");
		byte[] bytes = new byte[is.available()];
		is.read(bytes);
		String str = new String(bytes);
		logger.info(str);
	}

};