package org.loader.nettest;


import org.loader.andnet.okhttp.OkHttpStack;

import java.util.LinkedHashMap;

/**
 * Created by lenovo on 2015/11/30.
 */
public class OkHttpHeaderStack<T> extends OkHttpStack<T> {

    @Override
    public LinkedHashMap<String, String> headers() {
        LinkedHashMap<String, String> headers = new LinkedHashMap<>();
        headers.put("name", "header");
        return headers;
    }
}
