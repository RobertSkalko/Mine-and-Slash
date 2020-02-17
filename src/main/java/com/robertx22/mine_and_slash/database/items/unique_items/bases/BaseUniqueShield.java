package com.robertx22.mine_and_slash.database.items.unique_items.bases;

import com.robertx22.mine_and_slash.items.gearitems.offhands.IEffectItem;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public final class BaseUniqueShield extends ShieldItem implements IEffectItem {

    public BaseUniqueShield() {
        super(new Properties().maxStackSize(1)
            .defaultMaxDamage(1000));

    }

    @Override
    public List<ITextComponent> getEffectTooltip(boolean moreInfo) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(color() + "" + TextFormatting.BOLD + "[Active]: " + TextFormatting.RESET + color() + "Block"));
        if (moreInfo) {
            list.add(new StringTextComponent(color() + "DMG Reduced Based on Block Strength"));
        }
        return list;
    }
}

