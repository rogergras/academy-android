package com.sea.academy.list;

import android.app.Activity;
import android.content.Intent;

import com.sea.academy.list.entity.People;
import com.sea.academy.list.login.LoginActivity;
import com.sea.academy.list.people.PeopleDetailActivity;
import com.sea.academy.list.people.PeopleListActivity;
import com.sea.academy.list.util.IntentUtil;
import com.sea.academy.list.util.Validator;

public class NavigationHandler {

    private NavigationHandler() {

    }

    public static void loginDone(LoginActivity activity, String username) {

        Validator.checkNull(activity);
        Validator.checkNull(username);

        Intent intent = new Intent(activity, PeopleListActivity.class);
        intent.putExtra(IntentUtil.USERNAME_EXTRA_KEY, username);
        activity.startActivity(intent);
        activity.finish();
    }

    public static void loginDone(LoginActivity activity) {

        Validator.checkNull(activity);

        Intent intent = new Intent(activity, PeopleListActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    public static void selectedPeople(PeopleListActivity activity, People selectedPeople) {

        Validator.checkNull(activity);
        Validator.checkNull(selectedPeople);

        Intent intent = new Intent(activity, PeopleDetailActivity.class);
        intent.putExtra(IntentUtil.PEOPLE_EXTRA_KEY, selectedPeople);
        activity.startActivity(intent);
    }

    public static void logoutDone(Activity activity) {

        Validator.checkNull(activity);

        Intent intent = new Intent(activity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

}
