package com.robertx22.datasaving.bases;

import com.google.gson.GsonBuilder;
import com.robertx22.spells.bases.BaseSpellEffect;
import com.robertx22.stats.Stat;

public class Gson {
	private static GsonBuilder gsonBilder = new GsonBuilder()
			.registerTypeAdapter(Stat.class, new InterfaceAdapter<Stat>())
			.registerTypeAdapter(BaseSpellEffect.class, new InterfaceAdapter<BaseSpellEffect>());

	public static com.google.gson.Gson instance = gsonBilder.create();
}
