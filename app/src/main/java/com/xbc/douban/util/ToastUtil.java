package com.xbc.douban.util;

import android.text.TextUtils;
import android.widget.Toast;

import com.xbc.douban.MainApplication;

public class ToastUtil {
    public static String lastContent;

    /**
     * 2 秒内不显示相同toast
     *
     * @param content
     */
    public static void show(String content) {
        if (TextUtils.equals(lastContent, content)) {
            return;
        }
        if (!TextUtils.isEmpty(content)) {
            lastContent = content;
            Toast toast = Toast.makeText(MainApplication.getApplication(), content, Toast.LENGTH_SHORT);
            toast.show();
            toast.getView().postDelayed(new Runnable() {
                @Override
                public void run() {
                    lastContent = null;
                }
            }, 2000);
        }
    }

    public static void show(int resId) {
        String content = MainApplication.getApplication().getString(resId);
        show(content);
    }
}
