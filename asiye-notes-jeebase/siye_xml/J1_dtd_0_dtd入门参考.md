#### DTD认知  
- DTD是什么?  
  (1)`DTD`即是文档类型定义;  
  (2)是早期的xml文档的语义约束;  
  (3)是否有学习价值?有,虽然现在很少使用DTD,但是XSD中的许多知识点与DTD中的重合,有助于学习XSD;  
  (4)可以约束xml文档的元素和属性,以及文档结构,但是无法做到精度约束;  
- DTD的特性  
  总是以`<!DOCTYPE 根元素[`开头,以`]>`结尾.  
- DTD的声明  
  可以内嵌在xml文档中,也可作为独立的文件.  
  作为独立的文件时,DTD的声明和XML的声明语法一样.  
  `<?xml version="1.0" encoding="utf-8" standalone="yes"?>`  

#### DTD的3种引入  
- 内部DTD  
  `<!DOCTYPE 根元素 [元素描述]>`  
- 外部DTD  
  `<!DOCTYPE 根元素 SYSTEM '外部DTD的URI'>`  
- 公共DTD  
  `<!DOCTYPE 根元素 PUBLIC 'DTD标识' '公共DTD的URI'>`  

#### DTD的5个部件  
- 元素  
  约束xml文档元素的存在与组织架构;  
- 属性  
  简单的约束xml元素的属性;  
- 实体  
- #PCDATA  
- CDATA  
