package com.sea.academy.list.people;

import com.sea.academy.list.base.LogoutPresenter;
import com.sea.academy.list.datasource.DataSource;
import com.sea.academy.list.util.Validator;

public class PeopleDetailPresenter implements LogoutPresenter {

    private final PeopleDetailContract contract;
    private final DataSource dataSource;

    public PeopleDetailPresenter(PeopleDetailContract contract, DataSource dataSource) {

        Validator.checkNull(contract);
        Validator.checkNull(dataSource);

        this.contract = contract;
        this.dataSource = dataSource;

        contract.appendOnTitle(dataSource.getUsername());
    }

    @Override
    public void logoutAction() {
        dataSource.logout();
        contract.logout();
    }

    @Override
    public void checkLoggedIn() {
        if("".equals(dataSource.getUsername())) {
            logoutAction();
        }
    }

}
