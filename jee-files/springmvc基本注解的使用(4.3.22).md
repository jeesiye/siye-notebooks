
注解|说明
:--|:--
@SessionAttributes|提取model中键值对到session中



###### @SessionAttributes
1. 将model中指定的key存储到session实例中
1. 限制标记的控制器,在处理请求方法的时候,session的实例中必须存在此注解指定的键值对.(**实际的使用场景**)


@SessionAttributes
@ModelAttribute

@Controller
@RequestMapping

@RequestParam
@PathVariable
@MatrixVariable
@RequestHeader
@CookieValue
@SessionAttribute
