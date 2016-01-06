package org.loader.andnet.net;

import org.loader.andnet.helper.Helper;
import org.loader.andnet.helper.IBaseBean;

/**
 * Created by qibin on 2015/11/29.
 */
public class Net {

	/**
	 * 默认错误提醒
	 */
	public static final String DEF_ERR_MSG = "请求失败";
	/**
	 * 数据解析失败
	 */
	public static final String ERR_PARSE_MSG = "数据解析失败";

	private static INetStack sNetStack;

	public static void init(INetStack netStack) {
		sNetStack = netStack;
	}

	/**
	 * get请求
	 * 
	 * @param url 网址
	 * @param parser 解析器
	 * @param callback 回调
	 */
	public static <T> void get(final String url, final Parser<T> parser,
			final Callback<T> callback, final Object tag) {
		try {
			sNetStack.get(url, parser, callback, tag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * post请求
	 * 
	 * @param url 访问的url
	 * @param params post参数
	 * @param parser 解析器
	 * @param callback 回调
	 */
	public static <T> void post(final String url, final RequestParams params, 
			final Parser<T> parser, final Callback<T> callback, final Object tag) {
		try {
			sNetStack.post(url, params, parser, callback, tag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * post请求
	 *
	 * @param url 访问的url
	 * @param params post参数
	 * @param parser 解析器
	 * @param callback 回调
	 */
	public static <T> void post(final String url, final IBaseBean params,
								final Parser<T> parser, final Callback<T> callback,
								final Object tag) {
		try {
			sNetStack.post(url, Helper.bean2Params(params),
					parser, callback, tag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * post json
	 * @param url
	 * @param json
	 * @param parser
	 * @param callback
	 * @param tag
     * @param <T>
     */
	public static <T> void post(final String url, final String json,
					 final Parser<T> parser,
					 final Callback<T> callback, final Object tag) {
		try {
			sNetStack.post(url, json,
					parser, callback, tag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * put请求
	 * @param url
	 * @param params
	 * @param parser
	 * @param callback
	 * @param tag
	 * @param <T>
	 */
	public static <T> void put(final String url, final RequestParams params,
							   final Parser<T> parser,
							   final Callback<T> callback, final Object tag) {
		try {
			sNetStack.put(url, params, parser, callback, tag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * put请求
	 *
	 * @param url 访问的url
	 * @param params put参数
	 * @param parser 解析器
	 * @param callback 回调
	 */
	public static <T> void put(final String url, final IBaseBean params,
								final Parser<T> parser, final Callback<T> callback,
								final Object tag) {
		try {
			sNetStack.put(url, Helper.bean2Params(params),
					parser, callback, tag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * put json
	 * @param url
	 * @param json
	 * @param parser
	 * @param callback
	 * @param tag
     * @param <T>
     */
	public static <T> void put(final String url, final String json,
					final Parser<T> parser,
					final Callback<T> callback, final Object tag) {
		try {
			sNetStack.put(url, json, parser, callback, tag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * delete请求
	 * @param url
	 * @param parser
	 * @param callback
	 * @param tag
	 * @param <T>
	 */
	public static <T> void delete(final String url, final Parser<T> parser,
								  final Callback<T> callback, final Object tag) {
		try {
			sNetStack.delete(url, parser, callback, tag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * cancel请求
	 * @param tag
     */
	public static void cancel(final Object tag) {
		if(sNetStack != null) sNetStack.cancel(tag);
	}

	/**
	 * 数据返回接口
	 *
	 * @param <T>
	 * @author loader
	 */
	public interface Callback<T> {
		void callback(Result<T> result);
	}

	/**
	 * 数据解析接口
	 *
	 * @param <T>
	 * @author loader
	 */
	public interface Parser<T> {
		Result<T> parse(String response);
	}

	/**
	 * 不做任何解析，返回原本的字符串
	 */
	public static class NoParser implements Parser<String> {
		public Result<String> parse(String response) {
			Result<String> res = new Result<String>();
			res.setResult(response);
			return res;
		}
	}
}
