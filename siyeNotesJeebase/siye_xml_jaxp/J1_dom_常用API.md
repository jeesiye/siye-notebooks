#### 接口提要  
- Node接口架构  
  (1) `org.w3c.dom.Document`:文档对象;对应的是XML文档本身,此接口中包含一个根元素.  
  (2) `org.w3c.dom.DocumentType`:文档类型;
  (3) `org.w3c.dom.DocumentFragment`:文档片段,代表XML文档中的某个片段.  
  (4) `org.w3c.dom.Element`:元素;代表XML文档中开始标签,结束标签,及其中间部分所组成的整体.  
  (5) `org.w3c.dom.Attr`:属性;代表XML文档中元素的属性.  
  (6) `org.w3c.dom.CharacterData`:字符数据;是Comment,Text,CDATASelection的顶层接口,代表XML中各种字符数据.  
  (7) `org.w3c.dom.Comment`:注释;是CharacterData的子接口,代表注释中所包含的字符数据.  
  (8) `org.w3c.dom.Text`:文本数据;是CharacterData的子接口,代表XML中的文本数据.  
  (9) `org.w3c.dom.CDATASection`:CDATA片段;是Text的子接口,代表XML中CDATA内的字符数据.  
  (10) `org.w3c.dom.Entity`:实体;代表XML中已解析和未解析的实体,它代表的是实体本身,而不是实体声明.  
  (11) `org.w3c.dom.EntityReference`:实体引用;代表的是DOM树中的实体引用,需要指出的是,在XML中对字符的引用,和对预定义实体的引用,都会被转换成对应的Unicode值,而不会当作实体引用来处理.  
  (12) `org.w3c.dom.ProcessingInstruction`:处理指令;代表XML中的处理指令.  
  (13) `org.w3c.dom.Notation`:符号;代表在DTD中声明的符号.  

#### DOM解析器相关  
- `javax.xml.parsers.DocumentBuilderFactory` : 从XML文档中获取DOM树的解析器.  
  以下罗列部分方法  
  [获取实例相关]  
  (1) `javax.xml.parsers.DocumentBuilderFactory.newInstance()`: 获取 DocumentBuilderFactory 的新实例。  
  (2) `javax.xml.parsers.DocumentBuilderFactory.newDocumentBuilder()`: 使用当前配置的参数创建一个新的 DocumentBuilder 实例。  
  [配置相关]  
  (1) `javax.xml.parsers.DocumentBuilderFactory.setNamespaceAware(boolean)`:指定由此代码生成的解析器将提供对 XML 名称空间的支持。  
  (2) `javax.xml.parsers.DocumentBuilderFactory.setValidating(boolean)`:指定由此代码生成的解析器将验证被解析的文档。  
  (3) `javax.xml.parsers.DocumentBuilderFactory.setIgnoringElementContentWhitespace(boolean)`:指定由此工厂创建的解析器在解析 XML 文档时，必须删除元素内容中的空格（有时也可以称作“可忽略空格”，请参阅 XML Rec 2.10）。  
  (4) `javax.xml.parsers.DocumentBuilderFactory.setExpandEntityReferences(boolean)`:指定由此代码生成的解析器将扩展实体引用节点。  
  (5) `javax.xml.parsers.DocumentBuilderFactory.setIgnoringComments(boolean)`: 指定由此代码生成的解析器将忽略注释。  
  (6) `javax.xml.parsers.DocumentBuilderFactory.setCoalescing(boolean)`:指定由此代码生成的解析器将把 CDATA 节点转换为 Text 节点，并将其附加到相邻（如果有）的 Text 节点。  
  (7) `javax.xml.parsers.DocumentBuilderFactory.setAttribute(String, Object)`:允许用户在底层实现上设置特定属性。  
  (8) `javax.xml.parsers.DocumentBuilderFactory.setFeature(String, boolean)`:设置由此工厂创建的此 DocumentBuilderFactory 和 DocumentBuilder 的功能。  
  (9) `javax.xml.parsers.DocumentBuilderFactory.setSchema(Schema)`:设置将由解析器使用的 Schema，该解析器从此工厂创建。  
  (10) `javax.xml.parsers.DocumentBuilderFactory.setXIncludeAware(boolean)`:设置 XInclude 处理的状态。  
  [判断相关]  
  (1) `javax.xml.parsers.DocumentBuilderFactory.isNamespaceAware()`  
  (2) `javax.xml.parsers.DocumentBuilderFactory.isValidating()`  
  (3) `javax.xml.parsers.DocumentBuilderFactory.isIgnoringElementContentWhitespace()`  
  (4) `javax.xml.parsers.DocumentBuilderFactory.isExpandEntityReferences()`  
  (5) `javax.xml.parsers.DocumentBuilderFactory.isIgnoringComments()`  
  (6) `javax.xml.parsers.DocumentBuilderFactory.isCoalescing()`  
  (7) `javax.xml.parsers.DocumentBuilderFactory.isXIncludeAware()`  
