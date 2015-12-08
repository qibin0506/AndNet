package volley;

import android.app.Application;
import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**
 * Created by qibin on 2015/11/29.
 */
public class VolleyManager {
	private static VolleyManager sInstance;
	private RequestQueue mRequestQueue;
	
	public synchronized static VolleyManager getInstance(Application context) {
		if(sInstance == null) {
			synchronized (VolleyManager.class) {
				if(sInstance == null) sInstance = new VolleyManager(context.getApplicationContext());
			}
		}
		return sInstance;
	}
	
	private VolleyManager(Context context) {
		mRequestQueue = Volley.newRequestQueue(context);
	}
	
	/**
	 * 添加一个请求
	 * @param request
	 */
	public <T> void add(Request<T> request) {
		// 重试策略
		request.setRetryPolicy(new DefaultRetryPolicy(5000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		mRequestQueue.add(request);
	}
	
	/**
	 * 添加一个请求，并设置tag
	 * @param request
	 * @param tag
	 */
	public <T> void add(Request<T> request, Object tag) {
		if(tag != null) request.setTag(tag);
		add(request);
	}
	
	/**
	 * 取消全部请求
	 */
	public void cancelAll() {
		mRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
			public boolean apply(Request<?> request) {
				return true;
			}
		});
	}
	
	/**
	 * 取消指定tag的请求
	 * @param tag
	 */
	public void cancel(Object tag) {
		mRequestQueue.cancelAll(tag);
	}
}
