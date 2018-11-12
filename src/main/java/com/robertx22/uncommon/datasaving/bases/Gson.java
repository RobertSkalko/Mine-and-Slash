package com.robertx22.uncommon.datasaving.bases;

import com.google.gson.GsonBuilder;

public class Gson {

	private static GsonBuilder gsonBilder = new GsonBuilder();

	public static com.google.gson.Gson instance = gsonBilder.create();
}
