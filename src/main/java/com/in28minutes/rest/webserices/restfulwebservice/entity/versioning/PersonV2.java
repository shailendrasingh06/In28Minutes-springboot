package com.in28minutes.rest.webserices.restfulwebservice.entity.versioning;

import com.in28minutes.rest.webserices.restfulwebservice.entity.versioning.Name;

public class PersonV2 {

    private Name name;

    public PersonV2(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonV2{" +
                "name=" + name +
                '}';
    }
}
