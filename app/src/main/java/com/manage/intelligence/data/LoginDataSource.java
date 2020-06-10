package com.manage.intelligence.data;

import android.Manifest;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.manage.intelligence.base.MyApplication;
import com.manage.intelligence.data.db.AppDatabase;
import com.manage.intelligence.data.model.LoggedInUser;
import com.manage.intelligence.data.model.User;
import com.manage.intelligence.utils.SharedPrefsUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

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

            AndroidNetworking.post("http://39.100.40.204:8088/APPService.asmx?op=Login")
                    .addUrlEncodeFormBodyParameter(jsonObject)
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
