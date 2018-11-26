package com.robertx22.uncommon.utilityclasses;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListUtils {

	public static <OLD, NEW> List<NEW> CollectionToList(Collection<OLD> coll) {

		List<NEW> list = new ArrayList<NEW>();

		for (OLD old : coll) {
			list.add((NEW) old);
		}

		return list;

	}

	public static <T> List<T> SameTierOrLess(Collection<ITiered> coll, int tier) {

		List<T> list = new ArrayList<T>();
		for (ITiered tiered : coll) {
			if (tiered.Tier() <= tier) {
				list.add((T) tiered);
			}
		}

		return list;

	}

}
