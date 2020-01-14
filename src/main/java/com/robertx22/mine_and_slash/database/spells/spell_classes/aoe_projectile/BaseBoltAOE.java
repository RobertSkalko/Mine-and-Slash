package com.robertx22.mine_and_slash.database.spells.spell_classes.aoe_projectile;

import com.robertx22.mine_and_slash.database.spells.spell_classes.projectile.BaseBolt;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import net.minecraft.util.text.ITextComponent;

public abstract class BaseBoltAOE extends BaseBolt {

    @Override
    public int getManaCost() {
        return 25;
    }

    @Override
    public int getBaseValue() {
        return 15;
    }

    @Override
    public ITextComponent GetDescription(SpellItemData data) {
        return CLOC.tooltip("aoe_spell_explosion");

    }

}
