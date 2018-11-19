package com.robertx22.uncommon.datasaving;

import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.WorldData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;

import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class Load {

	public static UnitData Unit(ICapabilityProvider provider) {
		return provider.getCapability(EntityData.Data, null);
	}

	public static IWorldData World(ICapabilityProvider provider) {
		return provider.getCapability(WorldData.Data, null);
	}
}
