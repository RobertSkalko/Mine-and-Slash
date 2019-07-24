package com.robertx22.mine_and_slash.saveclasses.item_classes;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.rarities.RuneRarity;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.items.currency.CurrencyItem;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Rune;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.DataItemType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ListUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Tooltip;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

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

            Item item = RandomUtils.weightedRandom(ListUtils.SameTierOrLess(CurrencyItem.ITEMS, 10));

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
    public void BuildTooltip(ItemStack stack, ItemTooltipEvent event, Unit unit,
                             EntityCap.UnitData data) {

        List<ITextComponent> tooltip = event.getToolTip();

        RuneItemData rune = Rune.Load(stack);

        if (rune != null) {

            tooltip.add(TooltipUtils.level(rune.level));

            RuneRarity rar = rune.getRarity();

            TooltipInfo info = new TooltipInfo(new EntityCap.DefaultImpl(), rar.StatPercents(), rune.level);

            if (rune.armor != null) {
                Tooltip.add(Styles.GRAYCOMP()
                        .appendSibling(Words.Armor.locName().appendText(":")), tooltip);
                for (ITextComponent str : rune.armor.GetTooltipString(info)) {
                    Tooltip.add(str, tooltip);
                }
                Tooltip.add("", tooltip);
            }
            if (rune.weapon != null) {

                Tooltip.add(Styles.GRAYCOMP()
                        .appendSibling(Words.Weapon.locName().appendText(":")), tooltip);
                for (ITextComponent str : rune.weapon.GetTooltipString(info)) {
                    Tooltip.add(str, tooltip);
                }
            }
            if (rune.jewerly != null) {

                Tooltip.add("", tooltip);
                Tooltip.add(Styles.GRAYCOMP()
                        .appendSibling(Words.Jewerly.locName().appendText(":")), tooltip);
                for (ITextComponent str : rune.jewerly.GetTooltipString(info)) {
                    Tooltip.add(str, tooltip);
                }
                Tooltip.add("", tooltip);
            }

            Tooltip.add(TooltipUtils.rarity(rune.getRarity()), tooltip);

            Tooltip.add("", tooltip);

            Tooltip.add(Styles.BLUECOMP()
                    .appendSibling(Words.Item_modifiable_in_station.locName()), tooltip);

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
