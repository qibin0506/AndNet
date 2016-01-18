package org.loader.andnet.net;

/**
 * Created by qibin on 2015/11/29.
 */
public abstract class AbsHttpStack<T> implements INetStack<T> {

    protected boolean debug;

    public void debug(boolean debug) {
        this.debug = debug;
    }

    public boolean isDebugMode() {
        return this.debug;
    }

    @Override
    public void post(final String url, final String json,
                     final Net.Parser<T> parser,
                     final Net.Callback<T> callback, final Object tag) {
        throw new UnsupportedOperationException("you must implement this operation by yourself!");
    }

    @Override
    public void put(final String url, final RequestParams params,
                    final Net.Parser<T> parser,
                    final Net.Callback<T> callback, final Object tag) {
        throw new UnsupportedOperationException("you must implement this operation by yourself!");
    }

    @Override
    public void put(final String url, final String json,
                    final Net.Parser<T> parser,
                    final Net.Callback<T> callback, final Object tag) {
        throw new UnsupportedOperationException("you must implement this operation by yourself!");
    }

    @Override
    public void delete(final String url, final Net.Parser<T> parser,
                       final Net.Callback<T> callback, final Object tag) {
        throw new UnsupportedOperationException("you must implement this operation by yourself!");
    }

    /**
     * 请求成功
     *
     * @param parser
     * @param callback
     * @param response
     */
    @Override
    public void onNetResponse(final Net.Parser<T> parser,
                              final Net.Callback<T> callback,
                              final String response) {
        if(isDebugMode()) System.out.println(response);
        if (callback == null) return;
        if (parser == null) {
            Result<T> result = new Result<T>();
            result.setStatus(Result.ERROR);
            result.setMsg(Net.ERR_PARSE_MSG);
            callback.callback(result);
            return;
        }

        Result<T> result = parser.parse(response);
        callback.callback(result);
    }

    /**
     * 请求失败
     *
     * @param callback
     * @param msg
     */
    @Override
    public void onError(final Net.Callback<T> callback, final String msg) {
        if(isDebugMode()) System.out.println(msg);
        if (callback == null) return;

        Result<T> result = new Result<T>();
        result.setStatus(Result.ERROR);
        result.setMsg(msg);
        callback.callback(result);
    }
    
    @Override
    public LinkedHashMap<String, String> headers() {
        return null;
    }
}
