#### 提要  
1. 默认的springmvc容器环境下处理json的组件  
   _参考webmvc配置类中关于http信息转换组件设置的方法_  
   ![](assets/markdown-img-paste-20190814000340211.png)  
   ![](assets/markdown-img-paste-20190814000428538.png)  
   ![](assets/markdown-img-paste-20190814000449451.png)  
1. 手动配置http信息转换组件的话,默认的配置会失效,因此建议配置必要的默认组件.  
   ![](assets/markdown-img-paste-20190814000639159.png)  
   ![](assets/markdown-img-paste-20190814000718573.png)  
1. 需要导入fastjson的资源包,注意版本,高版本的会报错(此处使用低版本),具体原因待定.  
   ```
   java.lang.IllegalArgumentException: 'Content-Type' cannot contain wildcard type '*'
   ```  
   ![](assets/markdown-img-paste-20190814001001237.png)  

#### 使用  
1. _真实环境容器的配置java-config_  
   ![](assets/markdown-img-paste-2019081400134425.png)  
   ![](assets/markdown-img-paste-20190814001315577.png)  
   ![](assets/markdown-img-paste-2019081400142304.png)  
1. _控制器配置_  
   ![](assets/markdown-img-paste-2019081400183984.png)  
1. _ajax配置_  
   ![](assets/markdown-img-paste-20190814001959935.png)  
1. _输出信息省略_  

#### 模拟  
1. _xml-config配置_  
   ![](assets/markdown-img-paste-20190814002220861.png)  
1. _控制器配置省略_  
1. _模拟配置_  
   ![](assets/markdown-img-paste-20190814002717741.png)  
   ![](assets/markdown-img-paste-20190814002315948.png)  
1. _输出信息_  
   ![](assets/markdown-img-paste-20190814002353678.png)  

#### 补充  
1. 另一种http信息转换的配置方式  
1. 配置此方法,可注册默认的http信息转换组件  
   ![](assets/markdown-img-paste-2019081413515965.png)  
   ![](assets/markdown-img-paste-20190814135255408.png)  
