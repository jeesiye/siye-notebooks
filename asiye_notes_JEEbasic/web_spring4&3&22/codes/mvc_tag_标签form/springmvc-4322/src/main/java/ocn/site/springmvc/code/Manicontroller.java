package ocn.site.springmvc.code;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ocn.site.springmvc.domain.User;

@Controller
public class Manicontroller {

	private Logger logger = Logger.getLogger(this.getClass());

	@ModelAttribute("user")
	public User initialized() {
		logger.info("initializing...");
		return new User();// 给form标签需要的model的key-value
	}

	@GetMapping("/register")
	public String forward() {
		return "views/register";
	}

	@DeleteMapping(value = "/test")
	public void handler(User user) {
		logger.info(user);
	}

}
