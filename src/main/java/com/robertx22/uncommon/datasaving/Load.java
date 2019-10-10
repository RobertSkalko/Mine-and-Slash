package com.robertx22.uncommon.datasaving;

import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;

import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class Load {

	public static UnitData Unit(ICapabilityProvider provider) {
		if (provider != null) {
			return provider.getCapability(EntityData.Data, null);
		}
		return null;
	}

	public static IWorldData World(ICapabilityProvider provider) {

		if (provider != null) {
			return provider.getCapability(WorldData.Data, null);

		}
		return null;
	}
}
