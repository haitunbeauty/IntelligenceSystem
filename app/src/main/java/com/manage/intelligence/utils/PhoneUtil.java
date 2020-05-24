package com.manage.intelligence.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.manage.intelligence.base.MyApplication;

import java.lang.reflect.Field;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
*作者:libao
*添加时间:2020/5/24 11:58
*修改人和时间: 2020/5/24 11:58
*说明:
*/
public class PhoneUtil {


    /**
     * 屏幕尺寸
     * @param context
     * @return DisplayMetrics
     */
    public static DisplayMetrics getScreenMetrics(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    /**
     * 屏幕宽度 如果1080
     * @param context
     * @return 1080
     */
    public static int getScreenWidth(Activity context) {
        return getScreenMetrics(context).widthPixels;
    }

    /**
     * 屏幕高度 如果1920
     * @param context
     * @return 1920
     */
    public static int getScreenHeight(Activity context) {
        return getScreenMetrics(context).heightPixels;
    }


    /**
     * 屏幕密度DPI（120 / 160 / 240）
     * @param context
     * @return
     */
    public static int getDensityDip(Activity context) {
        return getScreenMetrics(context).densityDpi;
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * @param dpValue
     * @return
     */
    public static int dip2px(float dpValue) {
        Resources r = Resources.getSystem();
        final float scale = r.getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        Resources r = Resources.getSystem();
        final float scale = r.getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 手型号 如果 Redmi Note
     * @return 手机型号
     */
    public static String getPhoneTypeCode() {
        return android.os.Build.MODEL;
    }

    /**
     * 手机制造厂 如 xiaomi huawei meizu
     * @return 手机制造厂
     */
    public static String getPhoneManufacturer() {
        return android.os.Build.MANUFACTURER;
    }


    /**
     * 系统版本号 8.1.0 9.0
     * @return 8.1.0 9.0
     */
    public static String getOSVersion() {
        return android.os.Build.VERSION.RELEASE;
    }



    /**
     * 获取imei
     * @param context
     * @return IMEI码
     */
    @SuppressLint("MissingPermission")
    public static String getPhoneIMEICode(Context context) {
        TelephonyManager tm = (TelephonyManager) context.
                getSystemService(Context.TELEPHONY_SERVICE);

        String imei;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            imei = getMacAddress();
        } else if (Build.VERSION.SDK_INT > 25) {
            imei = tm.getImei(0);
        } else {
            imei = tm.getDeviceId();
        }

        if (!TextUtils.isEmpty(imei)){
            return imei;
        } else {
            return "无记录";
        }
    }




    /**
     * 是否版号相同
     * @param versionCode 传入版本号
     * @return 相同 true
     */
    public static boolean isVersionSame(String versionCode){
        return getAppVersion().equals(versionCode);
    }

    /**
     * 获取当前app版本号
     * @return 如 1.0.0
     */
    public static String getAppVersion() {
        try {
            MyApplication context = MyApplication.getInstance();
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            return context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 强制隐藏键盘
     */
    private static InputMethodManager getInputMethodManager(Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        return imm;
    }

    /**
     * 隐藏键盘
     * @param context 上下文
     * @param et 编辑框editText
     */
    public static void hideKeyboard(Context context, EditText et){
        InputMethodManager manager = getInputMethodManager(context);
        if(manager.isActive(et)){
            manager.hideSoftInputFromWindow(et.getWindowToken(), 0);
        }
    }

    public static void hideKeyboard(Activity context){

        if (context != null){
            View vew = context.getCurrentFocus();
            if (vew != null) {
                getInputMethodManager(context).hideSoftInputFromWindow(vew.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     *  弹出键盘
     * @param context 上下文
     * @param et 编辑框editText
     */
    public static void showKeyboard(Context context, EditText et){
        getInputMethodManager(context).showSoftInput(et, 0);
    }



    /**
     * 验证手机号前三位
     * @param phoneNum 手机号
     * @return true
     */
    public static boolean mobilePhoneVerify(String phoneNum){

        if (!TextUtils.isEmpty(phoneNum) && phoneNum.length() == 11){
            String regEx = "^1[3-9][0-9]\\d{8}$";
            Pattern pattern = Pattern.compile(regEx);
            // 忽略大小写的写法
            Matcher matcher = pattern.matcher(phoneNum);
            // 字符串是否与正则表达式相匹配
            return matcher.matches();
        }
        return false;
    }


    /**
     * 座机号
     * @param phoneNum 手机号
     * @return true
     */
    public static boolean phoneVerify(String phoneNum){
        if (!TextUtils.isEmpty(phoneNum) && (phoneNum.length() == 11 || phoneNum.length() == 12)){
            return true;
        }
        return false;
    }

    /**  通过反射获取状态栏的高度 */
    public static int getStateBar2(Context context) {
        Class c = null;
        int statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }


    /**
     * GoTo Open Self Setting Layout
     * Compatible Mainstream Models 兼容市面主流机型
     *
     * @param context
     */
    public static void jumpStartInterface(Context context) {
        Intent intent = new Intent();
        try {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName componentName = null;
            if (getPhoneManufacturer().equals("Xiaomi")) { // 红米Note4测试通过
                componentName = new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity");
            } else if (getPhoneManufacturer().equals("Letv")) { // 乐视2测试通过
                intent.setAction("com.letv.android.permissionautoboot");
            } else if (getPhoneManufacturer().equals("samsung")) { // 三星Note5测试通过
                componentName = new ComponentName("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.ram.AutoRunActivity");
            } else if (getPhoneManufacturer().equals("HUAWEI")) { // 华为测试通过
                componentName = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity");
            } else if (getPhoneManufacturer().equals("vivo")) { // VIVO测试通过
                componentName = ComponentName.unflattenFromString("com.iqoo.secure/.safeguard.PurviewTabActivity");
            } else if (getPhoneManufacturer().equals("Meizu")) { //万恶的魅族
                // 通过测试，发现魅族是真恶心，也是够了，之前版本还能查看到关于设置自启动这一界面，系统更新之后，完全找不到了，心里默默Fuck！
                // 针对魅族，我们只能通过魅族内置手机管家去设置自启动，所以我在这里直接跳转到魅族内置手机管家界面，具体结果请看图
                componentName = ComponentName.unflattenFromString("com.meizu.safe/.permission.PermissionMainActivity");
            } else if (getPhoneManufacturer().equals("OPPO")) { // OPPO R8205测试通过
                componentName = ComponentName.unflattenFromString("com.oppo.safe/.permission.startup.StartupAppListActivity");
            } else if (getPhoneManufacturer().equals("ulong")) { // 360手机 未测试
                componentName = new ComponentName("com.yulong.android.coolsafe", ".ui.activity.autorun.AutoRunListActivity");
            } else {
                // 以上只是市面上主流机型，由于公司你懂的，所以很不容易才凑齐以上设备
                // 针对于其他设备，我们只能调整当前系统app查看详情界面
                // 在此根据用户手机当前版本跳转系统设置界面
                if (Build.VERSION.SDK_INT >= 9) {
                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.fromParts("package", context.getPackageName(), null));
                } else if (Build.VERSION.SDK_INT <= 8) {
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                    intent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
                }
            }
            intent.setComponent(componentName);
            context.startActivity(intent);
        } catch (Exception e) {//抛出异常就直接打开设置页面
            intent = new Intent(Settings.ACTION_SETTINGS);
            context.startActivity(intent);
        }
    }


    /**
     * 打电话
     * @param context 上下文
     * @param phoneNumber 电话号码
     */
    public static void call(Context context, String phoneNumber){
        if(TextUtils.isEmpty(phoneNumber)){
            return;
        }

        Intent intent = new Intent();
        intent.setAction("android.intent.action.CALL");
        intent.setData(Uri.parse("tel:"+phoneNumber));
        context.startActivity(intent);
    }


    /**
     * 获取手机的mac 地址
     * @return
     */
    public static String getMacAddress() {

        List<NetworkInterface> interfaces = null;
        try {
            interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface networkInterface : interfaces) {
                if (networkInterface != null && TextUtils.isEmpty(networkInterface.getName()) == false) {
                    if ("wlan0".equalsIgnoreCase(networkInterface.getName())) {
                        byte[] macBytes = networkInterface.getHardwareAddress();
                        if (macBytes != null && macBytes.length > 0) {
                            StringBuilder str = new StringBuilder();
                            for (byte b : macBytes) {
                                str.append(String.format("%02X:", b));
                            }
                            if (str.length() > 0) {
                                str.deleteCharAt(str.length() - 1);
                            }
                            return str.toString();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }
}


