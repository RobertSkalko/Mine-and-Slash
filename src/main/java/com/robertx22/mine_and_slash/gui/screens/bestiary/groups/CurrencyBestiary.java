package com.robertx22.mine_and_slash.gui.screens.bestiary.groups;

import com.robertx22.mine_and_slash.database.currency.base.CurrencyItem;
import com.robertx22.mine_and_slash.gui.screens.bestiary.splitters.BaseSplitter;
import com.robertx22.mine_and_slash.gui.screens.bestiary.splitters.TierSplitter;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class CurrencyBestiary extends BestiaryGroup<CurrencyItem> {
    @Override
    public List<CurrencyItem> getAll(int lvl) {
        return SlashRegistry.CurrencyItems()
            .getList();
    }

    @Override
    public BaseSplitter<CurrencyItem> getDefaultSplitter() {
        return new TierSplitter<CurrencyItem>(this);
    }

    @Override
    public ITextComponent getName(ItemStack stack) {
        return stack.getDisplayName();
    }

    @Override
    public String texName() {
        return "currency";
    }

    @Override
    public ItemStack createStack(int lvl, CurrencyItem entry) {
        return new ItemStack(entry);
    }
}
