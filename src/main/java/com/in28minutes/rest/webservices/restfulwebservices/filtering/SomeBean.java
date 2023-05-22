package com.in28minutes.rest.webservices.restfulwebservices.filtering;


import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("SomeBeanFilter")
public class SomeBean {
    String field1;

    //@JsonIgnore
    String field2;
    String field3;

    public SomeBean(String filed1, String filed2, String filed3) {
        this.field1 = filed1;
        this.field2 = filed2;
        this.field3 = filed3;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }

    @Override
    public String toString() {
        return "SomeBean{" +
                "filed1='" + field1 + '\'' +
                ", filed2='" + field2 + '\'' +
                ", filed3='" + field3 + '\'' +
                '}';
    }
}
