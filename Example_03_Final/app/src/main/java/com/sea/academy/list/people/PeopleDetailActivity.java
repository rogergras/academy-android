package com.sea.academy.list.people;

import android.os.Bundle;
import android.widget.TextView;

import com.sea.academy.list.Injector;
import com.sea.academy.list.R;
import com.sea.academy.list.base.view.BaseActivity;
import com.sea.academy.list.base.LogoutPresenter;
import com.sea.academy.list.entity.People;
import com.sea.academy.list.util.IntentUtil;


public class PeopleDetailActivity extends BaseActivity implements PeopleDetailContract {

    private PeopleDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        presenter = Injector.getPeopleDetailPresenter(this);

        TextView tvPeopleName = findView(R.id.tvPeopleName);
        tvPeopleName.setText(((People)getIntent().getParcelableExtra(IntentUtil.PEOPLE_EXTRA_KEY)).getName());
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
    public void appendOnTitle(String title) {
        setTitle(String.format(getString(R.string.logged_arg), title));
    }

}
