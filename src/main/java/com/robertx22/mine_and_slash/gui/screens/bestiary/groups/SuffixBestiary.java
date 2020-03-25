package com.robertx22.mine_and_slash.gui.screens.bestiary.groups;

import com.robertx22.mine_and_slash.database.affixes.BaseAffix;
import com.robertx22.mine_and_slash.gui.screens.bestiary.splitters.BaseSplitter;
import com.robertx22.mine_and_slash.gui.screens.bestiary.splitters.LevelReqAffixSplitter;
import com.robertx22.mine_and_slash.items.misc.BaseAffixItem;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class SuffixBestiary extends BestiaryGroup<BaseAffix> {

    @Override
    public List<BaseAffix> getAll(int lvl) {
        return SlashRegistry.Affixes()
            .getWrapped()
            .ofAffixType(BaseAffix.Type.suffix).list;
    }

    @Override
    public ITextComponent getName() {
        return Words.Suffix.locName();
    }

    @Override
    public BaseSplitter<BaseAffix> getDefaultSplitter() {
        return new LevelReqAffixSplitter(this);
    }

    @Override
    public ITextComponent getName(ItemStack stack) {
        return stack.getDisplayName();
    }

    @Override
    public String texName() {
        return "suffix";
    }

    @Override
    public ItemStack createStack(int lvl, BaseAffix entry) {
        return BaseAffixItem.getItemFor(entry);
    }
}

