package com.robertx22.saveclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.robertx22.customitems.currency.CurrencyItem;
import com.robertx22.customitems.gearitems.bases.IWeapon;
import com.robertx22.customitems.ores.ItemOre;
import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.gearitemslots.bases.GearItemSlot.GearSlotType;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.database.rarities.items.UniqueItem;
import com.robertx22.db_lists.GearTypes;
import com.robertx22.db_lists.Rarities;
import com.robertx22.mmorpg.config.ModConfig;
import com.robertx22.saveclasses.gearitem.ChaosStatsData;
import com.robertx22.saveclasses.gearitem.GearTypeStatsData;
import com.robertx22.saveclasses.gearitem.InfusionData;
import com.robertx22.saveclasses.gearitem.PrefixData;
import com.robertx22.saveclasses.gearitem.PrimaryStatsData;
import com.robertx22.saveclasses.gearitem.SecondaryStatsData;
import com.robertx22.saveclasses.gearitem.SetData;
import com.robertx22.saveclasses.gearitem.SocketsListData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.SuffixData;
import com.robertx22.saveclasses.gearitem.UniqueStatsData;
import com.robertx22.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer.LevelAndStats;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltip;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.saveclasses.rune.RunesData;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.CLOC;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.utilityclasses.ListUtils;
import com.robertx22.uncommon.utilityclasses.RandomUtils;
import com.robertx22.unique_items.IUnique;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

@Storable
public class GearItemData implements ITooltip, ISalvagable {

    @Store
    public boolean isUnique = false;
    @Store
    public String uniqueGUID;

    @Store
    public int Rarity;

    @Store
    public boolean isNotFromMyMod = false;

    @Store
    public String gearTypeName = "";

    @Store
    public RunesData runes;

    public boolean isRuned() {
	return runes != null;

    }

    public boolean changesItemStack() {
	return this.isNotFromMyMod == false;
    }

    public String name(ItemStack stack) {

	if (isUnique) {
	    if (IUnique.ITEMS.containsKey(this.uniqueGUID)) {
		return ((IUnique) IUnique.ITEMS.get(this.uniqueGUID)).locName();
	    } else {
		return "error";
	    }
	} else {
	    if (gearTypeName.isEmpty()) {
		return "error";
	    } else {
		return stack.getDisplayName();
	    }
	}
    }

    @Store
    public int level;

    // Stats
    @Store
    public UniqueStatsData uniqueStats;
    @Store
    public GearTypeStatsData gearTypeStats;
    @Store
    public PrimaryStatsData primaryStats;
    @Store
    public SecondaryStatsData secondaryStats;
    @Store
    public SuffixData suffix;
    @Store
    public PrefixData prefix;
    @Store
    public SetData set;
    @Store
    public ChaosStatsData chaosStats;
    @Store
    public SocketsListData sockets;
    @Store
    public InfusionData infusion;
    // Stats

    @Store
    public boolean isSalvagable = true;
    // crafting limits
    @Store
    public int timesLeveledUp = 0;
    //

    public boolean isSocketable() {
	return this.GetBaseGearType().slotType().equals(GearSlotType.Armor);
    }

    public boolean isUpgradable() {
	return this.GetBaseGearType().slotType().equals(GearSlotType.Weapon);
    }

    public boolean isEnchantable() {
	return this.GetBaseGearType().slotType().equals(GearSlotType.Jewerly);
    }

    public Item getItem() {

	if (isUnique) {
	    if (IUnique.ITEMS.containsKey(this.uniqueGUID)) {
		return IUnique.ITEMS.get(this.uniqueGUID);
	    } else {
		return null;
	    }
	} else {
	    if (gearTypeName.isEmpty()) {
		return Items.AIR;
	    } else {
		return GearTypes.All.get(gearTypeName).GetItemForRarity(GetRarity().Rank());
	    }
	}

    }

    public void WriteOverDataThatShouldStay(GearItemData newdata) {

	newdata.timesLeveledUp = this.timesLeveledUp;
	newdata.isSalvagable = this.isSalvagable;

    }

