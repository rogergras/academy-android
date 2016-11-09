package com.sea.academy.list.people;

import com.sea.academy.list.base.LogoutContract;
import com.sea.academy.list.entity.People;

import java.util.List;

public interface PeopleListContract extends LogoutContract {
    void show(People people);
    void appendOnTitle(String title);
    void update(List<People> people);
}
