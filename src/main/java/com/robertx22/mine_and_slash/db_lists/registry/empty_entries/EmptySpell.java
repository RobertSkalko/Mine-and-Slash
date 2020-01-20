package com.robertx22.mine_and_slash.db_lists.registry.empty_entries;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.EffectCalculation;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
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
    public int getBaseValue() {
        return 0;
    }

    @Override
    public EffectCalculation ScalingValue() {
        return new EffectCalculation(new EmptyStat(), 1);
    }

    @Override
    public Elements getElement() {
        return Elements.Physical;
    }

    @Override
    public ITextComponent GetDescription(SpellItemData data) {
        return new StringTextComponent("");
    }

    @Override
    public boolean cast(PlayerEntity caster, int ticksInUse, SpellItemData data) {
        return false;
    }
}
