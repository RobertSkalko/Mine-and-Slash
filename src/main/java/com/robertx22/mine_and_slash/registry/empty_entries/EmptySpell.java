package com.robertx22.mine_and_slash.registry.empty_entries;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class EmptySpell extends BaseSpell {
    @Override
    public SpellType getSpellType() {
        return SpellType.Self_Heal;
    }

    @Override
    public String GUID() {
        return "";
    }

    @Override
    public int getManaCost() {
        return 0;
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
    public SpellSchools getSchool() {
        return SpellSchools.OCEAN_MYSTIC;
    }

    @Override
    public int getCooldownInSeconds() {
        return 0;
    }

    @Override
    public Elements getElement() {
        return Elements.Physical;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {
        return null;
    }

    @Override
    public Words getName() {
        return null;
    }

    @Override
    public boolean cast(LivingEntity caster, int ticksInUse) {
        return false;
    }

}
