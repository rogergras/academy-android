package com.sea.academy.list.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sea.academy.list.Injector;
import com.sea.academy.list.NavigationHandler;
import com.sea.academy.list.R;
import com.sea.academy.list.base.view.BaseActivity;
import com.sea.academy.list.base.LogoutPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract {

    @BindView(R.id.etUsername) EditText etUsername;
    @BindView(R.id.btnLogin) Button btnLogin;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        presenter = Injector.getLoginPresenter(this);
    }

    @Override
    protected LogoutPresenter getLogoutPresenter() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loginActionDone() {
        NavigationHandler.loginDone(LoginActivity.this);
    }

    @Override
    public void loginActionDone(String username) {
        NavigationHandler.loginDone(LoginActivity.this, username);
    }

    @Override
    public void showLogin() {
        // Nothing to do, the screen loads normally
    }

    @OnClick(R.id.btnLogin)
    public void loginclick(View view) {
        String username = etUsername.getText().toString();

        if(validateUsername(username)) {
            presenter.loginAction(username);
        } else {
            loginEmptyError();
        }
    }


    private void loginEmptyError() {
        etUsername.setError(String
                .format(getString(R.string.empty_arg), getString(R.string.username)));
    }

    private boolean validateUsername(String username) {
        return !TextUtils.isEmpty(username);
    }

}
