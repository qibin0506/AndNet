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
    public void get(String url, Net.Parser<T> parser,
                    Net.Callback<T> callback, final Object tag) {
        invoke(Request.Method.GET, url, null, parser, callback, tag);
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
                     Net.Parser<T> parser,
                     Net.Callback<T> callback,
                     final Object tag) {
        invoke(Request.Method.POST, url, params, parser, callback, tag);
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
                        final Net.Parser<T> parser,
                        final Net.Callback<T> callback,
                        final Object tag) {
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

        VolleyManager.getInstance(mContext).add(request, tag);
    }

    @Override
    public void cancel(Object tag) {
        VolleyManager.getInstance(mContext).cancel(tag);
    }
}
