package ocn.site.springmvc.code;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;

import ocn.site.springmvc.domain.User;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath*:/conf/application.xml")
public class Runtest {

	@Autowired
	private WebApplicationContext wac;

	private Logger logger = Logger.getLogger(this.getClass());

	@Test
	public void run() throws Exception {

		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/test");
		String jsonValue = JSONObject.toJSONString(new User("13", 34, "jack"));
		requestBuilder.content(jsonValue);
		requestBuilder.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		ResultActions ra = mockMvc.perform(requestBuilder);
		String returnJsonValue = JSONObject.toJSONString(new User("17", 34, "hack"));
		ra.andExpect(MockMvcResultMatchers.content().json(returnJsonValue));
		ra.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void run2() {
		String subtype = MediaType.TEXT_HTML.getSubtype();
		logger.info(subtype);
		logger.info(MediaType.TEXT_HTML.toString());
		logger.info(MediaType.APPLICATION_JSON_UTF8.toString());
	}

}
