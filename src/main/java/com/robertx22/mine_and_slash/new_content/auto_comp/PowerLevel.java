package com.robertx22.mine_and_slash.new_content.auto_comp;

import com.google.common.collect.Multimap;
import com.robertx22.mine_and_slash.data_generation.compatible_items.CompatibleItem;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.util.math.MathHelper;

import java.util.List;
import java.util.stream.Collectors;

public class PowerLevel {

    public static float MAX_SINGLE_STAT_VALUE = 50;
    public static float MAX_TOTAL_STATS = 200;

    public PowerLevel(Item item, GearItemSlot slot) {

        this.item = item;

        Multimap<String, AttributeModifier> stats = item.getAttributeModifiers(slot.getVanillaSlotType());

        this.statAmount = stats.size();

        this.totalStatNumbers = stats.values()
            .stream()
            .mapToInt(x -> (int) MathHelper.clamp(x.getAmount(), -MAX_SINGLE_STAT_VALUE, MAX_SINGLE_STAT_VALUE))
            .sum();

        totalStatNumbers = MathHelper.clamp(totalStatNumbers, -MAX_TOTAL_STATS, MAX_TOTAL_STATS);

    }

    public static float getFloatValueOf(Item item) {
        List<GearItemSlot> slots = SlashRegistry.GearTypes()
            .getList()
            .stream()
            .filter(x -> x.isGearOfThisType(item))
            .collect(Collectors.toList());

        float val = 0;

        for (GearItemSlot slot : slots) {
            PowerLevel power = new PowerLevel(item, slot);

            PowerLevel best = DeterminePowerLevels.STRONGEST.get(slot);

            val += power.divideBy(best);

        }

        val *= slots.size();

        return val;
    }

    public enum Types {
        TRASH(0.01F) {
            @Override
            public CompatibleItem getAutoCompatibleItem(Item item, GearItemSlot slot) {

                CompatibleItem comp = CompatibleItem.getDefaultAuto(item, slot);
                comp.max_rarity = IRarity.Rare;
                return comp;

            }
        }, NORMAL(0.3F) {
            @Override
            public CompatibleItem getAutoCompatibleItem(Item item, GearItemSlot slot) {

                CompatibleItem comp = CompatibleItem.getDefaultAuto(item, slot);
                comp.max_rarity = IRarity.Legendary;
                return comp;

            }
        }, BEST(0.8F) {
            @Override
            public CompatibleItem getAutoCompatibleItem(Item item, GearItemSlot slot) {

                CompatibleItem comp = CompatibleItem.getDefaultAuto(item, slot);
                comp.max_rarity = IRarity.Mythic;
                comp.min_rarity = IRarity.Uncommon;
                return comp;

            }
        }, NONE(0) {
            @Override
            public CompatibleItem getAutoCompatibleItem(Item item, GearItemSlot slot) {
                return null;
            }
        };

        Types(float floatReq) {
            this.floatReq = floatReq;
        }

        public abstract CompatibleItem getAutoCompatibleItem(Item item, GearItemSlot slot);

        public float floatReq;
    }

    public static Types getPowerClassification(Item item) {

        float val = getFloatValueOf(item);

        if (val > Types.BEST.floatReq) {
            return Types.BEST;
        }
        if (val > Types.NORMAL.floatReq) {
            return Types.NORMAL;
        }
        if (val > Types.TRASH.floatReq) {
            return Types.TRASH;
        }

        return Types.NONE;
    }

    public boolean isStrongerThan(PowerLevel other) {
        return totalStatNumbers > other.totalStatNumbers;
    }

    public float divideBy(PowerLevel other) {
        return totalStatNumbers / other.totalStatNumbers;
    }

    public Item item;
    public int statAmount = 0;
    public float totalStatNumbers = 0;

}
