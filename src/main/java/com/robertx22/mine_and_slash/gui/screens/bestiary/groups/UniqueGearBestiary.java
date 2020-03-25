package com.robertx22.mine_and_slash.gui.screens.bestiary.groups;

import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.gui.screens.bestiary.splitters.BaseSplitter;
import com.robertx22.mine_and_slash.gui.screens.bestiary.splitters.TierSplitter;
import com.robertx22.mine_and_slash.loot.blueprints.UniqueGearBlueprint;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class UniqueGearBestiary extends BestiaryGroup<IUnique> {

    @Override
    public List<IUnique> getAll(int lvl) {
        return SlashRegistry.UniqueGears()
            .getList();
    }

    @Override
    public ITextComponent getName(ItemStack stack) {
        return Gear.Load(stack)
            .GetDisplayName(stack);
    }

    @Override
    public BaseSplitter<IUnique> getDefaultSplitter() {
        return new TierSplitter<IUnique>(this);
    }

    @Override
    public String texName() {
        return "unique_gear";
    }

    @Override
    public ItemStack createStack(int lvl, IUnique uniq) {
        UniqueGearBlueprint blueprint = new UniqueGearBlueprint(lvl, uniq);
        blueprint.unidentifiedPart.set(false);
        blueprint.suffixChancePart.set(false);
        blueprint.prefixChancePart.set(false);
        return blueprint.createStack();
    }
}
