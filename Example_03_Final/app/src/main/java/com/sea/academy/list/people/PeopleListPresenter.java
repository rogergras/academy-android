package com.sea.academy.list.people;

import com.sea.academy.list.Injector;
import com.sea.academy.list.base.LogoutPresenter;
import com.sea.academy.list.datasource.DataSource;
import com.sea.academy.list.entity.People;
import com.sea.academy.list.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class PeopleListPresenter implements LogoutPresenter {

    private final PeopleListContract contract;
    private final DataSource dataSource;
    private final Executor backgroundExecutor;
    private final Executor mainExecutor;

    public PeopleListPresenter(PeopleListContract contract, DataSource dataSource) {

        Validator.checkNull(contract);
        Validator.checkNull(dataSource);

        this.contract = contract;
        this.dataSource = dataSource;

        backgroundExecutor = Injector.getBackgroundExecutor();
        mainExecutor = Injector.getMainExecutor();

        contract.appendOnTitle(dataSource.getUsername());
        contract.update(new ArrayList<People>());

        // Network calls must be done on a different thread
        backgroundExecutor.execute(new Runnable() {
            @Override
            public void run() {
                final List<People> newPeople =
                        PeopleListPresenter.this.dataSource.getPeople();

                // Screen operations must be done on main thread
                mainExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        PeopleListPresenter.this.contract.update(newPeople);
                    }
                });

            }
        });

    }

    public void selected(People people) {
        contract.show(people);
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
