#### 提要  
1. __spring内置标签文件的位置__  
   在`spring-webmvc-4.3.22.RELEASE.jar`资源包中  
   ![](assets/markdown-img-paste-20190815095200602.png)  
1. __form标签概览__  
   ![](assets/markdown-img-paste-2019081509531777.png)  
1. __spring标签概览__  
   ![](assets/markdown-img-paste-20190815095409581.png)  
1. __form\:form标签使用__  
   - _文件引用_  
   ![](assets/markdown-img-paste-20190815095646847.png)  
   - _内置属性概览_  
     ![](assets/markdown-img-paste-20190815100143778.png)  
   - _form标签注意事项_  
     _1 : Neither BindingResult nor plain target object for bean name 'command' available as request attribute,出现此异常表示指定的key-value(默认key为command)不存在model中._  
     _2 : 重要的属性有`commandName`和`modelAttribute`,通常使用`commandName`其默认值是command,使用form标签model中必须存在匹配的key-value._  
     _3 : form标签的优势是支持除GET和POST之外的方法,普通的html标签是不支持的.使用html标签内置属性`method`设定;与之相关的属性`methodParam`设置http请求过滤相关的配置参数,默认是`_method`._  
     ![](assets/markdown-img-paste-20190815101218605.png)  

#### 使用  
1. _容器配置省略查看组织_  
   ![](assets/markdown-img-paste-20190815101456244.png)  
   ![](assets/markdown-img-paste-20190815101526848.png)  
1. _控制器_  
   ![](assets/markdown-img-paste-20190815101642640.png)  
1. _form标签使用_  
   ![](assets/markdown-img-paste-20190815101726979.png)  
1. _输出信息省略_  
