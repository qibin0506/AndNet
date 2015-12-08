package org.loader.andnet.net;

/**
 * Created by qibin on 2015/11/29.
 */
public abstract class AbsHttpStack<T> implements INetStack<T> {

    protected boolean debug;

    public void debug(boolean debug) {
        this.debug = debug;
    }

    public boolean isDebug() {
        return this.debug;
    }

    /**
     * 请求成功
     *
     * @param parser
     * @param callback
     * @param response
     */
    @Override
    public void onNetResponse(Net.Parser<T> parser,
                              Net.Callback<T> callback,
                              String response) {
        if(debug) System.out.println(response);

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
    public void onError(Net.Callback<T> callback, String msg) {
        if(debug) System.out.println(msg);
        if (callback == null) return;

        Result<T> result = new Result<T>();
        result.setStatus(Result.ERROR);
        result.setMsg(msg);
        callback.callback(result);
    }
}
