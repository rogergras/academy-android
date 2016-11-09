package com.sea.academy.list.datasource;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sea.academy.list.entity.People;
import com.sea.academy.list.util.Constants;
import com.sea.academy.list.util.FileUtil;
import com.sea.academy.list.util.Validator;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MockedDataSourceImpl implements DataSource {

    private static final String TAG = "DataSourceImpl";
    

    private static final String SHARED_FILE_NAME = "my_shared_preferences_file_name";
    private static final String USERNAME_KEY = "username";


    private List<People> peopleList;
    private SharedPreferences sharedPreferences;

    public MockedDataSourceImpl(Context applicationContext) {

        Validator.checkNull(applicationContext);

        sharedPreferences = applicationContext.getApplicationContext()
                .getSharedPreferences(SHARED_FILE_NAME, Context.MODE_PRIVATE);

        // WARNING: Hard process must be done outside of a constuctor
        // Think about switch init to "public" and call from outside of the class
        init(applicationContext);
    }

    private void init(Context applicationContext) {
        peopleList = getPeopleFromJsonAssets(applicationContext);
    }

    private List<People> getPeopleFromJsonAssets(Context context) {
        List<People> people =  new ArrayList<>();

        try {
            // Why to use json parser library?
            // Manually option, https://www.tutorialspoint.com/android/android_json_parser.htm
            // Less code is better!
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<People>>(){}.getType();
            return gson.fromJson(FileUtil.getFromAssets(context,
                    Constants.PEOPLE_ASSETS_FILE), listType);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }

        return people;
    }

    @Override
    public List<People> getPeople() {
        return Collections.unmodifiableList(peopleList);
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
