package com.robertx22.mine_and_slash.database.items.currency;

import com.robertx22.mine_and_slash.database.items.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Tooltip;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public interface ICurrencyItemEffect {

    public abstract ItemStack ModifyItem(ItemStack stack, ItemStack currency);

    public abstract List<BaseLocRequirement> requirements();

    boolean canItemBeModified(ItemStack item, ItemStack currency);

    public default void addToTooltip(List<ITextComponent> tooltip) {

        if (Screen.hasShiftDown()) {
            tooltip.add(Tooltip.color(TextFormatting.RED, Words.Requirements.locName()
                    .appendText(": ")));

            for (BaseLocRequirement req : requirements()) {
                tooltip.add(Tooltip.color(TextFormatting.RED, req.getText()));
            }
        } else {
            tooltip.add(Tooltip.color(TextFormatting.GREEN, Words.PressShiftForRequirements
                    .locName()));

        }
    }

}
