package org.loader.nettest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.loader.andnet.helper.Helper;
import org.loader.andnet.net.Net;
import org.loader.andnet.net.Result;


/**
 * 共同的parser
 * 
 * @author loader
 *
 * @param <T>
 */
public class CommParser<T> implements Net.Parser<T> {
	
	private String mKey;
	
	public CommParser(String key) {
		mKey = key;
	}

	@Override
	public Result<T> parse(String response) {
		Result<T> result = new Result<T>();
		try {
			JSONObject baseObject = JSON.parseObject(response);
			if(!baseObject.getBooleanValue("success")) {
				result.setMsg(baseObject.getString("message"));
			}else {
				Class<T> klass = Helper.generateType(getClass());
				if(klass == null) throw new Exception();
				
				T t = baseObject.getObject(mKey, klass);
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
