package com.sea.academy.list.base.view;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sea.academy.list.NavigationHandler;
import com.sea.academy.list.R;
import com.sea.academy.list.base.LogoutContract;
import com.sea.academy.list.base.LogoutPresenter;

/**
 * Base class with Activity utilities.
 */
public abstract class BaseActivity extends AppCompatActivity implements LogoutContract {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initToolBar();
    }

    protected void initToolBar() {
        Toolbar toolbar = findView(R.id.toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_toolbar_arrow);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            getLogoutPresenter().logoutAction();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected abstract LogoutPresenter getLogoutPresenter();

    @SuppressWarnings({"unchecked"})
    public <T extends View> T findView(@IdRes int id) {
        return (T) findViewById(id);
    }

    @Override
    public void logout() {
        NavigationHandler.logoutDone(this);
    }

}
