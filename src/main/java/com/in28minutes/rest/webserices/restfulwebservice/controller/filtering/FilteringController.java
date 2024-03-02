package com.in28minutes.rest.webserices.restfulwebservice.controller.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.in28minutes.rest.webserices.restfulwebservice.entity.filtering.FilteredBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    private MappingJacksonValue filteredResponse() {

        FilteredBean filteredBean = new FilteredBean("some1", "some2", "some3");

        MappingJacksonValue jacksonValue = new MappingJacksonValue(filteredBean);

        SimpleBeanPropertyFilter filters = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filters);

        jacksonValue.setFilters(filterProvider);

        return jacksonValue;
    }

    @GetMapping("/filtering/list")
    private MappingJacksonValue filteredResponseList() {
        List<FilteredBean> list = Arrays.asList(new FilteredBean("field2", "field2", "field3"),
                new FilteredBean("samos", "Somthing", "something"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;

    }

}
