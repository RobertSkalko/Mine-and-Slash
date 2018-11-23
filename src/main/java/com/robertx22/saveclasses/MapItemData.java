package com.robertx22.saveclasses;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.dimensions.IWP;
import com.robertx22.dimensions.world_providers.CliffWP;
import com.robertx22.mmorpg.Main;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.uncommon.capability.MapDatas;
import com.robertx22.uncommon.capability.WorldData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.enumclasses.AffectedEntities;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.DimensionManager;

@Storable
public class MapItemData {

	@Store
	public String name;

	@Store
	public int level;

	@Store
	public int tier;

	@Store
	public int rarity;

	@Store
	public List<MapAffixData> affixes = new ArrayList<MapAffixData>();

	@Store
	public String worldGeneratorName;

	public int getBonusLootAmount() {

		return (int) (getTotalPercents() * 0.4F);

	}

	public int getBonusLootRarity() {

		return (int) (getTotalPercents() * 0.7F);

	}

	private int getTotalPercents() {

		int total = 0;
		for (MapAffixData affix : affixes) {
			total += affix.percent;
		}
		return total;
	}

	public List<MapAffixData> getAllAffixesThatAffect(AffectedEntities affected) {

		return (List<MapAffixData>) affixes.stream().filter(x -> x.affectedEntities.equals(affected));

	}

	public int createDimension(EntityPlayer player) {

		int id = findFreeDimensionId();

		DimensionManager.registerDimension(id, Main.dimtype);
		DimensionManager.initDimension(id);

		DimensionData dimData = getDimData(id);

		IWorldData data = DimensionManager.getWorld(id).getCapability(WorldData.Data, null);
		data.init(player, this, id);

		MapDatas mapdatas = (MapDatas) DimensionManager.getWorld(id).getMapStorage().getOrLoadData(MapDatas.class,
				MapDatas.LOCATION);

		mapdatas.register(dimData);

		return id;

	}

	private DimensionData getDimData(int id) {

		DimensionData data = new DimensionData(Ref.MODID + "_dim", "_map", id, (IWP) new CliffWP());

		return data;
	}

	private int findFreeDimensionId() {

		int id = -1462;

		while (DimensionManager.isDimensionRegistered(id)) {
			id--;
		}

		return id;
	}

}
