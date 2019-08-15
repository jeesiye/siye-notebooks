- 文字说明  
  (1)前端控制器`DispatcherServlet`截获客户端发送的请求;  
  (2)前端控制器解析url,获得uri;  
  &#8195;handlermapping根据uri得到对应的handler;  
  &#8195;此handler包含对应的handler对象和handler对应的拦截器;  
  &#8195;前端控制器将这些对象封装到`HandlerExecutionChain`对象中返回;  
  (3)前端控制器根据获得的handler,来选择一个合适的handlerAdapter,来调用对应的请求方法.  
  (4)提取请求中的模型数据,开始执行handler,在这一入参的过程中,会根据配置做一些额外的工作;比如消息转换,数据转换,数据格式化,数据验证等等;  
  (5)在handler执行完后,会向前端控制器返回一个`ModelAndView`实例.  
  (6)根据得到的模型视图对象,会选择一个合适的`ViewResolver`,返回给前端控制器.  
  (7)视图解析器结合`Model`和`View`来渲染视图.  
  (8)将渲染的结果返还给客户端.  
- 图片说明  
  待定  
