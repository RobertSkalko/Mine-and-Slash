package com.robertx22.mine_and_slash.loot.blueprints;

import com.robertx22.mine_and_slash.database.map_affixes.BaseMapAffix;
import com.robertx22.mine_and_slash.database.rarities.MapRarity;
import com.robertx22.mine_and_slash.database.rarities.RaritiesContainer;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.initializers.WorldProviders;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.items.misc.ItemMap;
import com.robertx22.mine_and_slash.quests.base.Quest;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.saveclasses.mapitem.MapAffixData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Map;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MapBlueprint extends ItemBlueprint {

    public static final int PERMADEATH_CHANCE = 10;

    public MapBlueprint(int level, int worldTier) {
        super(level);

        this.tier.minTier = 1;
        this.tier.number = worldTier;
    }

    public boolean getIsPermaDeath() {
        return RandomUtils.roll(PERMADEATH_CHANCE);
    }

    public void rollSetupGrouPlay(MapItemData data, MapRarity rarity) {

        if (RandomUtils.roll(rarity.groupPlayChance())) {
            data.groupPlay = true;
            data.maxPlayersInGroup = RandomUtils.RandomRange(3, 8);
        }

    }

    @Override
    public RaritiesContainer<? extends Rarity> getRarityContainer() {
        return Rarities.Maps;
    }

    @Override
    public ItemStack generate() {
        MapItemData data = createData();
        MapRarity rarity = (MapRarity) this.rarity.get();
        ItemStack stack = new ItemStack(ItemMap.Items.get(rarity.Rank()));

        Map.Save(stack, data);

        return stack;

    }

    public MapItemData createData() {
        MapItemData data = new MapItemData();
        MapRarity rarity = (MapRarity) this.rarity.get();

        data.rarity = rarity.Rank();

        Quest quest = SlashRegistry.Quests().getWrapped().random();

        data.questGUID = quest.GUID();

        data.minutes = quest.minutes();

        data.tier = tier.get();

        data.isPermaDeath = getIsPermaDeath();

        data.rewardCrateGUID = SlashRegistry.LootCrates().getWrapped().random().GUID();

        rollSetupGrouPlay(data, rarity);

        data.level = level.get();

        genAffixes(data, rarity);

        data.worldGeneratorName = WorldProviders.INSTANCE.random().GUID();

        return data;
    }

    private MapItemData genAffixes(MapItemData map, MapRarity rarity) {

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
