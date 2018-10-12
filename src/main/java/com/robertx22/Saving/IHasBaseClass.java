package com.robertx22.saving;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public interface IHasBaseClass {

	public default String ClassToString(Object obj) {
		return obj.getClass().toString();
	}
	
	public abstract String BaseClass();
		
	
	public default <T> T GetBase() {
		
		Class<?> clazz = null;
		try {
			clazz = Class.forName(BaseClass());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Constructor<?> ctor = null;		
		try {
			ctor = clazz.getConstructor();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		Object object = null;
		try {
			object = ctor.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return (T) object;
	}
	
}
