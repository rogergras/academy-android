package com.sea.academy.list.datasource.api;

import com.sea.academy.list.entity.PeopleResult;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StarWarsApi {

    // Get all people service is paginated, this method returns the first page only.
    // To get all pages you have to use with ?page={number} argument
    // Call<PeopleResult> getAllPeople(@Query("page") int page);
    @GET("people/")
    Call<PeopleResult> getAllPeople();

}
