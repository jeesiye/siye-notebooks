#### annotation元素详解  
- 定义  
  XSD中用于文档注释的元素.  
- 支持的属性  
  (1) `id` : 全局下唯一标识ID.  
  (2) `any attributes` : 规定带有non-schema命名空间的其他任何属性.  
- 支持的父元素  
  任何元素  
- 支持的子元素  
  `(appinfo|documentation)*`  

#### any元素详解  
- 定义  
  允许使用者以任何的形式扩展XML文档中的该元素.  
- 支持的属性  
  (1) `id` : 全局下的唯一标识ID.  
  (2) `maxOccurs` : 允许出现的最大次数,默认值1.  
  (3) `minOccurs` : 允许出现的最小次数,默认值1.  
  (4) `namespace` : 规定可使用的元素命名空间,查看参考手册.  
  (5) `processContents` : 一个指示符,查看参考手册.  
  (6) `any attributes` : 规定带有non-schema命名空间的其他任何属性.  
- 支持的父元素  
  choice, sequence  
- 支持的子元素  
  `(annotation?)`  

#### 其他涉及XPath的元素  
&#8195;`key`,`keyref`,`unique`,`selector`,`filed`等元素,是对XML文档中的约束配置,但涉及XPath知识点,此处不作赘述.  
