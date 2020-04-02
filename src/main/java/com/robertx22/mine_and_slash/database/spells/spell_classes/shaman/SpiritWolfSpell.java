package com.robertx22.mine_and_slash.database.spells.spell_classes.shaman;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.summons.SpiritWolfPetEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;

import java.util.Arrays;
import java.util.List;

public class SpiritWolfSpell extends BaseSpell {
    @Override
    public SpellSchools getSchool() {
        return SpellSchools.SHAMAN;
    }

    @Override
    public int getCooldownInSeconds() {
        return 0;
    }

    @Override
    public BaseSpell.SpellType getSpellType() {
        return SpellType.Summon;
    }

    @Override
    public String GUID() {
        return "spirit_wolves";
    }

    @Override
    public int getManaCost() {
        return 30;
    }

    @Override
    public int useTimeTicks() {
        return 0;
    }

    @Override
    public SpellCalcData getCalculation() {
        return null;
    }

    @Override
    public Elements getElement() {
        return Elements.Thunder;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {
        return Arrays.asList();
    }

    @Override
    public Words getName() {
        return Words.SpiritWolves;
    }

    @Override
    public boolean cast(LivingEntity caster, int ticksInUse) {

        SpellUtils.spawnSummon(new SpiritWolfPetEntity(caster.world), this, caster);

        return false;
    }
}

