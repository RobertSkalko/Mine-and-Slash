package com.robertx22.uncommon.gui.player_stats;

import java.util.ArrayList;
import java.util.List;

import com.libraries.rabbit.gui.component.list.ScrollableDisplayList;
import com.libraries.rabbit.gui.component.list.entries.ListEntry;
import com.libraries.rabbit.gui.component.list.entries.StringEntry;
import com.libraries.rabbit.gui.show.Show;
import com.robertx22.database.stat_types.defense.Armor;
import com.robertx22.database.stat_types.defense.Dodge;
import com.robertx22.database.stat_types.elementals.all_damage.AllFireDamage;
import com.robertx22.database.stat_types.elementals.all_damage.AllNatureDamage;
import com.robertx22.database.stat_types.elementals.all_damage.AllThunderDamage;
import com.robertx22.database.stat_types.elementals.all_damage.AllWaterDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackFireDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackNatureDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackThunderDamage;
import com.robertx22.database.stat_types.elementals.attack_damage.AttackWaterDamage;
import com.robertx22.database.stat_types.elementals.pene.FirePene;
import com.robertx22.database.stat_types.elementals.pene.NaturePene;
import com.robertx22.database.stat_types.elementals.pene.ThunderPene;
import com.robertx22.database.stat_types.elementals.pene.WaterPene;
import com.robertx22.database.stat_types.elementals.resist.FireResist;
import com.robertx22.database.stat_types.elementals.resist.NatureResist;
import com.robertx22.database.stat_types.elementals.resist.ThunderResist;
import com.robertx22.database.stat_types.elementals.resist.WaterResist;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellFireDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellNatureDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellThunderDamage;
import com.robertx22.database.stat_types.elementals.spell_damage.SpellWaterDamage;
import com.robertx22.database.stat_types.offense.ArmorPenetration;
import com.robertx22.database.stat_types.offense.CriticalDamage;
import com.robertx22.database.stat_types.offense.CriticalHit;
import com.robertx22.database.stat_types.offense.PhysicalDamage;
import com.robertx22.database.stat_types.offense.spell_to_attack.FireSpellToAttackDMG;
import com.robertx22.database.stat_types.offense.spell_to_attack.NatureSpellToAttackDMG;
import com.robertx22.database.stat_types.offense.spell_to_attack.ThunderSpellToAttackDMG;
import com.robertx22.database.stat_types.offense.spell_to_attack.WaterSpellToAttackDMG;
import com.robertx22.database.stat_types.resources.EnergyRegen;
import com.robertx22.database.stat_types.resources.HealthRegen;
import com.robertx22.database.stat_types.resources.LifeOnHit;
import com.robertx22.database.stat_types.resources.Lifesteal;
import com.robertx22.database.stat_types.resources.ManaRegen;
import com.robertx22.saveclasses.StatData;
import com.robertx22.saveclasses.Unit;
import com.robertx22.stats.Stat;
import com.robertx22.stats.Trait;
import com.robertx22.stats.UsableStat;
import com.robertx22.uncommon.capability.EntityData;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.UnitSaving;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;

public class PlayerStatsGui extends Show {

	Unit unit;
	UnitData data;
	Minecraft mc;

	public PlayerStatsGui() {

	}

	@Override
	public void setup() {
		super.setup();

		mc = Minecraft.getMinecraft();

		if (mc != null && mc.player != null) {

			unit = UnitSaving.Load(mc.player);
			data = mc.player.getCapability(EntityData.Data, null);

		}

	}

	ScrollableDisplayList scroll = null;

	boolean setup = false;

	@Override
	public void onDraw(int mouseX, int mouseY, float partialTicks) {

		super.onDraw(mouseX, mouseY, partialTicks);

		try {
			scroll = displayStats();

			if (scroll != null && setup == false) {
				scroll.setup();
				setup = true;
				scroll.setVisibleBackground(true);

				this.registerComponent(scroll);
			}
		} catch (Exception e) {
			e.printStackTrace();
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

			ShowStat(TextFormatting.RED + "Elemental Spell Damage:", list);

			ShowStat(this.GetStatString(unit.MyStats.get(SpellFireDamage.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(SpellWaterDamage.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(SpellThunderDamage.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(SpellNatureDamage.GUID), stats), list);

			ShowStat(TextFormatting.RED + "Elemental Attack Damage:", list);

			ShowStat(this.GetStatString(unit.MyStats.get(AttackFireDamage.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(AttackWaterDamage.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(AttackThunderDamage.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(AttackNatureDamage.GUID), stats), list);

			ShowStat(TextFormatting.RED + "Bonus to All Elemental Damage:", list);

			ShowStat(this.GetStatString(unit.MyStats.get(AllFireDamage.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(AllWaterDamage.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(AllThunderDamage.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(AllNatureDamage.GUID), stats), list);

			ShowStat(TextFormatting.RED + "Spell to Attack Conversion Damage:", list);

			ShowStat(this.GetStatString(unit.MyStats.get(FireSpellToAttackDMG.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(WaterSpellToAttackDMG.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(ThunderSpellToAttackDMG.GUID), stats), list);
			ShowStat(this.GetStatString(unit.MyStats.get(NatureSpellToAttackDMG.GUID), stats), list);

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

			return str + " (" + String.format("%.1f", usable.GetUsableValue(this.data.getLevel(), (int) data.Value))
					+ "%)";

		}

		if (data.GetStat() instanceof Trait) {
			return null;
		} else {

			return str;
		}
	}

}