package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import net.minecraft.util.text.ITextComponent;

public abstract class BaseBoltAOE extends BaseBolt {

    @Override
    public int getManaCost() {
        return 25;
    }

    @Override
    public ITextComponent GetDescription() {
        return CLOC.tooltip("aoe_spell_explosion");

    }

}
