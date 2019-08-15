#### xml的文档结构  
- 声明  
  `<?xml version="1.0" encoding="UTF-8" standalone="yes"?>`  
  (1)声明不是必须的,若使用必须置于整个文档的第1行!;  
  (2)`version`:表示xml的版本,必选属性;  
  (3)`encoding`:表示xml文档的编码,可选;  
  (4)`standalone`:是否引用外部的资源,可选;  
- 有且仅有一个根元素!  
- 元素必须合理的结束;  
- 元素必须合理的嵌套;  
- 元素的属性必须有值;  

#### w3c对xml文档的规范建议  
- xml元素的属性必须有属性值,应当用双引号引用;  
- 同1个xml元素不能有同名的属性;  
- xml元素的多个属性之间没有先后顺序;  

#### xml文档的基本规则  
- 合法的xml标识符  
  (1)由数字,字母,下划线`_`,中划线`-`,冒号`:`和点号`.`组成;不能以数字,中划线和点号开头;  
  (2)标签名不能出现空格!  
  (3)标签名不能出现`<`,`>`,`$`等特殊符号!  
  (4)标签名不能出现大小写的`xml`关键字!  
  (5)非明文规定,当不是命名空间时,不建议表签名出现`:`符号.  
  (6)和大多数语言的标识符规范一样,命名要具有描述性.  
- 可任意嵌套子元素(但应当遵循合理性)  
- 可声明空元素  
  (1)第1种形式:`<'tagName' />`  
  (2)第2种形式:`<'tagName'></'tagName'>`  
- 可声明字符数据`PCDATA`  
  注意!若存在特殊字符,应当进行转义.  
  (1)`PCDATA`是parsed character data的缩写;  
  (2)其,通常指的是被解析的字符串数据;  
  (3)比如`<tagName>this is text</tagName>`,其中的`this is text`就是`PCDATA`;  
- 可使用实体引用`entity`  
  xml预置了5种实体引用.  
  (1)`&lt` : 英文小于号`<`  
  (2)`&gt` : 英文大于号`>`  
  (3)`&amp` : 英文and符号`&`  
  (4)`&apos` : 英文单引号`'`  
  (5)`&quot` : 英文双引号`"`  
- 可使用CDATA标记  
  格式如`<![CDATA[此处填写不会被处理的文本数据]]>`;  
  只能使用在根元素之内;  
- 可使用注释  
  格式如`<!-- 此处填写需要进行注释的内容 -->`  
  以下几点是使用注释的注意点  
  (1)不推荐将注释放在标签之内;  
  (2)不能将注释放在xml文档的首行;  
  (3)不能在注释中使用双中划线`--`;  
  (4)注释不能以`-->`结尾;  
- 可使用处理指令  
  格式如`<?处理指令名   处理指令信息?>`  
  注意xml的声明1种特殊的用法,而不是处理指令!  
  简单入门的处理指令,即xml-stylesheet指令  
  示例如`<?xml-stylesheet type="text/css" href="css-uri"?>`  
- 可使用的换行规则  
  xml使用`LF`进行换行处理!  
  补充,换行小常识  
  (1)windows平台,以回车符`CR`和换行符`LF`的组合存储换行;  
  (2)unix平台,以换行符`LF`存储换行;  
  (3)mac平台,以回车符`CR`存储换行;  

#### xml的命名空间  
- 命名空间的语法  
  ```xml
  <tageName xmlns:namespace-prefix="namespace-uri">
  </tagName>
  ```  
  (1)可以为任何元素指定命名空间的属性;  
  (2)`xmlns`是XML Namespace的缩写;  
  (3)`xmlns[:namespace-prefix]`中的`namespace-prefix`是可选的;  
- 命名空间的作用  
  避免xml内元素的命名冲突.  
- 命名空间的分类  
  (1)带前缀的命名空间;  
  (2)默认的命名空间;  
- 默认命名空间示例  
  ```xml
  <tagName xmlns="http://www.example.com/use-xsd">
  </tagName>
  ```  
- 带前缀的命名空间示例  
  ```xml
  <xs:tagName xmlns:xs="http://www.example.com/use-xsd">
  </xs:tagName>
  ```  
