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
    下载到源代码及编译好的jar包。kahlua可以识别*.lua及*.lbc文件,*.lbc是编译后的lua文件,项目中一般使用
    这种文件,因为不会泄露lua文件的内容。可以到http://www.lua.org上下载一个lua的运行环境,安装后会自动添加
    安装路径到系统变量,此时在cmd中运行luac程序就可以编译脚本,命令是luac -o f.lbc d:/f.lua
>参数结构

    data = {appinfoList = {
        { appDesc = "爱奇艺", appIcon = "", appId = 101, appName = "爱奇艺", appPackage = "com.qiyi.video", appSize = "", appStatus = "00", appType = "01", appUrl = "http://121.29.10.1/f5.market.mi-img.com/download/AppStore/0b8b552a1df0a8bc417a5afae3a26b2fb1342a909/com.qiyi.video.apk", appVersion = "2.5.0", createTime = "Fri Aug 04 14:55:41 CST 2017", createTimeForamt = "2017-08-04 14:55:41", factoryCode = "XDL001", installType = 0, uptTime = "Wed Jan 10 16:46:24 CST 2018", uptTimeForamt = "2018-01-10 16:46:24" },
        { appDesc = "会下载错误", appIcon = "", appId = 201, appName = "智能pos", appPackage = "com.hhz.laucher", appSize = "", appStatus = "00", appType = "01", appUrl = "http://10.148.21.91:8080/smartpos/20170922/smartposAppstore2.0.1-0922.apk", appVersion = "2.0.1", createTime = "Fri Sep 15 11:44:19 CST 2017", createTimeForamt = "2017-09-15 11:44:19", factoryCode = "XDL001", installType = 0, uptTime = "Fri Dec 29 10:23:44 CST 2017", uptTimeForamt = "2017-12-29 10:23:44" },
        { appDesc = "购票", appIcon = "", appId = 241, appName = "微信", appPackage = "com.tencent.mm", appSize = "", appStatus = "00", appType = "01", appUrl = "http://116.117.158.129/f2.market.xiaomi.com/download/AppStore/04275951df2d94fee0a8210a3b51ae624cc34483a/com.tencent.mm.apk", appVersion = "1.0.0", createTime = "Tue Nov 14 15:08:20 CST 2017", createTimeForamt = "2017-11-14 15:08:20", factoryCode = "XDL001", installType = 0, uptTime = "Thu Dec 28 10:21:46 CST 2017", uptTimeForamt = "2017-12-28 10:21:46" },
        { appDesc = "新浪微博", appIcon = "", appId = 242, appName = "新浪微博", appPackage = "com.sina.weibo", appSize = "", appStatus = "00", appType = "01", appUrl = "http://60.28.125.129/f1.market.xiaomi.com/download/AppStore/0ff41344f280f40c83a1bbf7f14279fb6542ebd2a/com.sina.weibo.apk", appVersion = "1.0.0", createTime = "Tue Nov 14 15:13:44 CST 2017", createTimeForamt = "2017-11-14 15:13:44", factoryCode = "XDL001", installType = 0, uptTime = "Thu Dec 28 10:23:53 CST 2017", uptTimeForamt = "2017-12-28 10:23:53" },
        { appDesc = "qq下载", appIcon = "", appId = 261, appName = "QQ", appPackage = "com.tencent.mobileqq.apk", appSize = "", appStatus = "00", appType = "01", appUrl = "http://121.29.10.1/f3.market.xiaomi.com/download/AppStore/0ff0604fd770f481927d1edfad35675a3568ba656/com.tencent.mobileqq.apk", appVersion = "3.0.2", createTime = "Tue Jan 16 17:38:25 CST 2018", createTimeForamt = "2018-01-16 17:38:25", factoryCode = "XDL001", installType = 0, uptTime = "Tue Jan 16 17:38:46 CST 2018", uptTimeForamt = "2018-01-16 17:38:46" }
        },responseCode = "0",}
    使用中，可以自由切换成json格式
>解析

     LuaTable luaTable = LuaTableUtil.stringToLuaTable(luaDataString.getBytes());
     //获取参数的方法
     String mRespCode2 = LuaTableUtil.getString(luaTable, "responseCode", "");
     //获取数组的方法
     String mAppinfoList = LuaTableUtil.getArrayList(LuaTableUtil.getLuaTable(luaTable, "appinfoList")).toString();
     //解析数组，转成json
     Object obj = luaTable.rawget("appinfoList");
     Vector<LuaTable> array = LuaTableUtil.getArray((LuaTable) obj);
     if (array == null || array.size() <= 0) return;
     ArrayList<LuaBean> arrayList = new ArrayList<>();
     for (LuaTable mLuaTable : array) {
         LuaBean luaBean = new LuaBean();
         luaBean.setAppDesc((String) mLuaTable.rawget("appDesc"));  
          ………………
     }

    