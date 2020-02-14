package com.robertx22.mine_and_slash.saveclasses.item_classes;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.rarities.RuneRarity;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipContext;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.ItemType;
import com.robertx22.mine_and_slash.uncommon.datasaving.Rune;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.DataItemType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

@Storable
public class RuneItemData implements ICommonDataItem<RuneRarity> {

    public RuneItemData() {

    }

    @Store
    public String name;

    @Store
    public int level = 1;

    @Store
    public int tier = 0;

    @Store
    public StatModData weapon;
    @Store
    public StatModData armor;
    @Store
    public StatModData jewerly;

    @Override
    public void saveToStack(ItemStack stack) {
        Rune.Save(stack, this);
    }

    @Override
    public String getUniqueGUID() {
        return this.name;
    }

    public StatModData getModFor(GearItemData gear) {

        GearItemSlot slot = gear.GetBaseGearType();

        if (slot.slotType().equals(GearItemSlot.GearSlotType.Armor)) {
            return armor;
        } else if (slot.slotType().equals(GearItemSlot.GearSlotType.Jewerly)) {
            return jewerly;
        }

        return weapon;

    }

    @Store
    public int rarity = 0;

    @Override
    public ItemStack getSalvageResult(float salvageBonus) {

        int min = tryIncreaseAmount(salvageBonus, 1);
        int max = tryIncreaseAmount(salvageBonus, 2);

        ItemStack stack = ItemStack.EMPTY;

        if (RandomUtils.roll(this.getRarity().specialItemChance())) {

            Item item = SlashRegistry.CurrencyItems()
                    .getWrapped()
                    .ofCurrencyUsableOnItemType(ItemType.GEAR)
                    .ofTierOrLess(this.rarity + 10)
                    .random();

            stack = new ItemStack(item);
        } else {

            int amount = RandomUtils.RandomRange(min, max);

            ItemOre ore = (ItemOre) ItemOre.ItemOres.get(rarity);

            stack = new ItemStack(ore);
            stack.setCount(amount);

        }

        return stack;
    }

    @Override
    public DataItemType getDataType() {
        return DataItemType.RUNE;
    }

    @Override
    public void BuildTooltip(TooltipContext ctx) {

        List<ITextComponent> tooltip = ctx.event.getToolTip();

        RuneItemData rune = Rune.Load(ctx.stack);

        if (rune != null) {

            tooltip.add(TooltipUtils.level(rune.level));

            RuneRarity rar = rune.getRarity();

            TooltipInfo info = new TooltipInfo(new EntityCap.DefaultImpl(), rar.StatPercents(), rune.level);

            if (rune.armor != null) {
                tooltip.add(Styles.GRAYCOMP().appendSibling(Words.Armor.locName().appendText(":")));
                for (ITextComponent str : rune.armor.GetTooltipString(info)) {
                    tooltip.add(str);
                }
                TooltipUtils.addEmpty(tooltip);
            }
            if (rune.weapon != null) {

                tooltip.add(Styles.GRAYCOMP().appendSibling(Words.WeaponOffhand.locName().appendText(":")));
                for (ITextComponent str : rune.weapon.GetTooltipString(info)) {
                    tooltip.add(str);
                }
            }
            if (rune.jewerly != null) {

                TooltipUtils.addEmpty(tooltip);
                tooltip.add(Styles.GRAYCOMP().appendSibling(Words.Jewerly.locName().appendText(":")));
                for (ITextComponent str : rune.jewerly.GetTooltipString(info)) {
                    tooltip.add(str);
                }
                TooltipUtils.addEmpty(tooltip);
            }

            tooltip.add(TooltipUtils.rarity(rune.getRarity()));

            if (this.tier > 0) {
                TooltipUtils.addEmpty(tooltip);
                tooltip.add(TooltipUtils.tier(tier));
            }

            TooltipUtils.addEmpty(tooltip);

            tooltip.add(Styles.BLUECOMP().appendSibling(Words.Item_modifiable_in_station.locName()));

        }
    }

    @Override
    public int getRarityRank() {
        return this.rarity;
    }

    @Override
    public RuneRarity getRarity() {
        return Rarities.Runes.get(rarity);
    }

    @Override
    public int Tier() {
        return this.tier;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public String getSpecificType() {
        return this.name;
    }
}
