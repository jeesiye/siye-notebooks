package ocn.site.springmvc.code;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ocn.site.springmvc.domain.User;

@RestController
public class Manicontroller {

	private Logger logger = Logger.getLogger(this.getClass());

	@PostMapping(value="/test",consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User handler(@RequestBody User user) {
		logger.info(user);
		User obj = new User();
		obj.setId("17");
		obj.setAge(34);
		obj.setName("hack");
		return obj;
	}

}
