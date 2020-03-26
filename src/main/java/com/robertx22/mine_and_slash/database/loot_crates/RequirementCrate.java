package com.robertx22.mine_and_slash.database.loot_crates;

import com.robertx22.mine_and_slash.database.loot_crates.bases.LootCrate;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.*;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.base.BaseCoreStat;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

public class RequirementCrate extends LootCrate {

    public static RequirementCrate DEX = new RequirementCrate(Dexterity.INSTANCE);
    public static RequirementCrate INT = new RequirementCrate(Intelligence.INSTANCE);
    public static RequirementCrate STR = new RequirementCrate(Strength.INSTANCE);
    public static RequirementCrate WIS = new RequirementCrate(Wisdom.INSTANCE);
    public static RequirementCrate STA = new RequirementCrate(Stamina.INSTANCE);
    public static RequirementCrate VIT = new RequirementCrate(Vitality.INSTANCE);

    BaseCoreStat stat;

    private RequirementCrate(BaseCoreStat stat) {
        this.stat = stat;
    }

    @Override
    public ITextComponent name() {
        return stat.locName()
            .appendText(" ")
            .appendSibling(Words.Crate.locName());
    }

    @Override
    public ItemStack generateStack(LootInfo info) {

        GearBlueprint blueprint = new GearBlueprint(info.level);
        blueprint.rarity.minRarity = 1;

        blueprint.gearItemSlot.set(SlashRegistry.GearTypes()
            .getFilterWrapped(x -> x.getRequirements()
                .getStats()
                .contains(stat))
            .random());

        return blueprint.createStack();

    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public int maxItems() {
        return 4;
    }

    @Override
    public String GUID() {
        return stat.GUID() + "_req_crate";
    }

}