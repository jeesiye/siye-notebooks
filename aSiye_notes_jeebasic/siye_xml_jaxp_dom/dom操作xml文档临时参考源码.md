```java
package zebra_text_xml_jaxp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xerces.impl.Constants;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

public class J0_dom {

	/*
	 * 使用DOM解析XML使用到的类和对象方法
	 * (排序按照大体上按照使用的先后顺序)
	 *
	 * (涉及到的DOM解析器)
	 * javax.xml.parsers.DocumentBuilderFactory.setCoalescing(boolean) : 是否自动转换CDATA元素.
	 * javax.xml.parsers.DocumentBuilderFactory.setExpandEntityReferences(boolean) : 是否自动获取实体引用数据.
	 * javax.xml.parsers.DocumentBuilderFactory.setIgnoringComments(boolean) : 是否忽略文本注释.
	 * javax.xml.parsers.DocumentBuilderFactory.setIgnoringElementContentWhitespace(boolean) : 是否自动删除内容中的空格.
	 * javax.xml.parsers.DocumentBuilderFactory.newInstance() : 初始化该对象.
	 * javax.xml.parsers.DocumentBuilderFactory.newDocumentBuilder() : 获取DocumentBuilder的实例.
	 *
	 * javax.xml.parsers.DocumentBuilder.parse(InputStream) : 解析XML文件,获取Document的实例.
	 *
	 *
	 * (DOM节点操作)
	 * org.w3c.dom.Node.hasChildNodes() : 判断当前节点是否还有子节点.
	 * org.w3c.dom.Node.hasAttributes() : 获取当前节点的属性集合.
	 * org.w3c.dom.Node.getChildNodes() : 获取当前节点的子节点集合.
	 * org.w3c.dom.Node.getNodeType() : 获取节点的节点类型.
	 * org.w3c.dom.Node.getNodeName() : 获取当前节点的节点名称.
	 * org.w3c.dom.Node.getNodeValue() : 获取当前节点的节点值.
	 * org.w3c.dom.Node.getTextContent() : 获取当前节点内的文本内容.
	 *
	 * org.w3c.dom.Document.getDocumentElement() : 获取XML文档的根元素.
	 *
	 * org.w3c.dom.Element.getTagName() : 获取标签的名称.
	 *
	 * org.w3c.dom.NodeList.getLength() : 获取节点集合的长度.
	 * org.w3c.dom.NodeList.item(int) : 获取指定索引的节点实例.
	 *
	 * org.w3c.dom.NamedNodeMap.getLength() : 获取属性集合的长度.
	 * org.w3c.dom.NamedNodeMap.item(int) : 获取指定索引的属性节点实例.
	 *
	 *
	 * org.w3c.dom.Node.ELEMENT_NODE : 常量,元素节点类型.
	 * org.w3c.dom.Node.ATTRIBUTE_NODE : 常量,属性节点类型.
	 */
	@Test
	public void readXmlText() throws Exception {

		FileInputStream fileInputStream = null;

		try {
			String filePath = "src/main/java/zebra_text_xml_jaxp/ReadXml.xml";
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}

			fileInputStream = new FileInputStream(file);

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilderFactory.setCoalescing(false);
			documentBuilderFactory.setExpandEntityReferences(false);
			documentBuilderFactory.setIgnoringComments(true);
			documentBuilderFactory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(fileInputStream);
			Element rootElement = document.getDocumentElement();

			Assert.assertEquals(rootElement.getTagName(), "class");

			int index = 0;
			Map<Integer, Map<String, String>> getElementMap = new HashMap<>();
			Map<String, String> elementMap = null;
			Map<Integer, Map<String, String>> getAttributeMap = new HashMap<>();
			Map<String, String> attributeMap = null;

			if (rootElement.hasChildNodes()) {

				NodeList studentNodeList = rootElement.getChildNodes();
				for (int i = 0; i < studentNodeList.getLength(); i++) {
					Node studentNode = studentNodeList.item(i);
					if (studentNode.getNodeType() == Node.ELEMENT_NODE) {
						// attr
						if (studentNode.hasAttributes()) {
							attributeMap = new HashMap<>();
							NamedNodeMap namedNodeMap = studentNode.getAttributes();
							for (int j = 0; j < namedNodeMap.getLength(); j++) {
								Node node = namedNodeMap.item(j);
								if (node.getNodeType() == Node.ATTRIBUTE_NODE) {
									attributeMap.put(node.getNodeName(), node.getNodeValue());
								}
							}
							getAttributeMap.put(index, attributeMap);
						}
						// ele
						if (studentNode.hasChildNodes()) {
							NodeList nodeList = studentNode.getChildNodes();
							elementMap = new HashMap<>();
							for (int j = 0; j < nodeList.getLength(); j++) {
								Node node = nodeList.item(j);
								if (node.getNodeType() == Node.ELEMENT_NODE) {
									elementMap.put(node.getNodeName(), node.getTextContent());
								}
							}
							getElementMap.put(index, elementMap);
						}
						index++;
					}
				}

			}

			Set<Integer> attSet = getAttributeMap.keySet();
			for (Integer key : attSet) {
				System.out.println(key + "" + getAttributeMap.get(key));
			}
			Set<Integer> eleSet = getElementMap.keySet();
			for (Integer key : eleSet) {
				System.out.println(key + "" + getElementMap.get(key));
			}

		} finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			}
		}

	}

	/*
	 * 此示例中使用到的类和对象方法
	 * 排序大体上按照使用的先后顺序.
	 *
	 *
	 * (涉及到的DOM解析器)
	 * javax.xml.parsers.DocumentBuilderFactory
	 * javax.xml.parsers.DocumentBuilder
	 * 以上使用的方法,在解析XML文件的示例中,有重复使用的,参考以上说明,此处不赘述.
	 * javax.xml.parsers.DocumentBuilder.newDocument() : 创建一个空的dom对象实例.
	 *
	 * org.w3c.dom.Document.createElement(String) : 创建一个节点元素.
	 * org.w3c.dom.Element.setAttribute(String, String) : 设置当前节点元素的属性.
	 * org.w3c.dom.Node.setTextContent(String) : 设置当前节点元素的文本数据.
	 * org.w3c.dom.Node.appendChild(Node) : 对当前节点,添加指定的节点.
	 *
	 * (DOM序列化操作)
	 * org.w3c.dom.bootstrap.DOMImplementationRegistry.newInstance() : 获取dom序列化注册器的实例.
	 * org.w3c.dom.bootstrap.DOMImplementationRegistry.getDOMImplementation(String) : 获取LS序列化DOM的对象实例.
	 * org.w3c.dom.ls.DOMImplementationLS.createLSSerializer() : 创建LS序列化对象实例.
	 * org.w3c.dom.ls.DOMImplementationLS.createLSOutput() : 创建LS输出配置对象实例.
	 * org.w3c.dom.ls.LSOutput.setEncoding(String) : 设置输出数据编码.
	 * org.w3c.dom.ls.LSOutput.setCharacterStream(Writer) : 指定输出数据的字符流.
	 * org.w3c.dom.ls.LSSerializer.write(Node, LSOutput) : 将dom树序列化输出到指定的输出字符流中.
	 * org.w3c.dom.ls.LSSerializer.getDomConfig() : 获取配置DOM的对象实例.
	 * org.w3c.dom.DOMConfiguration.setParameter(String, Object) : 配置DOM定制化的参数.
	 * org.apache.xerces.impl.Constants : 调用DOM配置常量,jdk内置的有此类,但无法直接调用(?此问题待解决),添加xerces-J开源包.
	 * org.apache.xerces.impl.Constants.DOM_FORMAT_PRETTY_PRINT : 输出时,格式化DOM树结构.
	 */
	@Test
	public void writeXmlText() throws Exception {
		FileWriter fileWriter = null;
		try {

			String filePath = "src/main/java/zebra_text_xml_jaxp/WriteXml.xml";
			File file = new File(filePath);
			fileWriter = new FileWriter(file, false);

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilderFactory.setCoalescing(false);
			documentBuilderFactory.setExpandEntityReferences(false);
			documentBuilderFactory.setIgnoringComments(true);
			documentBuilderFactory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();

			Element classElement = document.createElement("class");
			for (int i = 0; i < 3; i++) {
				Element studentElement = document.createElement("student");
				studentElement.setAttribute("rollno", "235");
				Element firstnameElement = document.createElement("firstname");
				firstnameElement.setTextContent("jack");
				Element lastnameElement = document.createElement("lastname");
				lastnameElement.setTextContent("rose");
				Element nicknameElement = document.createElement("nickname");
				nicknameElement.setTextContent("hack");
				Element marksElement = document.createElement("marks");
				marksElement.setTextContent("2650");
				studentElement.appendChild(firstnameElement);
				studentElement.appendChild(lastnameElement);
				studentElement.appendChild(nicknameElement);
				studentElement.appendChild(marksElement);
				classElement.appendChild(studentElement);
			}
			document.appendChild(classElement);

			DOMImplementationRegistry domImplementationRegistry = DOMImplementationRegistry.newInstance();
			DOMImplementationLS domImplementationLS =
				(DOMImplementationLS) domImplementationRegistry.getDOMImplementation("LS");
			LSSerializer lsSerializer = domImplementationLS.createLSSerializer();
			// 当前的实现字类是 com.sun.org.apache.xml.internal.serialize.DOMSerializerImpl
			// 内置在jdk中
			DOMConfiguration domConfiguration = lsSerializer.getDomConfig();
			// 格式化xml文档.
			domConfiguration.setParameter(Constants.DOM_FORMAT_PRETTY_PRINT, true);
			LSOutput lsOutput = domImplementationLS.createLSOutput();
			lsOutput.setEncoding("UTF-8");
			lsOutput.setCharacterStream(fileWriter);
			boolean bool = lsSerializer.write(document, lsOutput);
			System.out.println(bool);

		} finally {
			if (fileWriter != null) {
				fileWriter.close();
			}
		}
	}

}

```
