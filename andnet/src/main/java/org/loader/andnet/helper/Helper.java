package org.loader.andnet.helper;

import org.loader.andnet.net.RequestParams;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by qibin on 2015/11/29.
 */
public class Helper {
	
	public static <T> Class<T> generateType(Class<?> klass) {
		Type type = klass.getGenericSuperclass();
		if(type instanceof ParameterizedType) {
			ParameterizedType paramType = (ParameterizedType) type;
			Type[] actualTypes = paramType.getActualTypeArguments();
			if(actualTypes != null && actualTypes.length > 0) {
				return (Class<T>) actualTypes[0];
			}
		}
		
		return null;
	}

	public static RequestParams bean2Params(IBaseBean bean) {
		RequestParams params = new RequestParams();
		Class<?> klass = bean.getClass();
		Field[] fields = klass.getDeclaredFields();
		for(Field f : fields) {
			if(!f.isAnnotationPresent(Annotation.class)) continue;
			Annotation annotation = f.getAnnotation(Annotation.class);
			String key = annotation.key();
			f.setAccessible(true);
			try {
				params.add(key, f.get(bean));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return params;
	}
}
