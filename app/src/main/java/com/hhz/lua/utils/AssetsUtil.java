package com.hhz.lua.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by haohz on 2018/1/29.
 */

public class AssetsUtil {
    public static String loadlocalData(Context context,String assetsFile) {
        AssetManager assetManager = context.getAssets();
        try {
            // InputStream is = assetManager.open("city.json");
            InputStream is = assetManager.open(assetsFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder stringBuffer = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }
            if(TextUtils.isEmpty(stringBuffer.toString())){
                Toast.makeText(context,"文件内容为空",Toast.LENGTH_SHORT).show();
            }
            LogT.w("本地文件内容：\n"+stringBuffer.toString());
            return stringBuffer.toString();
        } catch (IOException e) {
            Toast.makeText(context,"读本地文件失败",Toast.LENGTH_SHORT).show();
            return "";
        }
    }
}