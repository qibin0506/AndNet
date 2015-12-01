package org.loader.andnet.net;

import java.lang.ref.WeakReference;

/**
 * Created by qibin on 2015/11/29.
 */
public abstract class AbsHttpStack<T> implements INetStack<T> {

    /**
     * 请求成功
     *
     * @param parser
     * @param callback
     * @param response
     */
    @Override
    public void onNetResponse(WeakReference<Net.Parser<T>> parser,
                              WeakReference<Net.Callback<T>> callback,
                              String response) {
        if (callback.get() == null) return;
        if (parser.get() == null) {
            Result<T> result = new Result<T>();
            result.setStatus(Result.ERROR);
            result.setMsg(Net.ERR_PARSE_MSG);
            callback.get().callback(result);
            return;
        }

        Result<T> result = parser.get().parse(response);
        callback.get().callback(result);
    }

    /**
     * 请求失败
     *
     * @param callback
     * @param msg
     */
    @Override
    public void onError(WeakReference<Net.Callback<T>> callback, String msg) {
        if (callback.get() == null) return;

        Result<T> result = new Result<T>();
        result.setStatus(Result.ERROR);
        result.setMsg(msg);
        callback.get().callback(result);
    }
}