    public GearItemSlot GetBaseGearType() {

	return GearTypes.All.get(gearTypeName);
    }

    public ItemRarity GetRarity() {

	if (isUnique) {
	    return new UniqueItem();
	} else {
	    return Rarities.Items.get(Rarity);
	}
    }

    public String GetDisplayName(ItemStack stack) {

	String text = GetRarity().Color();

	if (isUnique) {
	    IUnique uniq = (IUnique) this.getItem();
	    text += uniq.locName();

	} else if (this.isRuned()) {
	    text += "Runed " + name(stack);
	} else {

	    if (prefix != null && ModConfig.Client.SHOW_AFFIXED_NAME) {
		text += prefix.BaseAffix().locName() + " ";
	    }
	    text += name(stack);

	    if (suffix != null && ModConfig.Client.SHOW_AFFIXED_NAME) {
		text += " " + suffix.BaseAffix().locName() + " ";
	    }
	}
	return text;

    }

    public List<IStatsContainer> GetAllStatContainers() {

	List<IStatsContainer> list = new ArrayList<IStatsContainer>();

	IfNotNullAdd(secondaryStats, list);
	IfNotNullAdd(primaryStats, list);
	IfNotNullAdd(prefix, list);
	IfNotNullAdd(suffix, list);
	IfNotNullAdd(chaosStats, list);
	IfNotNullAdd(uniqueStats, list);
	IfNotNullAdd(gearTypeStats, list);
	IfNotNullAdd(sockets, list);
	IfNotNullAdd(infusion, list);
	IfNotNullAdd(runes, list);

	return list;

    }

    public List<LevelAndStats> GetAllStats(int level) {

	List<LevelAndStats> datas = new ArrayList<LevelAndStats>();

	for (IStatsContainer con : GetAllStatContainers()) {
	    datas.addAll(con.GetAllStats(this.level));
	}

	return datas;
    }

    public List<StatModData> mergeSameStats(List<StatModData> mods) {

	List<StatModData> newmods = new ArrayList();

	for (StatModData mod : mods) {

	    boolean add = true;

	    for (StatModData newmod : newmods) {

		if (newmod.baseModName.equals(mod.baseModName)) {
		    newmod.percent += mod.percent;
		    add = false;
		    break;

		}

	    }

	    if (add) {
		newmods.add(mod);
	    }
	}

	return newmods;

    }

    @Override
    public void BuildTooltip(ItemStack stack, ItemTooltipEvent event, Unit unit, UnitData data) {

	event.getToolTip().clear();

	event.getToolTip().add(GetDisplayName(stack));
	event.getToolTip().add(TextFormatting.YELLOW + CLOC.word("level") + ": " + level);

	event.getToolTip().add("");

	List<ITooltipList> list = new ArrayList<ITooltipList>();

	if (uniqueStats != null) {
	    event.getToolTip().addAll(uniqueStats.GetTooltipString(this));
	}
	if (primaryStats != null) {
	    event.getToolTip().addAll(primaryStats.GetTooltipString(this));
	}

	if (runes != null) {
	    event.getToolTip().addAll(runes.GetTooltipString(this));
	}

	event.getToolTip().add("");

	list.add(secondaryStats);
	list.add(prefix);
	list.add(suffix);

	list.add(chaosStats);
	list.add(gearTypeStats);
	list.add(sockets);
	list.add(infusion);

	for (

	ITooltipList part : list) {

	    if (part != null) {
		event.getToolTip().addAll(part.GetTooltipString(this));
		event.getToolTip().add("");

	    }

	}

	this.BuildSetTooltip(event, unit, data);

	if (isUnique) {
	    IUnique unique = this.uniqueStats.getUniqueItem();
	    event.getToolTip().add(TextFormatting.GREEN + "'" + unique.locDesc() + "'");
	    event.getToolTip().add("");

	}

	ItemRarity rarity = GetRarity();
	event.getToolTip().add(CLOC.word("rarity") + ": " + rarity.Color() + rarity.locName());

	if (!this.isSalvagable) {
	    event.getToolTip().add(TextFormatting.RED + CLOC.word("unsalvagable"));
	}

	if (this.GetBaseGearType() instanceof IWeapon) {
	    IWeapon iwep = (IWeapon) this.GetBaseGearType();
	    event.getToolTip().add("");
	    event.getToolTip().add(TextFormatting.GREEN + CLOC.stat("energy") + ": " + iwep.mechanic().GetEnergyCost());
	}

	List<String> tool = removeDoubleBlankLines(event.getToolTip());
	event.getToolTip().clear();
	event.getToolTip().addAll(tool);

    }

