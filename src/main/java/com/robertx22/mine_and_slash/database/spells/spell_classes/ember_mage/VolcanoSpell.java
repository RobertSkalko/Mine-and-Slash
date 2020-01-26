package com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage;

import com.robertx22.mine_and_slash.database.spells.entities.cloud.VolcanoEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSummonAtSightSpell;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class VolcanoSpell extends BaseSummonAtSightSpell {

    public VolcanoSpell() {

    }

    public Elements element = Elements.Fire;

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.EMBER_MAGE;
    }

    @Override
    public int getCooldownInSeconds() {
        return 30;
    }

    @Override
    public BaseSpell.SpellType getSpellType() {
        return SpellType.LASTING_AOE;
    }

    @Override
    public String GUID() {
        return "volcano";
    }

    @Override
    public int getManaCost() {
        return 60;
    }

    @Override
    public int useTimeTicks() {
        return 60;
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.one(dmgStat(), 0.5F, 10);
    }

    @Override
    public Elements getElement() {
        return element;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Summons an erupting volcano: "));

        list.addAll(getCalculation().GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.Volcano;
    }

    @Override
    public Entity newEntity(World world) {
        return new VolcanoEntity(world);
    }
}
