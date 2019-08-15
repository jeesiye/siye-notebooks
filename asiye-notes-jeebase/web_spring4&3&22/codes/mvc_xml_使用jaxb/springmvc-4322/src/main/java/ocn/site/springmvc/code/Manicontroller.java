package ocn.site.springmvc.code;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import ocn.site.springmvc.domain.User;

@Controller
public class Manicontroller {

	private Logger logger = Logger.getLogger(this.getClass());

	@PostMapping(value = "/test", consumes = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody User handler(@RequestBody User user) throws Exception {
		logger.info(user);

		// 获取指定class的jaxb上下文
		JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
		// 具体参考该方法的doc
		InputStream is = this.getClass().getResourceAsStream("/ocn/site/springmvc/domain/data.xml");
		// 使用Unmarshaller的实例将xml转换为java对象
		User returnObj = (User) jaxbContext.createUnmarshaller().unmarshal(is);
		logger.info(returnObj);
		return returnObj;
	}

}
