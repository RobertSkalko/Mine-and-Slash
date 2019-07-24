package com.robertx22.mine_and_slash.spells.aoe_projectile;

import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import com.robertx22.mine_and_slash.spells.projectile.BaseBolt;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import net.minecraft.util.text.ITextComponent;

public abstract class BaseBoltAOE extends BaseBolt {

    @Override
    public int ManaCost() {
        return 25;
    }

    @Override
    public int BaseValue() {
        return 7;
    }

    @Override
    public ITextComponent GetDescription(SpellItemData data) {
        return CLOC.tooltip("aoe_spell_explosion");

    }

}
