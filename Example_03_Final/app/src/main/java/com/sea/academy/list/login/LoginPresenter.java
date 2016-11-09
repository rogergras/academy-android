package com.sea.academy.list.login;

import com.sea.academy.list.datasource.DataSource;
import com.sea.academy.list.util.Validator;

public class LoginPresenter {

    private final LoginContract contract;
    private final DataSource dataSource;

    public LoginPresenter(LoginContract contract, DataSource dataSource) {

        Validator.checkNull(contract);
        Validator.checkNull(dataSource);

        this.contract = contract;
        this.dataSource = dataSource;

        if("".equals(dataSource.getUsername())) {
            contract.showLogin();
        } else {
            contract.loginActionDone();
        }

    }

    public void loginAction(String username) {
        dataSource.setUsername(username);
        contract.loginActionDone(username);
    }

}
