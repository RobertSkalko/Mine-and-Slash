package com.robertx22.mine_and_slash.blocks.conditions;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class NoCondition extends LootCrateCondition {

    @Override
    public boolean canOpenCrate(PlayerEntity player) {
        return true;
    }

    @Override
    public ITextComponent tellCondition() {
        return new StringTextComponent("");
    }
}
