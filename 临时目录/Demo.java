






XmlBeanFactory beanFactory = new XmlBeanFactory("指定xml文件的相对路径或之绝对路径");
beanFactory.getBean("指定类对象的类类型或之是配置文件中的bean名称");
// 上行代码返回的是指定类对象的实例


	/**
	 * Initialize the strategy objects that this servlet uses.
	 * <p>May be overridden in subclasses in order to initialize further strategy objects.
	 */
	protected void initStrategies(ApplicationContext context) {
		initMultipartResolver(context); //初始化上传文件解析器
		initLocaleResolver(context); // 初始化本地化解析器
		initThemeResolver(context); // 初始化主题解析器
		initHandlerMappings(context); // 初始化处理映射器,将请求映射到处理器
		initHandlerAdapters(context); // 初始化处理适配器
		initHandlerExceptionResolvers(context); // 初始化处理异常解析器
		initRequestToViewNameTranslator(context); // 初始化请求到视图名称解析器
		initViewResolvers(context); // 初始化视图解析器
		initFlashMapManager(context); // 初始化flash映射管理器
	}
