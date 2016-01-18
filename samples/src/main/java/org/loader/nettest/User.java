package org.loader.nettest;

import org.loader.andnet.helper.Annotation;
import org.loader.andnet.helper.IBaseBean;

/**
 * Created by lenovo on 2015/11/30.
 */
public class User implements IBaseBean {
    @Annotation(key = "name")
    private String name;
    @Annotation(key = "age")
    private int age;
    @Annotation(key = "city")
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name=" + name + ",age=" + age + ",city=" + city;
    }
}
