package com.robertx22.mine_and_slash.gui.screens.bestiary.groups;

import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.gui.screens.bestiary.splitters.BaseSplitter;
import com.robertx22.mine_and_slash.gui.screens.bestiary.splitters.RuneSplitter;
import com.robertx22.mine_and_slash.loot.blueprints.RuneBlueprint;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class RuneBestiary extends BestiaryGroup<BaseRune> {

    @Override
    public List<BaseRune> getAll(int lvl) {
        return SlashRegistry.Runes()
            .getList();
    }

    @Override
    public ITextComponent getName() {
        return Words.Runes.locName();
    }

    @Override
    public ITextComponent getName(ItemStack stack) {
        return stack.getDisplayName();
    }

    @Override
    public BaseSplitter<BaseRune> getDefaultSplitter() {
        return new RuneSplitter();
    }

    @Override
    public String texName() {
        return "rune";
    }

    @Override
    public ItemStack createStack(int lvl, BaseRune entry) {
        RuneBlueprint blueprint = new RuneBlueprint(lvl);
        blueprint.runePart.set(entry);

        if (!entry.isUnique()) {
            blueprint.rarity.set(Rarities.Runes.get(IRarity.Highest));
        } else {
            blueprint.rarity.set(Rarities.Runes.get(IRarity.Unique));
        }

        return blueprint.createStack();
    }
}
