package com.robertx22.saveclasses;

import java.util.ArrayList;
import java.util.List;

import com.robertx22.customitems.currency.CurrencyItem;
import com.robertx22.customitems.ores.ItemOre;
import com.robertx22.database.rarities.MapRarity;
import com.robertx22.db_lists.Rarities;
import com.robertx22.db_lists.WorldProviders;
import com.robertx22.dimensions.IWP;
import com.robertx22.mmorpg.Ref;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.saveclasses.mapitem.MapAffixData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.capability.MapDatas;
import com.robertx22.uncommon.capability.WorldData;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.enumclasses.AffectedEntities;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

@Storable
public class MapItemData implements ISalvagable {

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

    @Override
    public int getSalvagedRarity() {
	return this.rarity;
    }

    public int getBonusLootAmount() {

	return (int) (getTotalPercents() * .5F);

    }

    public int getBonusLootRarity() {

	return (int) (getTotalPercents() * 0.5F);

    }

    public boolean increaseLevel(int i) {

	int lvl = level + i;

	if (lvl > ModConfig.Server.MAXIMUM_PLAYER_LEVEL) {
	    return false;
	}

	level = lvl;

	return true;
    }

    public boolean increaseTier(int i) {

	int tier = this.tier + i;

	if (tier > 20) {
	    return false;
	}

	this.tier = tier;

	return true;
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

    public void createDimension(int id, World ogworld, BlockPos pos, EntityPlayer player) {

	UnitData unit = Load.Unit(player);

	MapItemData map = this;

	unit.setCurrentMapId(id);

	DimensionData dimData = getDimData(id, map.worldGeneratorName);

	if (DimensionManager.isDimensionRegistered(id)) {
	    DimensionManager.unregisterDimension(id);
	}

	DimensionManager.registerDimension(id, dimData.getDimensionType());
	DimensionManager.initDimension(id);
	World world = DimensionManager.getWorld(id);

	IWorldData data = world.getCapability(WorldData.Data, null);
	data.init(pos, ogworld, map, id, player);

	MapDatas mapdatas = (MapDatas) DimensionManager.getWorld(0).getMapStorage().getOrLoadData(MapDatas.class,
		MapDatas.getLoc());

	mapdatas.register(dimData);

    }

    private DimensionData getDimData(int id, String worldgen) {

	DimensionData data = new DimensionData(Ref.MODID + "_dim", "_map", id, WorldProviders.All.get(worldgen));

	return data;
    }

    public int findFreeDimensionId(EntityPlayer player, UnitData unit) {

	if (unit.hasCurrentMapId()) {

	    int lastid = unit.getCurrentMapId();
	    if (DimensionManager.isDimensionRegistered(lastid)) {
		DimensionManager.initDimension(lastid);
		World world = DimensionManager.getWorld(lastid);
		if (world != null) {
		    IWorldData data = Load.World(world);
		    data.setDelete(true, world);
		}
	    }
	}

	int id = ModConfig.MapDimensions.MAP_ID_START;

	while (DimensionManager.isDimensionRegistered(id)) {

	    id--;
	}

	return id;
    }

    public MapRarity GetRarity() {

	return Rarities.Maps.get(rarity);

    }

    @Override
    public ItemStack getSalvageResult(float salvageBonus) {

	int min = tryIncreaseAmount(salvageBonus, 1);
	int max = tryIncreaseAmount(salvageBonus, 3);

	ItemStack stack = ItemStack.EMPTY;

	if (RandomUtils.roll(this.GetRarity().specialItemChance())) {

	    Item item = (Item) RandomUtils
		    .WeightedRandom(ListUtils.SameTierOrLess(ListUtils.CollectionToList(CurrencyItem.ITEMS), tier));

	    stack = new ItemStack(item);
	} else {

	    int amount = RandomUtils.RandomRange(min, max);

	    ItemOre ore = (ItemOre) ItemOre.ItemOres.get(rarity);

	    stack = new ItemStack(ore);
	    stack.setCount(amount);

	}

	return stack;
    }

}
