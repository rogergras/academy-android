package com.sea.academy.list.datasource;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.sea.academy.list.Injector;
import com.sea.academy.list.R;
import com.sea.academy.list.datasource.api.StarWarsApi;
import com.sea.academy.list.base.rest.DefaultRestAdapter;
import com.sea.academy.list.entity.People;
import com.sea.academy.list.util.Validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DataSourceImpl implements DataSource {

    private static final String TAG = "DataSourceImpl";
    

    private static final String SHARED_FILE_NAME = "my_shared_preferences_file_name";
    private static final String USERNAME_KEY = "username";


    private List<People> cachedPeopleList;
    private SharedPreferences sharedPreferences;
    private StarWarsApi starWarsApi;

    public DataSourceImpl(Context applicationContext) {

        Validator.checkNull(applicationContext);

        sharedPreferences = applicationContext.getApplicationContext()
                .getSharedPreferences(SHARED_FILE_NAME, Context.MODE_PRIVATE);

        cachedPeopleList = new ArrayList<>();

        // WARNING: Hard process must be done outside of a constuctor
        // Think about switch init to "public" and call from outside of the class
        init(applicationContext);
    }

    private void init(Context applicationContext) {
        initRestAdapter(applicationContext);
    }

    private void initRestAdapter(Context applicationContext) {
        DefaultRestAdapter starwarsRestAdapter =
                Injector.getDefaultRestAdapter(applicationContext
                        .getString(R.string.starwars_base_url));
        starWarsApi = starwarsRestAdapter.createService(StarWarsApi.class);
    }

    @Override
    public List<People> getPeople() {

        if(cachedPeopleList.isEmpty()) {
            try {
                cachedPeopleList = starWarsApi
                        .getAllPeople()
                        .execute()
                        .body()
                        .getResults();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }

        return Collections.unmodifiableList(cachedPeopleList);
    }

    @Override
    public String getUsername() {
        return sharedPreferences.getString(USERNAME_KEY, "");
    }

    @Override
    public void setUsername(String username) {

        Validator.checkNull(username);

        // Don't forget to call "commit" or "apply"
        sharedPreferences.edit().putString(USERNAME_KEY, username).commit();
    }

    @Override
    public void logout() {
        // Don't forget to call "commit" or "apply"
        sharedPreferences.edit().putString(USERNAME_KEY, null).commit();
    }

}
