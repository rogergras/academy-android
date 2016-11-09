package com.sea.academy.list;

import android.content.Context;

import com.sea.academy.list.base.MainExecutor;
import com.sea.academy.list.datasource.DataSource;
import com.sea.academy.list.datasource.MockedDataSourceImpl;
import com.sea.academy.list.login.LoginContract;
import com.sea.academy.list.login.LoginPresenter;
import com.sea.academy.list.people.PeopleDetailContract;
import com.sea.academy.list.people.PeopleDetailPresenter;
import com.sea.academy.list.people.PeopleListContract;
import com.sea.academy.list.people.PeopleListPresenter;

import java.util.concurrent.Executor;


public final class Injector {

    private static DataSource dataSource;
    private static Executor backgroundExecutor;
    private static Executor mainExecutor;

    public Injector(Context applicationContext) {
        dataSource = new MockedDataSourceImpl(applicationContext);
        mainExecutor = new MainExecutor();
        backgroundExecutor = mainExecutor;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static LoginPresenter getLoginPresenter(LoginContract contract) {
        return new LoginPresenter(contract, dataSource);
    }

    public static PeopleListPresenter getPeopleListPresenter(PeopleListContract contract) {
        return new PeopleListPresenter(contract, dataSource);
    }

    public static PeopleDetailPresenter getPeopleDetailPresenter(PeopleDetailContract contract) {
        return new PeopleDetailPresenter(contract, dataSource);
    }

    public static Executor getBackgroundExecutor() {
        return backgroundExecutor;
    }

    public static Executor getMainExecutor() {
        return mainExecutor;
    }

}
