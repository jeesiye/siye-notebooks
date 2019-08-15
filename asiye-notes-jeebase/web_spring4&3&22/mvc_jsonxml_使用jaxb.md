#### 提要  
1. _springmvc标准环境中默认加载有处理xml的http信息转换组件_  
   ![](assets/markdown-img-paste-2019081415414843.png)  
   ![](assets/markdown-img-paste-20190814154203367.png)  
1. _只要注册mvc注解驱动组件_  
   - 使用注解\@EnableWebMc  
   - xml\-config中使用标签`<mvc:annotation-driven />`  
1. _模拟测试中需要额外添加xmlunit资源包_  
   ![](assets/markdown-img-paste-20190814154442958.png)  
1. _需要jaxb相关知识_  

#### 使用  
1. __标准springmvc环境配置__  
   ![](assets/markdown-img-paste-20190814154700535.png)  
   ![](assets/markdown-img-paste-20190814154724162.png)  
   ![](assets/markdown-img-paste-20190814154747727.png)  
1. __控制器配置__  
   ![](assets/markdown-img-paste-20190814154827985.png)  
1. __pojo配置__  
   ![](assets/markdown-img-paste-20190814154855985.png)  
1. __ajax配置__  
   ![](assets/markdown-img-paste-20190814155108462.png)  
1. __测试输出省略__  

#### 模拟  
1. _xml-config配置_  
   ![](assets/markdown-img-paste-20190814155218120.png)  
1. _控制器配置省略_  
1. _模拟测试_  
   ![](assets/markdown-img-paste-20190814155250268.png)  
1. _测试输出信息_  
   ![](assets/markdown-img-paste-20190814155324966.png)  
