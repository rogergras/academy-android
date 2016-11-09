package com.sea.academy.list.datasource;

import com.sea.academy.list.entity.People;

import java.util.List;


public interface DataSource {

    List<People> getPeople();
    /**
     * @return Empty string if does not exist.
     */
    String getUsername();

    void setUsername(String username);

    void logout();
}