    private List<String> removeDoubleBlankLines(List<String> tooltip) {

	List<String> newt = new ArrayList();

	String s = "";
	for (int i = 0; i < tooltip.size(); i++) {

	    if (i > 0) {

		if (s.equals(tooltip.get(i)) && s.isEmpty()) {

		} else {
		    newt.add(tooltip.get(i));
		}
	    } else {
		newt.add(tooltip.get(i));
	    }
	    s = tooltip.get(i);
	}

	return newt;
    }

    private void BuildSetTooltip(ItemTooltipEvent event, Unit unit, UnitData data) {

	if (this.set != null) {
	    event.getToolTip().add(TextFormatting.GREEN + "[Set]: " + TextFormatting.GRAY + set.GetSet().Name());

	    for (Entry<Integer, StatMod> entry : set.GetSet().AllMods().entrySet()) {

		boolean has = false;

		TextFormatting color = null;
		if (unit.WornSets.containsKey(set.baseSet) && unit.WornSets.get(set.baseSet) >= entry.getKey()) {
		    color = TextFormatting.GREEN;
		    has = true;
		} else {
		    color = TextFormatting.DARK_GREEN;
		}

		for (String str : StatModData.Load(entry.getValue(), set.GetSet().StatPercent)
			.GetTooltipString(this.GetRarity().StatPercents(), data.getLevel(), false)) {

		    String stat = StringUtils.stripControlCodes(str);

		    String str2 = color + "" + entry.getKey() + " set" + ": " + TextFormatting.DARK_GREEN + stat;

		    event.getToolTip().add(str2);
		}

	    }
	    event.getToolTip().add("");
	}
    }

    public List<IRerollable> GetAllRerollable() {
	List<IRerollable> list = new ArrayList<IRerollable>();
	IfNotNullAdd(secondaryStats, list);
	IfNotNullAdd(primaryStats, list);
	IfNotNullAdd(prefix, list);
	IfNotNullAdd(suffix, list);
	IfNotNullAdd(chaosStats, list);
	IfNotNullAdd(uniqueStats, list);
	IfNotNullAdd(gearTypeStats, list);
	return list;
    }

    private <T> void IfNotNullAdd(T obj, List<T> list) {
	if (obj != null) {
	    list.add(obj);
	}
    }

    @Override
    public ItemStack getSalvageResult(float salvageBonus) {

	if (this.isSalvagable == false) {
	    return ItemStack.EMPTY;
	}

	ItemStack stack = ItemStack.EMPTY;
	int tier = 0;

	int min = 1;
	int max = 3;

	min = tryIncreaseAmount(salvageBonus, min);
	max = tryIncreaseAmount(salvageBonus, max);

	if (isUnique) {
	    try {
		tier = this.uniqueStats.getUniqueItem().Tier();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	if (RandomUtils.roll(this.GetRarity().specialItemChance())) {

	    Item item = (Item) RandomUtils
		    .WeightedRandom(ListUtils.SameTierOrLess(ListUtils.CollectionToList(CurrencyItem.ITEMS), tier));

	    if (isUnique) {
		int amount = RandomUtils.RandomRange(min, max + (tier / 5));
		stack.setCount(amount);
	    }

	    stack = new ItemStack(item);
	} else {

	    int amount = RandomUtils.RandomRange(min, max);

	    ItemOre ore = (ItemOre) ItemOre.ItemOres.get(Rarity);
	    stack = new ItemStack(ore);
	    stack.setCount(amount);

	}

	return stack;
    }

    @Override
    public int getSalvagedRarity() {
	return this.Rarity;
    }

}
