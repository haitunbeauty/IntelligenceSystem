package com.manage.intelligence.data;

import com.alibaba.fastjson.JSON;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.manage.intelligence.base.MyApplication;
import com.manage.intelligence.bean.login.LoginParam;
import com.manage.intelligence.data.db.AppDatabase;
import com.manage.intelligence.data.model.LoggedInUser;
import com.manage.intelligence.data.model.User;
import com.manage.intelligence.urls.URLconstant;
import com.manage.intelligence.utils.SharedPrefsUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            //{"securitycode":"09E4247EFB290F801B848F85630BCF6E","data": {"ACCOUNT": "APPUSER01","PASSWORD": "123456"}}
            // TODO: handle loggedInUser authentication
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObjectData = new JSONObject();
            jsonObject.put("securitycode","09E4247EFB290F801B848F85630BCF6E");
            jsonObjectData.put("ACCOUNT","APPUSER01");
            jsonObjectData.put("PASSWORD","123456");
            jsonObject.put("data",jsonObjectData);

//            AndroidNetworking.post("http://39.100.40.204:8088/APPService.asmx?op=Login")
            AndroidNetworking.post("http://39.100.40.204:8088/WebService1.asmx?op=HelloWorld")
//                    .addUrlEncodeFormBodyParameter(jsonObject)
                    .setTag("login")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // do anything with response
                        String res = response.toString();


                        }
                        @Override
                        public void onError(ANError error) {
                            // handle error
                            String err = error.toString();
                        }
                    });


            String param = "{\"securitycode\":\"09E4247EFB290F801B848F85630BCF6E\",\"data\": {\"ACCOUNT\": \"APPUSER01\",\"PASSWORD\": \"123456\"}}";

//            OkGo.post(URLconstant.LOGIN_URL)//
//                    .tag(this)//
//                    .upJson(param)//
//                    .execute(new PostCallBack() {
//                        @Override
//                        public void onSuccess(Response response) {
//                            String result = response.toString();
//                        }
//
//                        @Override
//                        public void onError(Response response) {
//                            String result = response.toString();
//                        }
//                    });


            LoginParam loginParam = new LoginParam();
            LoginParam.DataBean dataBean = new LoginParam.DataBean();
            dataBean.setACCOUNT("APPUSER01");
            dataBean.setPASSWORD("123456");
            loginParam.setSecuritycode("09E4247EFB290F801B848F85630BCF6E");
            loginParam.setData(dataBean);
            String loginParamString = JSON.toJSONString(loginParam);


//            OkHttpUtils.post().url(URLconstant.LOGIN_URL).params(params).build().execute(new Callback() {
//            OkHttpUtils.post().url(URLconstant.LOGIN_URL).addParams("params",loginParamString).build().execute(new Callback() {
            OkHttpUtils.post().url(URLconstant.LOGIN_URL).build().execute(new Callback() {
                @Override
                public Object parseNetworkResponse(Response response, int id) throws Exception {
                    return response;
                }

                @Override
                public void onError(Call call, Exception e, int id) {
                    String result = e.toString();
                }

                @Override
                public void onResponse(Object response, int id) {
                    String result = response.toString();
                }
            });



            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");

            User user = new User();
            user.setId(1);
            user.setUserName("FakeName1");

            AppDatabase appDB = MyApplication.getInstance().getAppDB();
            List<User> allUser = appDB.userDao().getAllUser();
            boolean isRememberPassword = SharedPrefsUtil.getBoolean(MyApplication.getInstance(), "remember_password", "remember_password");
            if ((allUser == null || allUser.size() < 1) && isRememberPassword){//不包含  才存
                appDB.userDao().insertOneUser(user);
            }else {
                appDB.userDao().deleteAll();
            }

            return new Result.Error(new IOException("登录失败"));

//            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }

}
