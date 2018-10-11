package com.robertx22.classes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public interface IHasBaseClass {

	public abstract Class<?> BaseClass();
	
	public default <T> T GetBase() {
		
		Class<?> clazz = null;
		clazz = BaseClass();
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
