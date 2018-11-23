package com.robertx22.database.lists;

import java.util.HashMap;

import com.robertx22.dimensions.world_providers.CliffWP;

import net.minecraft.world.WorldProvider;

public class WorldGens {
	public static HashMap<String, WorldProvider> All = new HashMap<String, WorldProvider>() {
		{
			{
				put(new CliffWP().GUID(), new CliffWP());

			}
		}
	};

}