-  `javax.xml.parsers.DocumentBuilder` : 定义 API， 使其从 XML 文档获取 DOM 文档实例。  
  以下罗列部分方法  
  [解析相关]  
  (1) `javax.xml.parsers.DocumentBuilder.parse(InputStream)`:将给定输入源的内容解析为一个 XML 文档，并且返回一个新的 DOM Document 对象。  
  (2) `javax.xml.parsers.DocumentBuilder.parse(InputStream, String)`:将给定 InputStream 的内容解析为一个 XML 文档，并且返回一个新的 DOM Document 对象。  
  (3) `javax.xml.parsers.DocumentBuilder.parse(String)`:  将给定 URI 的内容解析为一个 XML 文档，并且返回一个新的 DOM Document 对象。  
  (4) `javax.xml.parsers.DocumentBuilder.parse(File)`:将给定文件的内容解析为一个 XML 文档，并且返回一个新的 DOM Document 对象。  
  [判断相关]  
  (1) `javax.xml.parsers.DocumentBuilder.isNamespaceAware()`:指示此解析器是否被配置为可识别名称空间。  
  (2) `javax.xml.parsers.DocumentBuilder.isValidating()`:指示此解析器是否被配置为验证 XML 文档。  
  (3) `javax.xml.parsers.DocumentBuilder.isXIncludeAware()`:获取此解析器的 XInclude 处理模式。  
  [getter,setter相关]  
  (1) `javax.xml.parsers.DocumentBuilder.setEntityResolver(EntityResolver)`: 指定使用 EntityResolver 解析要解析的 XML 文档中存在的实体。  
  (2) `javax.xml.parsers.DocumentBuilder.setErrorHandler(ErrorHandler)`:指定解析器要使用的 ErrorHandler。  
  (3) `javax.xml.parsers.DocumentBuilder.getDOMImplementation()`:获取 DOMImplementation 对象的一个实例。  
  (4) `javax.xml.parsers.DocumentBuilder.getSchema()`:获取由 XML 处理器使用的 Schema 的引用。  
  [杂项]  
  (1) `javax.xml.parsers.DocumentBuilder.newDocument()`:获取 DOM Document 对象的一个新实例来生成一个 DOM 树。  
  (2) `javax.xml.parsers.DocumentBuilder.reset()`:将此 DocumentBuilder 重置为其原始配置。  

#### 节点操作相关  
- `org.w3c.dom.Node`:该 Node 接口是整个文档对象模型的主要数据类型。  
  [获取相关]  
  (1) `org.w3c.dom.Node.getNodeName()`:  此节点的名称，取决于其类型；参见上表。  
  (2) `org.w3c.dom.Node.getNodeValue()`:此节点的值，取决于其类型；参见上表。  
  (3) `org.w3c.dom.Node.getNodeType()`:表示基础对象的类型的节点，如上所述。  
  (4) `org.w3c.dom.Node.getParentNode()`:此节点的父节点。  
  (5) `org.w3c.dom.Node.getChildNodes()`:包含此节点的所有子节点的 NodeList。  
  (6) `org.w3c.dom.Node.getFirstChild()`:此节点的第一个子节点。  
  (7) `org.w3c.dom.Node.getLastChild()`:此节点的最后一个节点。  
  (8) `org.w3c.dom.Node.getPreviousSibling()`: 直接在此节点之前的节点。  
  (9) `org.w3c.dom.Node.getNextSibling()`:直接在此节点之后的节点。  
  (10) `org.w3c.dom.Node.getAttributes()`:包含此节点的属性的 NamedNodeMap（如果它是 Element）；否则为 null。  
  (11) `org.w3c.dom.Node.getOwnerDocument()`:与此节点相关的 Document 对象。  
  (12) `org.w3c.dom.Node.getNamespaceURI()`:此节点的名称空间 URI；如果它未被指定，则返回 null（参见）。  
  (13) `org.w3c.dom.Node.getPrefix()`:此节点的名称空间前缀；如果它未被指定，则为 null。  
  (14) `org.w3c.dom.Node.getLocalName()`:返回此节点限定名称的本地部分。  
  (15) `org.w3c.dom.Node.getBaseURI()`:此节点的绝对基 URI；如果实现不能获得绝对 URI，则为 null。  
  (16) `org.w3c.dom.Node.getTextContent()`:此属性返回此节点及其后代的文本内容。  
  (17) `org.w3c.dom.Node.getFeature(String, String)`: 此方法返回一个特定的对象，该对象实现指定功能或版本的特定 API，如下所述。  
  (18) `org.w3c.dom.Node.getUserData(String)`:检索与此节点上的某个键相关联的对象。  
