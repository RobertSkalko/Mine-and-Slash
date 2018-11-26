package com.robertx22.db_lists;

import java.util.HashMap;

import com.robertx22.dimensions.IWP;
import com.robertx22.dimensions.world_providers.AridHighlandWP;
import com.robertx22.dimensions.world_providers.BadlandsWP;
import com.robertx22.dimensions.world_providers.CliffWP;
import com.robertx22.dimensions.world_providers.RedDesertWP;
import com.robertx22.dimensions.world_providers.ThicketWP;

public class WorldProviders {
	public static HashMap<String, IWP> All = new HashMap<String, IWP>() {
		{
			{
				put(new CliffWP().GUID(), new CliffWP());
				put(new RedDesertWP().GUID(), new RedDesertWP());
				put(new ThicketWP().GUID(), new ThicketWP());
				put(new AridHighlandWP().GUID(), new AridHighlandWP());
				put(new BadlandsWP().GUID(), new BadlandsWP());

			}
		}
	};

}
