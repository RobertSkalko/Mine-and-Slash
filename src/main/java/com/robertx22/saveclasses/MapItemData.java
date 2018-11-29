package com.robertx22.saveclasses;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.db_lists.WorldProviders;
import com.robertx22.dimensions.IWP;
import com.robertx22.mmorpg.Ref;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.uncommon.capability.MapDatas;
import com.robertx22.uncommon.capability.WorldData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.enumclasses.AffectedEntities;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

@Storable
public class MapItemData {

    @Store
    public String name = "Adventure Map";

    @Store
    public int minutes = 30; // default

    @Store
    public int level = 1;

    @Store
    public int tier = 0;

    @Store
    public int rarity = 0;

    @Store
    public List<MapAffixData> affixes = new ArrayList<MapAffixData>();

    @Store
    public String worldGeneratorName;

    public int getBonusLootAmount() {

	return (int) (getTotalPercents() * .5F);

    }

    public int getBonusLootRarity() {

	return (int) (getTotalPercents() * 0.5F);

    }

    private int getTotalPercents() {

	int total = 0;
	for (MapAffixData affix : affixes) {
	    total += affix.percent;
	}
	return total;
    }

    public List<MapAffixData> getAllAffixesThatAffect(AffectedEntities affected) {

	List<MapAffixData> list = new ArrayList<>();

	for (MapAffixData data : affixes) {
	    if (data.affectedEntities.equals(affected)) {
		list.add(data);
	    }
	}
	return list;
    }

    public IWP getWorldProvider() {
	return WorldProviders.All.get(this.worldGeneratorName);
    }

    public int createDimension(EntityPlayer player) {

	int id = findFreeDimensionId();

	DimensionData dimData = getDimData(id, this.worldGeneratorName);

	DimensionManager.registerDimension(id, dimData.getDimensionType());
	DimensionManager.initDimension(id);
	World world = DimensionManager.getWorld(id);

	IWorldData data = world.getCapability(WorldData.Data, null);
	data.init(player, this, id);

	MapDatas mapdatas = (MapDatas) DimensionManager.getWorld(0).getMapStorage().getOrLoadData(MapDatas.class,
		MapDatas.getLoc());

	mapdatas.register(dimData);

	return id;

    }

    private DimensionData getDimData(int id, String worldgen) {

	DimensionData data = new DimensionData(Ref.MODID + "_dim", "_map", id, WorldProviders.All.get(worldgen));

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
