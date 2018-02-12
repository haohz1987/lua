#lua在Android中的应用
>介绍

    Lua是游戏、软件开发中脚本语言的首选。Android中的主要作用是替代json,提升了性能。
    fastjson包2.5MB,lua仅需要107KB,
    具体介绍：
        https://www.aliyun.com/jiaocheng/406668.html   
        http://www.runoob.com/lua/lua-tutorial.html
    lua脚本语言中一个独特的数据类型就是表,表其实就是java中的map、哈希表,比如说下面一个表:
    T1={}--定义一个空表 T1[1]=10--定义表的内容 T1["John"]={Age=27,Gender="Male"}
    而每一个脚本文件实际上就是一个大表,每一个变量、方法都是表的成员,因此脚本的变量默认都是全局的,而且也可以
    定以方法为变量,kahlua正是依据这个概念来的。
>使用kahlua，导入kahlua.jar

    kahlua最初是为J2ME设计的,现在已经扩展到J2SE,项目地址是http://code.google.com/p/kahlua/,在这里可以
    下载到源代码及编译好的jar包。在实际应用中我发现J2ME中导入jar包很困难,弄了好几天也没有成功,只好将源代码
    放在在工程的目录里一同编译。kahlua可以识别*.lua及*.lbc文件,*.lbc是编译后的lua文件,项目中一般使用
    这种文件,因为不会泄露lua文件的内容。可以到http://www.lua.org上下载一个lua的运行环境,安装后会自动添加
    安装路径到系统变量,此时在cmd中运行luac程序就可以编译脚本,命令是luac -o f.lbc d:/f.lua
    

    