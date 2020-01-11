package com.robertx22.mine_and_slash.database.spells.entities.bases;

import net.minecraft.entity.IRendersAsItem;
import net.minecraft.item.ItemStack;

public interface IMyRenderAsItem extends IRendersAsItem {
    ItemStack getItem();
}
