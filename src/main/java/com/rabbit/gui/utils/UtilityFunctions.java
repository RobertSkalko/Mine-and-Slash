package com.rabbit.gui.utils;

import java.util.List;

public class UtilityFunctions {

	public static <T> T getLastElement(List<T> list) {
		return list.get(list.size() - 1);
	}

	public static <T> List<T> minusLast(List<T> list) {
		return list.subList(0, list.size() - 1);
	}
}
