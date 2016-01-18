package org.loader.nettest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


import org.loader.andnet.helper.Helper;
import org.loader.andnet.net.Net;
import org.loader.andnet.net.Result;

import java.util.List;

public class ListParser<T> implements Net.Parser<List<T>> {
	
	private String mKey;
	
	public ListParser(String key) {
		mKey = key;
	}
	
	public Result<List<T>> parse(String response) {
		Result<List<T>> result = new Result<List<T>>();
		try {
			JSONObject baseObject = JSON.parseObject(response);
			if(!baseObject.getBooleanValue("success")) {
				result.setMsg(baseObject.getString("message"));
			}else {
				JSONArray arr = baseObject.getJSONArray(mKey);
				Class<T> klass = Helper.generateType(getClass());
				List<T> t = JSON.parseArray(arr.toJSONString(), klass);
				result.setStatus(Result.SUCCESS);
				result.setResult(t);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg(Net.ERR_PARSE_MSG);
		}
		
		result.setStatus(Result.ERROR);
 		return result;
	}
}
