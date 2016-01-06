package org.loader.andnet.net;


/**
 * Created by qibin on 2015/11/29.
 */
public interface INetStack<T> {

    void get(final String url, final Net.Parser<T> parser,
             final Net.Callback<T> callback, final Object tag);

    void post(final String url, final RequestParams params,
              final Net.Parser<T> parser,
              final Net.Callback<T> callback,
              final Object tag);

    void post(final String url, final String json,
              final Net.Parser<T> parser,
              final Net.Callback<T> callback,
              final Object tag);

    void put(final String url, final RequestParams params,
             final Net.Parser<T> parser,
             final Net.Callback<T> callback,
             final Object tag);

    void put(final String url, final String json,
             final Net.Parser<T> parser,
             final Net.Callback<T> callback,
             final Object tag);

    void delete(final String url, final Net.Parser<T> parser,
                final Net.Callback<T> callback, final Object tag);

    void onNetResponse(final Net.Parser<T> parser,
                       final Net.Callback<T> callback,
                       final String response);

    void onError(final Net.Callback<T> callback, final String msg);

    void cancel(final Object tag);
}
