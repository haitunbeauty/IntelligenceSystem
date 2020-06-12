package com.manage.intelligence.ui.login;

import android.Manifest;
import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.manage.intelligence.R;
import com.manage.intelligence.base.MyApplication;
import com.manage.intelligence.data.db.AppDatabase;
import com.manage.intelligence.data.model.User;
import com.manage.intelligence.ui.login.LoginViewModel;
import com.manage.intelligence.ui.login.LoginViewModelFactory;
import com.manage.intelligence.ui.main.MainActivity;
import com.manage.intelligence.utils.SharedPrefsUtil;
import com.manage.intelligence.utils.ToastUtil;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class LoginActivity extends AppCompatActivity {

    private static final int INTERNET = 1;
    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        AppDatabase appDB = MyApplication.getInstance().getAppDB();
        List<User> allUser = appDB.userDao().getAllUser();
        if (allUser != null && allUser.size() > 0){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final CheckBox rememberCheckBox = findViewById(R.id.remember_password_cb);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
//                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {//软键盘的完成按钮
                    LiveData<LoginFormState> loginFormState = loginViewModel.getLoginFormState();
                    LoginFormState value = loginFormState.getValue();
                    if (value.isDataValid()){
                        if (rememberCheckBox.isChecked()){//是否记住密码
                            SharedPrefsUtil.set("remember_password","remember_password",true);
                        }else {
                            SharedPrefsUtil.set("remember_password","remember_password",false);
                        }

                        loginViewModel.login(usernameEditText.getText().toString(),
                                passwordEditText.getText().toString());
                    }else {
                        ToastUtil.show(LoginActivity.this,"请输入正确的账号密码");
                    }


                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] perms = {Manifest.permission.INTERNET};
//                EasyPermissions.requestPermissions(LoginActivity.this, "获取网络权限",INTERNET, perms);

                if (EasyPermissions.hasPermissions(LoginActivity.this, perms)) {
                    // Already have permission, do the thing
                    loadingProgressBar.setVisibility(View.VISIBLE);
                    if (rememberCheckBox.isChecked()){//是否记住密码
                        SharedPrefsUtil.set("remember_password","remember_password",true);
                    }else {
                        SharedPrefsUtil.set("remember_password","remember_password",false);
                    }

                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());

                } else {
                    // Do not have permissions, request them now
                    EasyPermissions.requestPermissions(LoginActivity.this, "获取网络权限",INTERNET, perms);
                }



            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {

        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
