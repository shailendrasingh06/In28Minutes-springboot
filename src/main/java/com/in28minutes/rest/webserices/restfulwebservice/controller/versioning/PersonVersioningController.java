package com.in28minutes.rest.webserices.restfulwebservice.controller.versioning;

import com.in28minutes.rest.webserices.restfulwebservice.entity.versioning.Name;
import com.in28minutes.rest.webserices.restfulwebservice.entity.versioning.PersonV1;
import com.in28minutes.rest.webserices.restfulwebservice.entity.versioning.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

//    Versioning API based upon URI

    @GetMapping("v1/person")
    public PersonV1 getFirstVersionPerson() {
        PersonV1 personV1 = new PersonV1("Shailendra");
        return personV1;
    }

    @GetMapping("v2/person")
    public PersonV2 getSecondVersionPerson() {
        PersonV2 personV2 = new PersonV2(new Name("Shailendra", "Singh"));
        return personV2;
    }

//    Versioning based upon request params

    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstVersionPersonUsingRequestParams() {
        PersonV1 personV1 = new PersonV1("Shailendra");
        return personV1;
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionPersonUsingRequestParams() {
        PersonV2 personV2 = new PersonV2(new Name("Shailendra", "Singh"));
        return personV2;
    }

    //    Versioning based upon request header

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionPersonUsingRequestHeader() {
        PersonV1 personV1 = new PersonV1("Shailendra");
        return personV1;
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionPersonUsingRequestHeader() {
        PersonV2 personV2 = new PersonV2(new Name("Shailendra", "Singh"));
        return personV2;
    }

    //    Versioning based upon media type or accept header

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionPersonUsingMediaType() {
        PersonV1 personV1 = new PersonV1("Shailendra");
        return personV1;
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionPersonUsingMediaType() {
        PersonV2 personV2 = new PersonV2(new Name("Shailendra", "Singh"));
        return personV2;
    }


}
