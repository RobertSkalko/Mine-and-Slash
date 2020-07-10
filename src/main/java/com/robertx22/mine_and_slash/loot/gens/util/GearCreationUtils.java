package com.robertx22.mine_and_slash.loot.gens.util;

import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.UniqueGearBlueprint;
import com.robertx22.mine_and_slash.registry.FilterListWrap;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.BaseStatsData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.PrefixData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.SuffixData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.UniqueStatsData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.GearItemEnum;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.item.ItemStack;

public class GearCreationUtils {

    public static ItemStack CreateStack(GearItemData data) {

        ItemStack stack = new ItemStack(data.getItem());

        Gear.Save(stack, data);

        return stack;

    }

    public static ItemStack CreateStack(GearBlueprint blueprint) {

        GearItemData data = CreateData(blueprint);

        ItemStack stack = new ItemStack(data.getItem());

        Gear.Save(stack, data);

        return stack;

    }

    public static ItemStack CreateStack(GearBlueprint blueprint, GearItemEnum type) {

        GearItemData data = CreateData(blueprint, type);

        ItemStack stack = new ItemStack(data.getItem());

        Gear.Save(stack, data);

        return stack;

    }

    public static GearItemData CreateData(GearBlueprint blueprint) {

        GearItemEnum type = GearItemEnum.random();

        if (blueprint instanceof UniqueGearBlueprint) {
            type = GearItemEnum.UNIQUE;
        }

        return CreateData(blueprint, type);

    }

    public static boolean canMakeUnique(GearBlueprint blueprint) {
        FilterListWrap<IUnique> wrap = SlashRegistry.UniqueGears()
            .getWrapped()
            .ofTierOrLess(blueprint.tier.get());

        if (blueprint.gearItemSlot.isGenerated()) {

            wrap = wrap.ofSpecificGearType(blueprint.gearItemSlot.get()
                .GUID());
        }

        return !wrap.list.isEmpty();

    }

    public static GearItemData CreateData(GearBlueprint blueprint, GearItemEnum type) {

        GearRarity rarity = (GearRarity) blueprint.rarity.get();
        GearItemData data = new GearItemData();

        data.rarity = rarity.Rank();

        if (blueprint instanceof UniqueGearBlueprint) {

            if (!canMakeUnique(blueprint)) {
                data.rarity = Rarities.Gears.random()
                    .Rank();

            } else {

                UniqueGearBlueprint uniqprint = (UniqueGearBlueprint) blueprint;

                IUnique unique = uniqprint.unique.get();

                if (unique != null) {

                    blueprint.gearItemSlot.forceSet(unique.getGearSlot());

                    data.gear_type = unique.getGearSlot()
                        .GUID();

                    data.is_unique = true;

                    data.unique_id = unique.GUID();
                    data.uniqueStats = new UniqueStatsData(unique.GUID());
                    data.uniqueStats.RerollFully(data);

                } else {
                    data.rarity = IRarity.Common;
                }
            }
        } else {
            blueprint.gearItemSlot.get()
                .GUID();
        }

        data.gear_type = blueprint.gearItemSlot.get()
            .GUID();

        if (type.canGetPrimaryStats()) {
            data.baseStats = new BaseStatsData();
            data.baseStats.RerollFully(data);
        }

        if (type.canGetAffixes()) {
            int maxOfEachAffixType = blueprint.gearItemSlot.get()
                .maximumRareAffixes(rarity) / 2;

            for (int i = 0; i < maxOfEachAffixType; i++) {

                if (blueprint.suffixChancePart.get()) {
                    SuffixData suffix = new SuffixData();
                    suffix.RerollFully(data);
                    data.suffixes.add(suffix);
                }

                if (blueprint.prefixChancePart.get()) {
                    PrefixData prefix = new PrefixData();
                    prefix.RerollFully(data);
                    data.prefixes.add(prefix);
                }

            }
        }

        int minaffixes = rarity.minAffixes();
        int affixesToGen = minaffixes - data.getNumberOfAffixes();

        while (affixesToGen > 0) {

            if (data.getNumberOfPrefixes() > data.getNumberOfSuffixes()) {
                SuffixData suffix = new SuffixData();
                suffix.RerollFully(data);
                data.suffixes.add(suffix);
            } else {
                PrefixData prefix = new PrefixData();
                prefix.RerollFully(data);
                data.prefixes.add(prefix);
            }

            affixesToGen--;
        }

        if (blueprint.unidentifiedPart.get()) {
            data.setIdentified(false);
        }

        return data;
    }
}
