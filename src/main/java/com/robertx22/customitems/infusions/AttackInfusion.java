package com.robertx22.customitems.infusions;

import java.util.Arrays;
import java.util.List;

import com.robertx22.database.stat_mods.flat.CriticalDamageFlat;
import com.robertx22.database.stat_mods.flat.CriticalHitFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.FirePeneFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.NaturePeneFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.ThunderPeneFlat;
import com.robertx22.database.stat_mods.flat.elemental.pene.WaterPeneFlat;
import com.robertx22.database.stat_mods.percent.PhysicalDamagePercent;
import com.robertx22.database.stat_mods.percent.spell_ele_dmg.SpellFireDamagePercent;
import com.robertx22.database.stat_mods.percent.spell_ele_dmg.SpellNatureDamagePercent;
import com.robertx22.database.stat_mods.percent.spell_ele_dmg.SpellThunderDamagePercent;
import com.robertx22.database.stat_mods.percent.spell_ele_dmg.SpellWaterDamagePercent;
import com.robertx22.stats.StatMod;

public class AttackInfusion extends BaseInfusionItem {

    public AttackInfusion() {
	super("attack_infusion");

    }

    @Override
    public List<StatMod> weaponInfusions() {
	return Arrays.asList(new CriticalHitFlat(), new CriticalDamageFlat(), new PhysicalDamagePercent());
    }

    @Override
    public List<StatMod> armorInfusions() {
	return Arrays.asList(new SpellNatureDamagePercent(), new SpellFireDamagePercent(),
		new SpellThunderDamagePercent(), new SpellWaterDamagePercent(), new PhysicalDamagePercent());
    }

    @Override
    public List<StatMod> jewerlyInfusions() {
	return Arrays.asList(new FirePeneFlat(), new WaterPeneFlat(), new ThunderPeneFlat(), new NaturePeneFlat());
    }

}
