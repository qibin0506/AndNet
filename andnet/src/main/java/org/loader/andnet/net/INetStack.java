package org.loader.andnet.net;


import java.lang.ref.WeakReference;

/**
 * Created by qibin on 2015/11/29.
 */
public interface INetStack<T> {

    void get(final String url, final WeakReference<Net.Parser<T>> parser,
             final WeakReference<Net.Callback<T>> callback);

    void post(final String url, final RequestParams params,
              final WeakReference<Net.Parser<T>> parser,
              final WeakReference<Net.Callback<T>> callback);

    void onNetResponse(final WeakReference<Net.Parser<T>> parser,
                       final WeakReference<Net.Callback<T>> callback,
                       final String response);

    void onError(final WeakReference<Net.Callback<T>> callback, final String msg);

    void cancel(String tag);
}
