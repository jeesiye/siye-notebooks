#### 提要  
- `@RequestMapping`的衍生注解(便捷的restful开发模式)  
  _以下衍生注解从spring4.3版本开始新增_  
  ![](assets/markdown-img-paste-20190805151304867.png)  
- `@RequestMapping`属性概览  
  ![](assets/markdown-img-paste-20190805151442587.png)  
- 补充说明  
  a.在实际的使用中,默认是推荐使用以下两个组件注册在容器中的.  
  即`RequestMappingHandlerMapping`和`RequestMappingHandlerAdapter`,这两个组件是从spring3.1开始新增的,其中一项功能是对注解`@RequestMapping`支持处理的.  
  b.基于java-config的配置中,使用注解`@EnableWebMvc`可默认注册这两个组件;  
  基于xml-config的配置中,配置`<mvc:annotation-driven />`标签,等效.  

#### 使用  
1. _使用正则表达式_  
   ![](assets/markdown-img-paste-20190805152248457.png)  
   ![](assets/markdown-img-paste-20190805152403361.png)  
   ![](assets/markdown-img-paste-20190805152434774.png)  
1. _使用ant风格路径_  
   ![](assets/markdown-img-paste-20190805152528818.png)  
   ![](assets/markdown-img-paste-2019080515260745.png)  
   ![](assets/markdown-img-paste-20190805152631524.png)  
1. _对url的请求参数进行约束_  
   ![](assets/markdown-img-paste-20190805152738931.png)  
   ![](assets/markdown-img-paste-20190805152803176.png)  
   ![](assets/markdown-img-paste-2019080515282970.png)  
1. _对请求的头信息header进行约束_  
   ![](assets/markdown-img-paste-20190805152913798.png)  
   ![](assets/markdown-img-paste-20190805152933645.png)  
   ![](assets/markdown-img-paste-20190805152951989.png)  
1. _对http请求方法类型进行约束_  
   ![](assets/markdown-img-paste-2019080515312525.png)  
   ![](assets/markdown-img-paste-20190805153149974.png)  
   ![](assets/markdown-img-paste-20190805153215670.png)  
1. _对请求体的MIME类型进行约束_  
   ![](assets/markdown-img-paste-20190805153305569.png)  
   ![](assets/markdown-img-paste-20190805153332684.png)  
   ![](assets/markdown-img-paste-20190805153447699.png)  
1. _对返回体的MIME类型进行约束_  
   ![](assets/markdown-img-paste-20190805153545590.png)  
   ![](assets/markdown-img-paste-20190805153610963.png)  
   ![](assets/markdown-img-paste-20190805153630190.png)  

补充 : 额外的容器配置(java-config)  
![](assets/markdown-img-paste-20190805153713269.png)  
