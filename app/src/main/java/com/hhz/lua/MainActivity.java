package com.hhz.lua;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hhz.lua.utils.AssetsUtil;
import com.hhz.lua.utils.LogT;
import com.hhz.lua.utils.LuaTableUtil;

import java.util.ArrayList;
import java.util.Vector;

import se.krka.kahlua.vm.LuaTable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String luaDataString = AssetsUtil.loadlocalData(this, "luaData.lua");
        LuaTable luaTable = LuaTableUtil.stringToLuaTable(luaDataString.getBytes());
        //获取参数的方法
        String mRespCode2 = LuaTableUtil.getString(luaTable, "responseCode", "");
        //获取数组的方法
        String mAppinfoList = LuaTableUtil.getArrayList(LuaTableUtil.getLuaTable(luaTable, "appinfoList")).toString();
        LogT.w(mRespCode2 + ";" + mAppinfoList);
        if (luaTable == null) return;
        //解析数组，转成json
        Object obj = luaTable.rawget("appinfoList");
        Vector<LuaTable> array = LuaTableUtil.getArray((LuaTable) obj);
        if (array == null || array.size() <= 0) return;
        ArrayList<LuaBean> arrayList = new ArrayList<>();
        for (LuaTable mLuaTable : array) {
            LuaBean luaBean = new LuaBean();
            luaBean.setAppDesc((String) mLuaTable.rawget("appDesc"));
            luaBean.setAppIcon((String) mLuaTable.rawget("appIcon"));
            luaBean.setAppId((Double) mLuaTable.rawget("appId"));
            luaBean.setAppName((String) mLuaTable.rawget("appName"));
            luaBean.setAppPackage((String) mLuaTable.rawget("appPackage"));
            luaBean.setAppSize((String) mLuaTable.rawget("appSize"));
            luaBean.setAppStatus((String) mLuaTable.rawget("appStatus"));
            luaBean.setAppType((String) mLuaTable.rawget("appType"));
            luaBean.setAppUrl((String) mLuaTable.rawget("appUrl"));
            luaBean.setAppVersion((String) mLuaTable.rawget("appVersion"));
            luaBean.setCreateTime((String) mLuaTable.rawget("createTime"));
            luaBean.setCreateTimeForamt((String) mLuaTable.rawget("createTimeForamt"));
            luaBean.setFactoryCode((String) mLuaTable.rawget("factoryCode"));
            luaBean.setInstallType((Double) mLuaTable.rawget("installType"));
            luaBean.setUptTime((String) mLuaTable.rawget("uptTime"));
            luaBean.setUptTimeForamt((String) mLuaTable.rawget("uptTimeForamt"));
            arrayList.add(luaBean);
        }
        LogT.w(arrayList.toString());
        ((TextView) findViewById(R.id.tv)).setText(arrayList.toString());


    }

}
