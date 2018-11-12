package com.robertx22.saveclasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.robertx22.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.database.lists.GearTypes;
import com.robertx22.database.lists.Rarities;
import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.saveclasses.gearitem.ChaosStatsData;
import com.robertx22.saveclasses.gearitem.PrefixData;
import com.robertx22.saveclasses.gearitem.PrimaryStatsData;
import com.robertx22.saveclasses.gearitem.SecondaryStatsData;
import com.robertx22.saveclasses.gearitem.SetData;
import com.robertx22.saveclasses.gearitem.StatModData;
import com.robertx22.saveclasses.gearitem.SuffixData;
import com.robertx22.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltip;
import com.robertx22.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.stats.StatMod;

import net.minecraft.util.StringUtils;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class GearItemData implements IStatsContainer, Serializable, ITooltip {

	private static final long serialVersionUID = -8327205425334275976L;

	public int Rarity;
	public String gearTypeName;
	public String name = "Error no name";
	public int level;

	public PrimaryStatsData primaryStats;
	public SecondaryStatsData secondaryStats;

	public SuffixData suffix;
	public PrefixData prefix;

	public SetData set;

	public ChaosStatsData chaosStats = null;

	public boolean isSalvagable = true;
	// crafting limits
	public int timesLeveledUp = 0;
	//

	public void WriteOverDataThatShouldStay(GearItemData newdata) {

		newdata.timesLeveledUp = this.timesLeveledUp;
		newdata.isSalvagable = this.isSalvagable;

	}

	public GearItemSlot GetBaseGearType() {

		return GearTypes.All.get(gearTypeName);
	}

	public ItemRarity GetRarity() {
		return Rarities.Items.get(Rarity);
	}

	public String GetDisplayName() {

		String text = GetRarity().Color();

		if (prefix != null) {
			text += prefix.BaseAffix().Name() + " ";
		}
		text += name;

		if (suffix != null) {
			text += " " + suffix.BaseAffix().Name() + " ";
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
	public void BuildTooltip(ItemTooltipEvent event, Unit unit) {

		event.getToolTip().clear();

		event.getToolTip().add(GetDisplayName());
		event.getToolTip().add(TextFormatting.YELLOW + "Level: " + level);

		event.getToolTip().add("");

		List<ITooltipList> list = new ArrayList<ITooltipList>();
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

		this.BuildSetTooltip(event, unit);

		ItemRarity rarity = GetRarity();
		event.getToolTip().add(rarity.Color() + "Rarity: " + rarity.Name());

		if (!this.isSalvagable) {
			event.getToolTip().add(TextFormatting.RED + "Unsalvagable");
		}

	}

	private void BuildSetTooltip(ItemTooltipEvent event, Unit unit) {

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
						.GetTooltipString(unit.level, this, false));

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
		return list;
	}

	private <T> void IfNotNullAdd(T obj, List<T> list) {
		if (obj != null) {
			list.add(obj);
		}
	}

}
