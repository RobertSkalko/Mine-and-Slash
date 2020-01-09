package com.robertx22.mine_and_slash.database.loot_crates;

import com.robertx22.mine_and_slash.database.loot_crates.bases.LootCrate;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.gens.util.GearCreationUtils;
import com.robertx22.mine_and_slash.saveclasses.gearitem.GearItemEnum;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.item.ItemStack;

public class MythicCrate extends LootCrate {

    public static MythicCrate INSTANCE = new MythicCrate();

    private MythicCrate() {

    }

    @Override
    public Words name() {
        return Words.MythicCrate;
    }

    @Override
    public ItemStack generateStack(LootInfo info) {

        GearBlueprint blueprint = new GearBlueprint(info.level);

        blueprint.rarity.setSpecificRarity(IRarity.Mythic);

        return GearCreationUtils.CreateStack(blueprint, GearItemEnum.NORMAL);

    }

    @Override
    public int getRarityRank() {
        return IRarity.Mythic;
    }

    @Override
    public int maxItems() {
        return 3;
    }

    @Override
    public String GUID() {
        return "mythic_crate";
    }

}