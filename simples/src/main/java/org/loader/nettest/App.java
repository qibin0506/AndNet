package org.loader.nettest;

import android.app.Application;


import org.loader.andnet.net.Net;

import volley.VolleyStack;

/**
 * Created by lenovo on 2015/11/30.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        Net.init(new VolleyStack(this));
//        Net.init(new OkHttpStack());
        OkHttpHeaderStack okHttpStack = new OkHttpHeaderStack();
        okHttpStack.debug(true);
        Net.init(okHttpStack);
    }
}
