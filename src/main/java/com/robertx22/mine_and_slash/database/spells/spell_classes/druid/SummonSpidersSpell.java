package com.robertx22.mine_and_slash.database.spells.spell_classes.druid;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.summons.SpiderPetEntity;
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

public class SummonSpidersSpell extends BaseSpell {
    @Override
    public SpellSchools getSchool() {
        return SpellSchools.DRUID;
    }

    @Override
    public int getCooldownInSeconds() {
        return 0;
    }

    @Override
    public SpellType getSpellType() {
        return SpellType.Summon;
    }

    @Override
    public String GUID() {
        return "summon_spiders";
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
        return Elements.Nature;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {
        return Arrays.asList();
    }

    @Override
    public Words getName() {
        return Words.SummonSpiders;
    }

    @Override
    public boolean cast(LivingEntity caster, int ticksInUse) {

        SpellUtils.spawnSummon(new SpiderPetEntity(caster.world), this, caster);

        return false;
    }
}
