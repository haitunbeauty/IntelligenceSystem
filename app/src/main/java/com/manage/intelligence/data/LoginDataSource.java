package com.manage.intelligence.data;

import com.manage.intelligence.base.MyApplication;
import com.manage.intelligence.data.db.AppDatabase;
import com.manage.intelligence.data.model.LoggedInUser;
import com.manage.intelligence.data.model.User;
import com.manage.intelligence.utils.SharedPrefsUtil;

import java.io.IOException;
import java.util.List;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
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


            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
