package com.robertx22.mine_and_slash.new_content_test;

import com.robertx22.mine_and_slash.database.requirements.GearRequestedFor;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.traits.major_arcana.BaseMajorArcana;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.ChaosStatsData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.PrefixData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.SetData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

import java.util.stream.Collectors;

public enum GearBlueprintSpecialEffects implements IWeighted, IRarity {

    ALWAYS_SET("always_set", Words.AlwaysSet) {
        @Override
        public void modify(GearItemData gear) {
            gear.set = new SetData();
            gear.set = gear.set.generate(gear);

        }
    },
    ALWAYS_CHAOS_STATS("always_chaos_stats", Words.AlwaysChaosStats) {
        @Override
        public void modify(GearItemData gear) {
            gear.chaosStats = new ChaosStatsData();
            gear.chaosStats.RerollFully(gear);

        }
    },
    MYTHIC_AFFIXES("mythic_affixes", Words.AlwaysMythicAffixes) {
        @Override
        public int getRarityRank() {
            return IRarity.Legendary;
        }

        @Override
        public void modify(GearItemData gear) {

            gear.prefix = new PrefixData();

            gear.prefix.create(gear, SlashRegistry.Prefixes()
                    .getWrapped()
                    .allThatMeetRequirement(new GearRequestedFor(gear))
                    .ofExactRarity(5)
                    .random());
        }
    },

    MAJOR_ARCANA_CHAOS_STAT("major_arcana_chaos", Words.AlwaysMajorArcana) {
        @Override
        public int getRarityRank() {
            return IRarity.Legendary;
        }

        @Override
        public void modify(GearItemData gear) {

            StatMod mod = RandomUtils.weightedRandom(SlashRegistry.StatMods()
                    .getList()
                    .stream()
                    .filter(x -> x.GetBaseStat() instanceof BaseMajorArcana)
                    .collect(Collectors.toList()));

            gear.chaosStats = new ChaosStatsData();
            gear.chaosStats.create(gear, mod);

        }
    };

    GearBlueprintSpecialEffects(String guid, Words word) {
        this.guid = guid;
        this.word = word;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Items.get(getRarityRank());
    }

    @Override
    public int Weight() {
        return getRarity().Weight();
    }

    @Override
    public int getRarityRank() {
        return IRarity.Uncommon;
    }

    public String guid;

    public Words word;

    public abstract void modify(GearItemData gear);
}
