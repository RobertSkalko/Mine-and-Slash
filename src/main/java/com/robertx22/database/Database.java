package com.robertx22.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.robertx22.mmorpg.Main;

import net.minecraftforge.fml.common.discovery.ASMDataTable.ASMData;

public class Database {

	public static <C extends IGUID> HashMap<String, C> All(Class<? extends IGUID> theclass, String annotation,
			HashMap<String, C> map) {

		map = new HashMap<String, C>();

		Set<ASMData> test = Main.ASMData.getAll(annotation);

		List<C> objects = new ArrayList<C>();

		for (ASMData d : test) {
			Class obj = null;
			try {
				obj = Class.forName(d.getClassName());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			if (obj.getClass().isAssignableFrom(theclass.getClass()) || theclass.getClass().isAssignableFrom(obj)) {
				try {
					objects.add((C) obj.newInstance());
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}

		}
		for (C obj : objects) {

			IGUID g = (IGUID) obj;

			map.put(g.GUID(), obj);

		}

		return map;

	}

}
