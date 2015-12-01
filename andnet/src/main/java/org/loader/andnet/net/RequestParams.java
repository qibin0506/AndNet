package org.loader.andnet.net;

import java.io.File;
import java.util.LinkedHashMap;

/**
 * Created by qibin on 2015/11/29.
 */
public class RequestParams {
	private LinkedHashMap<String, String> mParams;
	private LinkedHashMap<String, File> mFileParams;
	
	public RequestParams() {
		mParams = new LinkedHashMap<String, String>();
		mFileParams = new LinkedHashMap<String, File>();
	}
	
	public RequestParams(String key, Object value) {
		this();
		add(key, value);
	}
	
	public RequestParams add(String key, Object value) {
		if(value instanceof File) mFileParams.put(key, (File) value);
		else mParams.put(key, value.toString());
		return this;
	}
	
	public LinkedHashMap<String, String> get() {
		return mParams;
	}
	public LinkedHashMap<String, File> files() {
		return mFileParams;
	}
}
