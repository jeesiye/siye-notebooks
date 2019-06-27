- 结构

```
├─java
│  └─code
│          CommonsController.java
│          ConstraintImpl.java
│          ConstraintValidatorImpl.java
│          ManiController.java
│          User.java
│
├─resources
│  └─conf
│          application.xml
│
└─webapp
    │  index.jsp
    │
    └─WEB-INF
            register.jsp
            web.xml
```

- jar包

```
[INFO] +- junit:junit:jar:4.11:test
[INFO] |  \- org.hamcrest:hamcrest-core:jar:1.3:test
[INFO] +- org.springframework:spring-context:jar:4.3.22.RELEASE:compile
[INFO] |  +- org.springframework:spring-aop:jar:4.3.22.RELEASE:compile
[INFO] |  +- org.springframework:spring-beans:jar:4.3.22.RELEASE:compile
[INFO] |  +- org.springframework:spring-core:jar:4.3.22.RELEASE:compile
[INFO] |  |  \- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  \- org.springframework:spring-expression:jar:4.3.22.RELEASE:compile
[INFO] +- org.springframework:spring-web:jar:4.3.22.RELEASE:compile
[INFO] +- org.springframework:spring-webmvc:jar:4.3.22.RELEASE:compile
[INFO] +- org.hibernate:hibernate-validator:jar:5.2.0.Final:compile
[INFO] |  +- javax.validation:validation-api:jar:1.1.0.Final:compile
[INFO] |  +- org.jboss.logging:jboss-logging:jar:3.2.1.Final:compile
[INFO] |  \- com.fasterxml:classmate:jar:1.1.0:compile
[INFO] \- org.hibernate:hibernate-validator-annotation-processor:jar:5.2.0.Final:compile
[INFO] ------------------------------------------------------------------------
```

---

- `web.xml`

```xml
<!--前端控制器 -->
<!-- The front controller of this Spring Web application, responsible for
	handling all application requests -->
<servlet>
	<servlet-name>springDispatcherServlet</servlet-name>
	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	<init-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>classpath*:/conf/application.xml</param-value>
	</init-param>
	<load-on-startup>1</load-on-startup>
</servlet>

<!-- Map all requests to the DispatcherServlet for handling -->
<servlet-mapping>
	<servlet-name>springDispatcherServlet</servlet-name>
	<url-pattern>/</url-pattern>
</servlet-mapping>
<!-- 乱码处理 -->
<filter>
	<filter-name>characterEncodingFilter</filter-name>
	<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
	<init-param>
		<param-name>encoding</param-name>
		<param-value>UTF-8</param-value>
	</init-param>
	<init-param>
		<param-name>forceEncoding</param-name>
		<param-value>true</param-value>
	</init-param>
</filter>
<filter-mapping>
	<filter-name>characterEncodingFilter</filter-name>
	<url-pattern>/*</url-pattern>
</filter-mapping>
<!--禁用jsp脚本 -->
<jsp-config>
	<jsp-property-group>
		<url-pattern>*.jsp</url-pattern>
		<el-ignored>false</el-ignored>
		<scripting-invalid>true</scripting-invalid>
	</jsp-property-group>
</jsp-config>
```

- `application.xml`

```xml
<context:component-scan base-package="code"></context:component-scan>
<mvc:annotation-driven></mvc:annotation-driven>
<bean id="viewResolver"
	class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	<property name="prefix" value="/WEB-INF/"></property>
	<property name="suffix" value=".jsp"></property>
</bean>
```

---

- `index.jsp`

```jsp
<a href="register">register</a>
```

- `register.jsp`

```jsp
<form:form action="test" method="POST" commandName="user">
	<form:input path="name" />
	<form:errors path="name"></form:errors>
	<br>
	<form:button>submit</form:button>
</form:form>
```

---

- `ConstraintImpl.java`

```java
@Documented
@Target(value = { ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConstraintValidatorImpl.class)
public @interface ConstraintImpl {

	String message() default "";

	Class<?>[] groups() default {};

	Class<?>[] payload() default {};

	// 以上三个定义参考@Constraint

	int size();

}
```

- `ConstraintValidatorImpl.java`

```java
// 无需@Component,会被自动注册.
public class ConstraintValidatorImpl
		implements ConstraintValidator<ConstraintImpl, Object> {

	private int length;

	@Override
	public void initialize(ConstraintImpl constraintAnnotation) {
		System.out.println(constraintAnnotation);
		int size = constraintAnnotation.size();
		this.length = size;
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		try {
			// 注意获取的value,即自定义注解标记的值.
			String name = (String) value;
			int length = name.length();
			if (this.length > length) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

}
```

- `CommonsController.java`

```java
// spring的验证必须结合form标签和pojo对象,且应当有getter和setter方法
@ControllerAdvice
public class CommonsController {

	@Autowired
	private User user;

	@ModelAttribute
	public void prepare(ModelMap map) {
		map.put("user", user);
	}

}
```

- `ManiController.java`

```java
@GetMapping("/register")
public String handlerRegister() {
	return "register";
}

@PostMapping("/test")
public String handler(@ModelAttribute @Valid User user, BindingResult bindingResult) {
	System.out.println(user);
	boolean bool = bindingResult.hasErrors();
	System.out.println(bool);
	if (bool) {
		System.out.println(bindingResult.getFieldError().getDefaultMessage());
		return "register";
	}

	return "/";
}
```

- `User.java`

```java
@Component
public class User {

	@ConstraintImpl(size = 4, message = "max 4")
	private String name;
```
