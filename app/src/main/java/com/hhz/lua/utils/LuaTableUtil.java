package com.hhz.lua.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Vector;

import se.krka.kahlua.luaj.compiler.LuaCompiler;
import se.krka.kahlua.vm.LuaClosure;
import se.krka.kahlua.vm.LuaPrototype;
import se.krka.kahlua.vm.LuaState;
import se.krka.kahlua.vm.LuaTable;


/**
 * @author lzliao
 */
public class LuaTableUtil {

    private static LuaClosure loadScript(byte[] data, boolean compiled, LuaState state, String name) {
        LuaClosure lc;
        try {
            if (compiled) {

                lc = LuaPrototype.loadByteCode(new ByteArrayInputStream(data),
                        state.getEnvironment());
            } else {
                lc = LuaCompiler.loadis(new ByteArrayInputStream(data), /* "<stdin>" */
                        name, state.getEnvironment());
            }
            return lc;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static LuaTable readData(byte[] data, boolean compiled, String name)// Caojun
    {
        LuaClosure lc = null;

        LuaState dataState = new LuaState();
        LuaTable dataObj = null;
        try {
            lc = loadScript(data, compiled, dataState, name);

            dataState.call(lc, null, null, null);
            dataObj = LuaTableUtil.getLuaTable(dataState.getEnvironment(),
                    "data");
        } catch (Exception e) {
            LogT.w("LuaTable" + e.getMessage());
            return null;
        }
        lc = null;
        // dataState.release();
        dataState = null;
        return dataObj;
    }

    /**
     * 读取key键值的数据，如果值不存在，则返回缺省值
     *
     * @param obj
     * @param key
     * @return
     */
    public static Integer getInteger(LuaTable obj, String key) {
        if (obj == null)
            return null;
        Object intObj = obj.rawget(key);
        if (intObj != null && intObj instanceof Double) {
            long longn = ((Double) intObj).longValue();
            int intn = (int) longn;
            return new Integer(intn);
        }

        return null;
    }

    public static Long getLong(LuaTable obj, String key) {
        if (obj == null)
            return null;
        Object intObj = obj.rawget(key);
        if (intObj != null && intObj instanceof Double) {
            return new Long(((Double) intObj).intValue());
        }
        return null;
    }

    public static String getString(LuaTable obj, String key, String defaultValue) {
        if (obj == null)
            return defaultValue;
        Object intObj = obj.rawget(key);
        if (intObj != null && intObj instanceof String) {
            return (String) intObj;
        }

        return defaultValue;
    }

    public static LuaTable getLuaTable(LuaTable obj, Object key) {
        if (obj == null)
            return null;
        Object tblObj = obj.rawget(key);
        if (tblObj != null && tblObj instanceof LuaTable) {
            return (LuaTable) tblObj;
        }

        return null;
    }

    public static Boolean getBoolean(LuaTable obj, String key) {
        if (obj == null)
            return null;
        Object boolObj = obj.rawget(key);
        if (boolObj != null && boolObj instanceof Boolean) {
            return (Boolean) boolObj;
        }

        return null;
    }

    public static LuaClosure getFunction(LuaTable obj, String key) {
        if (obj == null)
            return null;
        Object funcObj = obj.rawget(key);
        if (funcObj != null && funcObj instanceof LuaClosure) {
            return (LuaClosure) funcObj;
        }

        return null;
    }

    public static int tableLength(LuaTable table) {
        if (table == null) {
            return 0;
        }
        Object key = null;
        int len = 0;
        key = table.next(key);
        while (key != null) {
            len++;
            key = table.next(key);
        }
        return len;
    }

    public static void printTable(LuaTable table) {
        if (table == null) {
            return;
        }
        Object key = null;
        while (true) {
            Object nk = table.next(key);
            if (nk == null)
                break;
            Object value = table.rawget(nk);
//			LogManager.debug("LuaTable", value == null ? "null" : value
//					.getClass().toString());
            if (value instanceof LuaTable) {
                LuaTableUtil.printTable((LuaTable) value);
            } else {
//				LogManager.debug("LuaTable", nk + ": " + table.rawget(nk));
                LogT.w(table.rawget(nk));
            }
            key = nk;
        }
    }

    /**
     * 此方法获得的是直接的object值 如{a,b,c,d} vector=a,b,c,d
     */
    public static Vector<?> getArrayList(LuaTable obj) {
        if (obj == null) {
            return null;
        }
        Object key = null;
        int index = 0;
        Vector<Object> v = new Vector<Object>();

        while ((key = obj.next(key)) != null) {
            if (key instanceof Double) {
                index = (int) LuaState.fromDouble(key) - 1;
            } else {
                continue;
            }
            Object intObj = obj.rawget(key);
            //与getArray()区别：LuaTable itemObj = LuaTableUtil.getLuaTable(obj, key);
            if (intObj != null) {
                if (v.size() <= index) {
                    v.setSize(index + 1);
                }
                v.setElementAt(intObj, index);
            }
        }
        return v;
    }

    /**
     * 字符串转LuaTable
     *
     * @param data
     * @return
     */
    public static LuaTable stringToLuaTable(byte data[], boolean compiled) {
//		LuaTable resultdata=new LuaTableImpl();
//		ScriptEngine se = ScriptEngine.getInstance();
//		if (se != null) {
//			LuaTable data = se.readData(this.data, compiled, "NetData.getResultData");
//			resultdata.rawset("data", data);
//		}
//		return resultdata;
        return readData(data, compiled, "stringToLuaTable");
    }

    /**
     * 字符串转LuaTable
     *
     * @param data
     * @return
     */
    public static LuaTable stringToLuaTable(byte data[]) {
        return stringToLuaTable(data, false);
    }

    /**
     * 把LuaTable转换成字符串
     */
    public static String luaTableToString(LuaTable data) {
        StringBuffer s = new StringBuffer("{");
        // Log.systemout(data.len());
        // Log.systemout(data.next(data.next(null)));

        Object key = null;

        while ((key = data.next(key)) != null)
            if (!(key instanceof Double)) {

                s.append(key).append("=");

                Object tblObj = data.rawget(key);

                if (tblObj instanceof LuaTable)
                    s.append(luaTableToString((LuaTable) tblObj));
                else if (tblObj instanceof String)
                    s.append("\"").append(tblObj).append("\"");
                else
                    s.append(tblObj);

                s.append(",");

            }

        Vector<?> array = getArrayList(data);

        for (int i = 0; i < array.size(); i++) {

            Object tblObj = array.elementAt(i);

            if (tblObj instanceof LuaTable)
                s.append(luaTableToString((LuaTable) tblObj));
            else if (tblObj instanceof String)
                s.append("\"").append(tblObj).append("\"");
            else
                s.append(tblObj);

            s.append(",");

        }

        s.append("}");

//		LogManager.debug("LuaTable ", s.toString());
        LogT.w(s.toString());

        return s.toString();

    }

    /*
    vector对象（以及其他标准库容器对象）的重要属性就在于可以在运行时高效地添加元素。因为vector增长的效率高，
    在元素值已知的情况下，最好是动态地添加元素。这种增长方式不同于C语言中的内置数据类型，也不同于大多数其他编程语言的数据类型。
    特别地，如果读者习惯了C或Java的风格，由于vector元素连续存储，可能希望最好是预先分配合适的空间。
    但事实上，为了达到连续性，C++的做法恰好相反。
    虽然可以对给定元素个数的vector对象预先分配内存，但更有效的方法是先初始化一个空vector对象，然后再动态地增加元素。*/
    public static Vector<LuaTable> getArray(LuaTable obj) {
        if (obj == null) {
            return null;
        }
        Object key = null;
        int index = 0;
        Vector<LuaTable> v = new Vector<LuaTable>();
        while ((key = obj.next(key)) != null) {
            if (key instanceof Double) {
                index = (int) LuaState.fromDouble(key) - 1;
            } else {
                continue;
            }
            LuaTable itemObj = LuaTableUtil.getLuaTable(obj, key);
            if (itemObj != null) {
                if (v.size() <= index) {
                    v.setSize(index + 1);
                }
                v.setElementAt(itemObj, index);
            }
        }
        return v;
    }

    /* commonUtils 内容，非原版 */
    //LUA格式转换相关
    public static String luaPaser2String(Object obj) {
        if (null == obj) {
            return null;
        }
        return String.valueOf(luaValueOf(obj));
    }

    public static Object luaValueOf(Object obj) {
        if (null == obj) {
            return null;
        } else if (obj instanceof Double) {
            Double d_temp = Double.parseDouble(String.valueOf(obj));
            return d_temp;
        } else if (obj instanceof Float) {
            Float f_temp = Float.valueOf(String.valueOf(obj));
            return f_temp;
        } else if (obj instanceof Integer) {
            String s_temp = String.valueOf(obj);
            return Integer.parseInt(s_temp);
        } else if (obj instanceof String) {
            String s_temp = String.valueOf(obj);
            return s_temp;
        } else {
            String s_temp = String.valueOf(obj);
            return s_temp;
        }
    }

}

