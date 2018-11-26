package com.robertx22.saveclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.database.rarities.items.Unique;
import com.robertx22.db_lists.GearTypes;
import com.robertx22.db_lists.Rarities;
import com.robertx22.saveclasses.gearitem.ChaosStatsData;
import com.robertx22.saveclasses.gearitem.PrefixData;
import com.robertx22.saveclasses.gearitem.PrimaryStatsData;
import com.robertx22.saveclasses.gearitem.SecondaryStatsData;
import com.robertx22.saveclasses.gearitem.SetData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.SuffixData;
import com.robertx22.saveclasses.gearitem.UniqueStatsData;
import com.robertx22.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltip;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.stats.StatMod;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.unique_items.bases.BaseUniqueItem;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.Item;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

@Storable
public class GearItemData implements IStatsContainer, ITooltip {

	@Store
	public boolean isUnique = false;
	@Store
	public String uniqueGUID;

	@Store
	public int Rarity;
	@Store
	public String gearTypeName;
	@Store
	public String name = "Error no name";
	@Store
	public int level;

	// Stats
	@Store
	public UniqueStatsData uniqueStats;
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
	// Stats

	@Store
	public boolean isSalvagable = true;
	// crafting limits
	@Store
	public int timesLeveledUp = 0;
	//

	public Item getItem() {

		if (isUnique) {
			return BaseUniqueItem.ITEMS.get(this.uniqueGUID);

		} else {
			return GearTypes.All.get(gearTypeName).GetItemForRarity(GetRarity().Rank());
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
			return new Unique();
		} else {
			return Rarities.Items.get(Rarity);
		}
	}

	public String GetDisplayName() {

		String text = GetRarity().Color();

		if (isUnique) {
			text += ((BaseUniqueItem) this.getItem()).name();

		} else {

			if (prefix != null) {
				text += prefix.BaseAffix().Name() + " ";
			}
			text += name;

			if (suffix != null) {
				text += " " + suffix.BaseAffix().Name() + " ";
			}
		}
		return text;

	}

	public List<IStatsContainer> GetAllStatContainers() {

		List<IStatsContainer> containers = new ArrayList<IStatsContainer>();

		if (suffix != null) {
			containers.add(suffix);
		}
		if (prefix != null) {
			containers.add(prefix);
		}

		if (uniqueStats != null) {
			containers.add(uniqueStats);
		}

		if (primaryStats != null) {
			containers.add(primaryStats);
		}
		if (secondaryStats != null) {
			containers.add(secondaryStats);
		}
		if (chaosStats != null) {
			containers.add(chaosStats);
		}

		return containers;

	}

	@Override
	public List<StatModData> GetAllStats(int level) {

		List<StatModData> datas = new ArrayList<StatModData>();

		for (IStatsContainer con : GetAllStatContainers()) {
			datas.addAll(con.GetAllStats(this.level));
		}

		return datas;
	}

	@Override
	public void BuildTooltip(ItemTooltipEvent event, Unit unit, UnitData data) {

		event.getToolTip().clear();

		event.getToolTip().add(GetDisplayName());
		event.getToolTip().add(TextFormatting.YELLOW + "Level: " + level);

		event.getToolTip().add("");

		List<ITooltipList> list = new ArrayList<ITooltipList>();

		if (isUnique) {
			list.add(uniqueStats);
		}
		list.add(primaryStats);
		list.add(secondaryStats);
		list.add(prefix);
		list.add(suffix);
		list.add(chaosStats);

		for (ITooltipList part : list) {

			if (part != null) {
				event.getToolTip().addAll(part.GetTooltipString(this));
				event.getToolTip().add("");

			}

		}

		this.BuildSetTooltip(event, unit, data);

		ItemRarity rarity = GetRarity();
		event.getToolTip().add(rarity.Color() + "Rarity: " + rarity.Name());

		if (!this.isSalvagable) {
			event.getToolTip().add(TextFormatting.RED + "Unsalvagable");
		}

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

				String stat = StringUtils.stripControlCodes(StatModData.Load(entry.getValue(), set.GetSet().StatPercent)
						.GetTooltipString(this.GetRarity().StatPercents(), data.getLevel(), false));

				String str = color + "" + entry.getKey() + " set" + ": " + TextFormatting.DARK_GREEN + stat;

				event.getToolTip().add(str);

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
		return list;
	}

	private <T> void IfNotNullAdd(T obj, List<T> list) {
		if (obj != null) {
			list.add(obj);
		}
	}

}
