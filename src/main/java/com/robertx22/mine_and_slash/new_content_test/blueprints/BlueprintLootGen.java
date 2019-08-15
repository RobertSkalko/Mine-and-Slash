package com.robertx22.mine_and_slash.new_content_test.blueprints;

import com.robertx22.mine_and_slash.items.misc.ItemAwakenRuneWord;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.gens.BaseLootGen;
import com.robertx22.mine_and_slash.uncommon.datasaving.Blueprint;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import net.minecraft.item.ItemStack;

public class BlueprintLootGen extends BaseLootGen {

    public BlueprintLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float BaseChance() {
        return 2;

    }

    @Override
    public LootType lootType() {
        return LootType.Blueprint;
    }

    @Override
    public boolean condition() {
        return true;
    }

    @Override
    public ItemStack generateOne() {

        return Create(new BlueprintBlueprint(info.level, info.tier));

    }

    public static ItemStack Create(BlueprintBlueprint blueprint) {

        ItemStack stack = new ItemStack(ItemAwakenRuneWord.ITEM);

        BlueprintItemData data = new BlueprintItemData();

        data.level = blueprint.level;
        data.tier = blueprint.tier;
        data.rarity = blueprint.getRarityRank();

        blueprint.generateRequests(data);

        Blueprint.Save(stack, data);

        return stack;

    }

}
