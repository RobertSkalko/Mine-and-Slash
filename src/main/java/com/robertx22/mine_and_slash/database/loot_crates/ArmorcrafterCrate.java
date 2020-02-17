package com.robertx22.mine_and_slash.database.loot_crates;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.loot_crates.bases.LootCrate;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.BlueprintUtils;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.gens.util.GearCreationUtils;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

public class ArmorcrafterCrate extends LootCrate {

    public static ArmorcrafterCrate INSTANCE = new ArmorcrafterCrate();

    private ArmorcrafterCrate() {

    }

    @Override
    public ITextComponent name() {
        return Words.ArmorCrafterCrate.locName();
    }

    @Override
    public ItemStack generateStack(LootInfo info) {

        GearBlueprint blueprint = BlueprintUtils.randomGearBlueprint(info.level, info.tier);

        blueprint.rarity.minRarity = 1;

        blueprint.gearItemSlot.set(SlashRegistry.GearTypes()
                                           .getFilterWrapped(x -> x.slotType().equals(GearItemSlot.GearSlotType.Armor))
                                           .random());

        return GearCreationUtils.CreateStack(blueprint);

    }

    @Override
    public int getRarityRank() {
        return IRarity.Rare;
    }

    @Override
    public int maxItems() {
        return 8;
    }

    @Override
    public String GUID() {
        return "armorcrafter_crate";
    }

}