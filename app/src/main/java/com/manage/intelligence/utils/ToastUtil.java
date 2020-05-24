package com.manage.intelligence.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @author libao
 * @date 2020/05/24 0:19
 * @describe TODO Toast 提示类
 */
public class ToastUtil {


    private static Toast toast;

    /**
     * show toast
     *
     * @param context context
     * @param msg     message string
     */
    public static void show(Context context, String msg) {
        toast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT);
        toast.setText(msg);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * show toast
     *
     * @param context context
     * @param msgId   message resource id
     */
    public static void show(Context context, int msgId) {
        toast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT);
        toast.setText(msgId);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
