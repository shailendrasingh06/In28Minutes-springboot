package com.in28minutes.rest.webserices.restfulwebservice.controller.filtering;

import com.in28minutes.rest.webserices.restfulwebservice.entity.filtering.FilteredBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    private FilteredBean filteredResponse()
    {
        return new FilteredBean("field2", "field2", "field3");
    }

}
