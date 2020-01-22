package com.robertx22.mine_and_slash.db_lists.registry.empty_entries;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

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
    public ITextComponent GetDescription() {
        return new StringTextComponent("");
    }

    @Override
    public boolean cast(PlayerEntity caster, int ticksInUse) {
        return false;
    }
}
