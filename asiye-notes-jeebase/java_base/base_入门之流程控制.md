1. __流程控制的分类__  
   - 顺序流程  
   - 条件流程  
   - 循环流程  
1. __条件流程__  
   - if语句  
     _1 : 涉及关键字有`if`,`else`,`else if`_  
     _2 : 支持各种组合方式的嵌套._  
     _3 : 注意!考虑是否有必要省略域控制符`{}`._  
     _4 : `if(condition){statement}`_  
     _5 : `if(condition){statement}else if(condition){statement}`_  
     _6 : `if(condition){statement}else if(condition){statement}else{statement}`_  
   - switch语句  
     _1 : 切忌!莫要在switch语句中忘记break跳出标记._  
     _2 : 涉及关键字有`switch`,`case`,`break`,`default`_  
     _3 : 支持的标签类型有`byte`,`short`,`int`,`char`和枚举类型_  
     _4 : 从jdk1\.7开始,支持标签的类型新增`java.lang.String`类型_  
     _5 : ide编译提示`Only convertible int values, strings or enum variables are permitted`_  
     _6 : `switch(tag){case tag1:statement;break; case tag2:statement;break; default:statement;break;}`_  
1. __循环流程__  
   - while语句  
     _1 : 有2种使用形式的变体._  
     _2 : 不带关键字`do`的表示只有满足条件才会执行,反之无论是否满足必定执行一次._  
     _3 : `while(condition){statement}`_  
     _4 : `do{statement}while(condition);`_  
     _5 : 支持各种方式嵌套_  
   - for语句  
     _1 : 可指定循环次数的循环语句._  
     _2 : `for(expression1;expression2;expression3){statement}`_  
     _3 : expression1,计数器设置初始化._  
     _4 : expression2,计数器循环条件限制._  
     _5 : expression3,计数器更新配置._  
     _6 : 若表达式不存在,不能缺少两个语句分隔符号`;`._  
     _7 : 切忌!虽然计数器类型支持浮点类型,但不推荐且慎重使用!_  
     _8 : jdk1\.5新增for-each循环语句,`for(elementType identifier : collectionIdentifier<? extends Iterator>){statement}`_  
1. __中断流程语句__  
   - `break`关键字  
     结束当前执行的代码块,必须置于顺序执行逻辑的最后一条语句.  
   - `return`关键字  
     将语句的执行权限,交由给所属临近方法的调用者.  
   - `continue`关键字  
     结束当前执行时的代码块,在顺序执行逻辑中,此关键字余下的执行语句会跳过处理.  
