package com.robertx22.mine_and_slash.loot.gens;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.map_affixes.BaseMapAffix;
import com.robertx22.mine_and_slash.database.rarities.MapRarity;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.initializers.WorldProviders;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.items.misc.ItemMap;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.MapBlueprint;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.saveclasses.mapitem.MapAffixData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Map;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MapLootGen extends BaseLootGen {

    public MapLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float BaseChance() {
        return ModConfig.INSTANCE.DropRates.MAP_DROPRATE.get().floatValue();
    }

    @Override
    public LootType lootType() {
        return LootType.Map;
    }

    @Override
    public boolean condition() {
        return info.level >= ModConfig.INSTANCE.Server.MAPS_DROP_AFTER_LEVEL.get();
    }

    @Override
    public boolean hasLevelDistancePunishment() {
        return false;
    }

    @Override
    public ItemStack generateOne() {

        MapBlueprint blueprint = new MapBlueprint(info.level, info.tier);

        if (blueprint.level >= ModConfig.INSTANCE.Server.MAPS_DROP_AFTER_LEVEL.get()) {
            return Create(blueprint);
        } else {
            return ItemStack.EMPTY;
        }

    }

    public static ItemStack Create(MapBlueprint blueprint) {

        MapItemData data = new MapItemData();
        MapRarity rarity = Rarities.Maps.get(blueprint.getRarity());
        ItemStack stack = new ItemStack(ItemMap.Items.get(rarity.Rank()));

        data.rarity = rarity.Rank();

        data.minutes = RandomUtils.RandomRange(15, 60);

        data.tier = blueprint.getTier();

        data.isPermaDeath = blueprint.getIsPermaDeath();

        blueprint.rollSetupGrouPlay(data, rarity);

        data.level = blueprint.getLevel();

        data = genAffixes(data, rarity);

        data.worldGeneratorName = WorldProviders.INSTANCE.random().GUID();

        Map.Save(stack, data);

        return stack;

    }

    private static MapItemData genAffixes(MapItemData map, MapRarity rarity) {

        int amount = RandomUtils.RandomRange(rarity.AffixAmount().Min, rarity.AffixAmount().Max);

        List<String> affixes = new ArrayList<String>();

        for (int i = 0; i < amount; i++) {

            BaseMapAffix affix = RandomUtils.weightedRandom(SlashRegistry.MapAffixes()
                    .getAll()
                    .values());

            while (affixes.contains(affix.GUID()) || affix.isBeneficial()) { // can't have moba affixes arndom anymore. only on dimension types
                affix = RandomUtils.weightedRandom(SlashRegistry.MapAffixes()
                        .getAll()
                        .values());
            }

            int percent = RandomUtils.RandomRange(rarity.StatPercents().Min, rarity.StatPercents().Max);

            map.affixes.add(new MapAffixData(affix, percent));
            affixes.add(affix.GUID());

        }

        return map;
    }

}
