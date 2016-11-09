package com.sea.academy.list.people;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sea.academy.list.Injector;
import com.sea.academy.list.NavigationHandler;
import com.sea.academy.list.R;
import com.sea.academy.list.base.view.BaseActivity;
import com.sea.academy.list.base.LogoutPresenter;
import com.sea.academy.list.base.OnPeopleClickListener;
import com.sea.academy.list.entity.People;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeopleListActivity extends BaseActivity implements PeopleListContract {

    @BindView(R.id.recyclerview) RecyclerView recyclerview;
    private PeopleListPresenter presenter;
    private PeopleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        adapter = new PeopleAdapter();
        recyclerview.setAdapter(adapter);

        presenter = Injector.getPeopleListPresenter(this);

        adapter.setOnPeopleClickListener(new OnPeopleClickListener() {
            @Override
            public void onPeopleClick(People people) {
                presenter.selected(people);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.checkLoggedIn();
    }

    @Override
    protected LogoutPresenter getLogoutPresenter() {
        return presenter;
    }

    @Override
    public void show(People people) {
        NavigationHandler.selectedPeople(PeopleListActivity.this, people);
    }

    @Override
    public void appendOnTitle(String title) {
        setTitle(String.format(getString(R.string.welcome_arg), title));
    }

    @Override
    public void update(List<People> people) {
        adapter.update(people);
    }

}
