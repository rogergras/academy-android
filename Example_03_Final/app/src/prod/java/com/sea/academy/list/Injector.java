package com.sea.academy.list;

import android.content.Context;

import com.sea.academy.list.datasource.DataSource;
import com.sea.academy.list.datasource.DataSourceImpl;
import com.sea.academy.list.base.rest.DefaultRestAdapter;
import com.sea.academy.list.login.LoginContract;
import com.sea.academy.list.login.LoginPresenter;
import com.sea.academy.list.people.PeopleDetailContract;
import com.sea.academy.list.people.PeopleDetailPresenter;
import com.sea.academy.list.people.PeopleListContract;
import com.sea.academy.list.people.PeopleListPresenter;
import com.sea.academy.list.base.treading.BackgroundExecutor;
import com.sea.academy.list.base.treading.MainExecutor;

import java.util.concurrent.Executor;


public final class Injector {

    private static DataSource dataSource;
    private static Executor backgroundExecutor;
    private static Executor mainExecutor;

    public Injector(Context applicationContext) {
        dataSource = new DataSourceImpl(applicationContext);
        backgroundExecutor = new BackgroundExecutor();
        mainExecutor = new MainExecutor();
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

    public static DefaultRestAdapter getDefaultRestAdapter(String baseUrl) {
        return new DefaultRestAdapter(baseUrl);
    }

    public static Executor getBackgroundExecutor() {
        return backgroundExecutor;
    }

    public static Executor getMainExecutor() {
        return mainExecutor;
    }

}
