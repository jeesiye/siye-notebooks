#### element元素详解  
- 定义  
  在XSD中约束一个元素.  
- 支持的属性  
  1. `id` : 全局下的唯一标识ID.  
  1. `name` : 元素的名称,可选.若父元素是schema,则必须配置该属性.  
  1. `ref` : 对另一个元素的引用.  
  1. `type` : 指定元素的类型.  
  1. `substitutionGroup` : 规定可替代该元素的元素名称.  
  1. `default` : 设定该元素的默认值.  
  1. `fixed` : 设定该元素的固定值.  
  1. `form` : 使用该元素时,是否必须带有命名空间前缀.候选值qualified和unqualified.  
  1. `maxOccurs` : 该元素出现的最大次数,默认值1.  
  1. `minOccurs` : 该元素出现的最小次数.默认值1.  
  1. `nillable` : 可选,指示是否将可显示的0值分配给该元素.注意,分配给的是元素的内容,而非元素的属性.默认值是false.如果设置为true,则在XML中使用该元素的时候,可以为该元素指定`xsi:nil`属性,并将该属性也设置为true,用于将0值显示的分配给该元素的内容.一般用于显示日期时间上.一般很少使用.  
  1. `abstract` : 是否可以在实例文档中使用该元素.  
  1. `block` : 是否防止指定的类型来替代该类型.  
  1. `final` : 是否防止该类型继续派生子类型.  
  1. `any attrbites` : 规定带有non-schema命名空间的其他属性.  
- 支持的父元素  
  `schema`,`all`,`group`,`choice`,`sequence`  
- 支持的子元素  
  `annotation?,(simpleType|complexType)?,(unique|key|keyref)*`  

#### all元素详解  
- 定义  
  默认情况下,规定其内的元素可以以任意顺序出现.每个元素只可出现1次.  
- 支持的属性  
  1. `id` : 全局下的唯一标识ID.  
  1. `maxOccurs` : 指定元素可出现的最大次数,默认值是1.  
  1. `minOccurs` : 指定元素可出现的最小次数,默认值是1.  
  1. `any attributes` : 规定带有non-schema命名空间前缀的其他任何属性.  
- 支持的父元素  
   group, complexType, restriction (both simpleContent and complexContent), extension (both simpleContent and complexContent)  
- 支持的子元素  
  `(annotation?,element*)`  

#### group元素详解  
- 定义  
  该元素用于在复杂类型中定义元素组.  
- 支持的属性  
  1. `id` : 全局下的唯一标识ID.  
  1. `name` : 指定该元素的名称.  
  1. `ref` : 配置需要引用的元素组的名称.  
  1. `maxOccurs` : 设定该元素可出现的最大次数.默认值1.  
  1. `minOccurs` : 设定该元素可出现的最小次数,默认值1.  
  1. `any attributes` : 规定带有non-schema命名空间的其他任何属性.  
- 支持的父元素  
   schema, choice, sequence, complexType, restriction (both simpleContent and complexContent), extension (both simpleContent and complexContent)  
- 支持的子元素  
  `(annotation?,(all | group | chioce)?)`  

#### chioce元素详解  
- 定义  
  在该元素内声明的元素仅允许出现一次,默认情况下.  
- 支持的属性  
  1. `id` : 全局下唯一标识的ID.  
  1. `maxOccurs` : 允许约束的元素出现的最大次数,默认值1.  
  1. `minOccurs` : 允许约束的元素出现的最小次数,默认值1.  
  1. `any attributes` : 规定带有non-schema命名空间的其他任何属性.  
- 支持的父元素  
  group, choice, sequence, complexType, restriction (both simpleContent and complexContent), extension (both simpleContent and complexContent)  
- 支持的子元素  
  `(annotation?,(element|group|choice|sequence|any)*)`  

#### sequence元素详解  
- 定义  
  规定元素以指定的顺序出现,默认情况,每个元素只能出现1次.  
- 支持的属性  
  1. `id` : 全局下的唯一标识ID.  
  1. `maxOccurs` : 允许元素出现的最大次数,默认值1.  
  1. `minOccurs` : 允许元素出现的最小次数,默认值1.  
  1. `any attributes` : 规定带有non-schema命名空间的其他任何属性.  
- 支持的父元素  
  group, choice, sequence, complexType, restriction (both simpleContent and complexContent), extension (both simpleContent and complexContent)  
- 支持的子元素  
  `(annotation?,(element|group|choice|sequence|any)*)`  
