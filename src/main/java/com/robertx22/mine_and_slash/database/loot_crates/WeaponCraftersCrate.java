package com.robertx22.mine_and_slash.database.loot_crates;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.loot_crates.bases.LootCrate;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.BlueprintUtils;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

public class WeaponCraftersCrate extends LootCrate {

    public static WeaponCraftersCrate INSTANCE = new WeaponCraftersCrate();

    private WeaponCraftersCrate() {

    }

    @Override
    public ITextComponent name() {
        return Words.WeaponcraftersCrate.locName();
    }

    @Override
    public ItemStack generateStack(LootInfo info) {

        GearBlueprint blueprint = BlueprintUtils.randomGearBlueprint(info.level, info.tier);
        blueprint.rarity.minRarity = 1;

        blueprint.gearItemSlot.set(SlashRegistry.GearTypes()
            .getFilterWrapped(x -> x.family()
                .equals(GearItemSlot.SlotFamily.Weapon))
            .random());

        return blueprint.createStack();

    }

    @Override
    public int getRarityRank() {
        return IRarity.Rare;
    }

    @Override
    public int maxItems() {
        return 4;
    }

    @Override
    public String GUID() {
        return "weaponcrafter_crate";
    }

}