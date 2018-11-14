package com.robertx22.uncommon.gui.player_stats;

import java.util.ArrayList;
import java.util.List;

import com.rabbit.gui.component.list.ScrollableDisplayList;
import com.rabbit.gui.component.list.entries.ListEntry;
import com.rabbit.gui.component.list.entries.StringEntry;
import com.rabbit.gui.show.Show;
import com.robertx22.database.stats.types.defense.Armor;
import com.robertx22.database.stats.types.defense.Dodge;
import com.robertx22.database.stats.types.elementals.damage.FireDamage;
import com.robertx22.database.stats.types.elementals.damage.NatureDamage;
import com.robertx22.database.stats.types.elementals.damage.ThunderDamage;
import com.robertx22.database.stats.types.elementals.damage.WaterDamage;
import com.robertx22.database.stats.types.elementals.pene.FirePene;
import com.robertx22.database.stats.types.elementals.pene.NaturePene;
import com.robertx22.database.stats.types.elementals.pene.ThunderPene;
import com.robertx22.database.stats.types.elementals.pene.WaterPene;
import com.robertx22.database.stats.types.elementals.resist.FireResist;
import com.robertx22.database.stats.types.elementals.resist.NatureResist;
import com.robertx22.database.stats.types.elementals.resist.ThunderResist;
import com.robertx22.database.stats.types.elementals.resist.WaterResist;
import com.robertx22.database.stats.types.offense.ArmorPenetration;
import com.robertx22.database.stats.types.offense.CriticalDamage;
import com.robertx22.database.stats.types.offense.CriticalHit;
import com.robertx22.database.stats.types.offense.PhysicalDamage;
import com.robertx22.database.stats.types.offense.bonus.BonusFireDamage;
import com.robertx22.database.stats.types.offense.bonus.BonusNatureDamage;
import com.robertx22.database.stats.types.offense.bonus.BonusThunderDamage;
import com.robertx22.database.stats.types.offense.bonus.BonusWaterDamage;
import com.robertx22.database.stats.types.resources.EnergyRegen;
import com.robertx22.database.stats.types.resources.HealthRegen;
import com.robertx22.database.stats.types.resources.LifeOnHit;
import com.robertx22.database.stats.types.resources.Lifesteal;
import com.robertx22.database.stats.types.resources.ManaRegen;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.Stat;
import com.robertx22.stats.Trait;
import com.robertx22.stats.UsableStat;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.text.TextFormatting;

public class StatsGui extends Show {

	private GuiButton mButtonClose;

	Unit unit;
	Minecraft mc;

	public StatsGui() {

	}

	@Override
	public void setup() {
		super.setup();

		mc = Minecraft.getMinecraft();

		if (mc != null && mc.player != null) {

			unit = UnitSaving.Load(mc.player);

		}

	}

	ScrollableDisplayList scroll = null;

	boolean setup = false;

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {

		super.onDraw(mouseX, mouseY, partialTicks);

		scroll = displayStats();

		if (scroll != null && setup == false) {
			scroll.setup();
			setup = true;
			scroll.setVisibleBackground(true);

			this.registerComponent(scroll);
		}

	}

	private ScrollableDisplayList displayStats() {

		if (unit != null && mc.currentScreen != null) {

			List<Stat> stats = new ArrayList();
			List<ListEntry> list = new ArrayList();

			for (StatData data : unit.MyStats.values()) {
				stats.add(data.GetStat());
			}

			ShowStat(TextFormatting.GREEN + "Resources:", list);

			ShowStat(this.GetStatString(unit.healthData(), stats), list);
			ShowStat(this.GetStatString(unit.manaData(), stats), list);
			ShowStat(this.GetStatString(unit.energyData(), stats), list);

			ShowStat(TextFormatting.YELLOW + "Regen:", list);

			ShowStat(this.GetStatString(unit.MyStats.get(HealthRegen.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(ManaRegen.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(EnergyRegen.GUID), stats), list);

			ShowStat(TextFormatting.RED + "Attack:", list);

			ShowStat(this.GetStatString(unit.MyStats.get(PhysicalDamage.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(CriticalDamage.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(CriticalHit.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(Lifesteal.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(LifeOnHit.GUID), stats), list);

			ShowStat(TextFormatting.RED + "Elemental Damage:", list);

			ShowStat(this.GetStatString(unit.MyStats.get(FireDamage.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(WaterDamage.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(ThunderDamage.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(NatureDamage.GUID), stats), list);

			ShowStat(TextFormatting.RED + "Bonus Element Damage:", list);

			ShowStat(this.GetStatString(unit.MyStats.get(BonusFireDamage.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(BonusWaterDamage.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(BonusThunderDamage.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(BonusNatureDamage.GUID), stats), list);

			ShowStat(TextFormatting.BLUE + "Defense:", list);

			ShowStat(this.GetStatString(unit.MyStats.get(Armor.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(Dodge.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(FireResist.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(WaterResist.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(ThunderResist.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(NatureResist.GUID), stats), list);

			ShowStat(TextFormatting.LIGHT_PURPLE + "Penetration:", list);

			ShowStat(this.GetStatString(unit.MyStats.get(ArmorPenetration.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(FirePene.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(WaterPene.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(ThunderPene.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(NaturePene.GUID), stats), list);

			ShowStat(TextFormatting.GOLD + "Misc:", list);

			for (StatData data : unit.MyStats.values()) {
				ShowStat(this.GetStatString(data, stats), list);

			}

			int width = this.width / 2;
			int height = this.height - this.height / 5;

			ScrollableDisplayList scroll = new ScrollableDisplayList(this.width / 4, this.height / 10, width, height,
					15, list);

			return scroll;
		}
		return null;

	}

	private void ShowStat(String str, List<ListEntry> list) {
		if (str != null) {
			StringEntry entry = new StringEntry(str);

			list.add(entry);

		}
	}

	private String GetStatString(StatData data, List<Stat> stats) {

		if (stats.contains(data.GetStat())) {
			stats.remove(data.GetStat());

		} else {
			return null;
		}

		String str = data.GetStat().Name() + " " + String.format("%.1f", data.Value);

		if (data.GetStat().IsPercent()) {
			str += "%";
		}

		if (data.GetStat() instanceof UsableStat) {
			UsableStat usable = (UsableStat) data.GetStat();

			return str + " (" + String.format("%.1f", usable.GetUsableValue(unit.GetLevel(), (int) data.Value)) + "%)";

		}

		if (data.GetStat() instanceof Trait) {
			return null;
		} else {

			return str;
		}
	}

}