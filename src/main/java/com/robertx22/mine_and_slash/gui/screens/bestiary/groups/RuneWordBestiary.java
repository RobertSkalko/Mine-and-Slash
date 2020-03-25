package com.robertx22.mine_and_slash.gui.screens.bestiary.groups;

import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.gui.screens.bestiary.splitters.BaseSplitter;
import com.robertx22.mine_and_slash.gui.screens.bestiary.splitters.RunewordRunesSplitter;
import com.robertx22.mine_and_slash.loot.blueprints.AwakenRuneWordBlueprint;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class RuneWordBestiary extends BestiaryGroup<RuneWord> {
    @Override
    public List<RuneWord> getAll(int lvl) {
        return SlashRegistry.RuneWords()
            .getList();
    }

    @Override
    public ITextComponent getName() {
        return Words.Runeword.locName();
    }

    @Override
    public BaseSplitter<RuneWord> getDefaultSplitter() {
        return new RunewordRunesSplitter();
    }

    @Override
    public ITextComponent getName(ItemStack stack) {
        return stack.getDisplayName();
    }

    @Override
    public String texName() {
        return "runeword";
    }

    @Override
    public ItemStack createStack(int lvl, RuneWord entry) {
        AwakenRuneWordBlueprint blueprint = new AwakenRuneWordBlueprint();
        blueprint.runeWord.set(entry);
        return blueprint.createStack();
    }
}
