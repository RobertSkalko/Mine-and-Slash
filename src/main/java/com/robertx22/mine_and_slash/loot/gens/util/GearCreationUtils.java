package com.robertx22.mine_and_slash.loot.gens.util;

import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.RunedGearBlueprint;
import com.robertx22.mine_and_slash.loot.blueprints.UniqueGearBlueprint;
import com.robertx22.mine_and_slash.registry.FilterListWrap;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.*;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.saveclasses.rune.RunesData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
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

        if (blueprint instanceof RunedGearBlueprint) {
            type = GearItemEnum.RUNED;
        } else if (blueprint instanceof UniqueGearBlueprint) {
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

        data.level = blueprint.level.get();
        data.Rarity = rarity.Rank();

        if (blueprint instanceof UniqueGearBlueprint && canMakeUnique(blueprint)) {

            UniqueGearBlueprint uniqprint = (UniqueGearBlueprint) blueprint;

            IUnique unique = uniqprint.unique.get();

            if (unique != null) {

                blueprint.gearItemSlot.forceSet(unique.getGearSlot());

                data.gearTypeName = unique.getGearSlot()
                    .GUID();

                data.isUnique = true;

                data.uniqueGUID = unique.GUID();
                data.uniqueStats = new UniqueStatsData(unique.GUID());
                data.uniqueStats.RerollFully(data);

                if (unique.canGetSet()) {
                    data.set = blueprint.getSet(data)
                        .getSetData();
                }

            } else {
                data.Rarity = IRarity.Common;
            }
        } else {
            blueprint.gearItemSlot.get()
                .GUID();
        }

        data.gearTypeName = blueprint.gearItemSlot.get()
            .GUID();

        data.requirements = new StatRequirementsData();
        data.requirements.create(data);

        if (type.canGetPrimaryStats()) {
            data.primaryStats = new PrimaryStatsData();
            data.primaryStats.RerollFully(data);
        }
        if (type.canGetSecondaryStats()) {
            data.secondaryStats = new SecondaryStatsData();
            data.secondaryStats.RerollFully(data);
        }
        if (type.canGetChaosStats()) {
            if (blueprint.getsChaosStats()) {
                data.chaosStats = new ChaosStatsData();
                data.chaosStats.RerollFully(data);
            }
        }

        if (type.canGetAffixes()) {
            if (RandomUtils.roll(rarity.AffixChance())) {

                data.suffix = new SuffixData();
                data.suffix.RerollFully(data);

            }
            if (RandomUtils.roll(rarity.AffixChance())) {

                data.prefix = new PrefixData();
                data.prefix.RerollFully(data);

            }
        }
        if (type.canGetSet()) {
            data.set = blueprint.getSet(data)
                .getSetData();
        }

        if (type == GearItemEnum.RUNED) {
            data.runes = new RunesData();
            data.runes.capacity = rarity.runeSlots();
        }

        return data;
    }
}
