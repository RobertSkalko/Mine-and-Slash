package com.robertx22.mine_and_slash.database.loot_crates;

import com.robertx22.mine_and_slash.database.loot_crates.bases.LootCrate;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.UniqueGearBlueprint;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

public class UniqueCrate extends LootCrate {
    public static UniqueCrate INSTANCE = new UniqueCrate();

    private UniqueCrate() {

    }

    @Override
    public ITextComponent name() {
        return Words.UniqueCrate.locName();
    }

    @Override
    public ItemStack generateStack(LootInfo info) {

        UniqueGearBlueprint blueprint = new UniqueGearBlueprint(info.level, info.tier + RandomUtils.RandomRange(0, 3), true);

        ItemStack stack = blueprint.createStack();

        return stack;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public int maxItems() {
        return 3;
    }

    @Override
    public String GUID() {
        return "unique_crate";
    }

}
