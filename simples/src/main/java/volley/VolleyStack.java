package volley;

import android.app.Application;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import org.loader.andnet.net.AbsHttpStack;
import org.loader.andnet.net.Net;
import org.loader.andnet.net.RequestParams;

import java.lang.ref.WeakReference;
import java.util.Map;

/**
 * Created by qibin on 2015/11/29.
 */
public class VolleyStack<T> extends AbsHttpStack<T> {

    private Application mContext;

    public VolleyStack(Application context) {
        mContext = context;
    }

    /**
     * get请求
     *
     * @param url      网址
     * @param parser   解析器
     * @param callback 回调
     */
    @Override
    public void get(String url, WeakReference<Net.Parser<T>> parser,
                    WeakReference<Net.Callback<T>> callback) {
        invoke(Request.Method.GET, url, null, parser, callback);
    }

    /**
     * post请求
     *
     * @param url      访问的url
     * @param params   post参数
     * @param parser   解析器
     * @param callback 回调
     */
    @Override
    public void post(String url, RequestParams params,
                     WeakReference<Net.Parser<T>> parser,
                     WeakReference<Net.Callback<T>> callback) {
        invoke(Request.Method.POST, url, params, parser, callback);
    }

    /**
     * 执行网络请求
     *
     * @param url
     * @param params   post时请求的参数 get时为null
     * @param parser
     * @param callback
     * @param method
     */
    private void invoke(final int method, final String url,
                        final RequestParams params,
                        final WeakReference<Net.Parser<T>> parser,
                        final WeakReference<Net.Callback<T>> callback) {
        StringRequest request = new StringRequest(method, url,
                new Response.Listener<String>() {
                    public void onResponse(String response) {
                        onNetResponse(parser, callback, response);
                    }
                }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                onError(callback, Net.DEF_ERR_MSG);
            }
        }) {
            @Override
            protected Map<String, String> getParams()
                    throws AuthFailureError {
                if (params != null) return params.get();
                return super.getParams();
            }
        };

        VolleyManager.getInstance(mContext).add(request);
    }

    @Override
    public void cancel(String tag) {
        VolleyManager.getInstance(mContext).cancel(tag);
    }
}
