package com.robertx22.mine_and_slash.saveclasses.item_classes;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.*;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.GearItemEnum;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipContext;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.ItemType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.DataItemType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ItemUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@Storable
public class GearItemData implements ICommonDataItem<GearRarity> {

    public boolean meetsRequirements(EntityCap.UnitData data) {
        return true;
    }

    @Store
    public boolean is_unique = false;

    @Store
    public String unique_id = "";

    @Store
    public int rarity;

    @Store
    public int rare_prefix = -1;
    @Store
    public int rare_suffix = -1;

    @Store
    public boolean is_not_my_mod = false;

    @Store
    public String gear_type = "";

    @Store
    private boolean ided = true;

    public boolean isIdentified() {
        return ided;
    }

    public void setIdentified(boolean bool) {
        this.ided = bool;
    }

    public GearItemEnum getGearEnum() {

        if (this.isUnique()) {
            return GearItemEnum.UNIQUE;
        }

        return GearItemEnum.NORMAL;
    }

    @Override
    public int getRarityRank() {
        return MathHelper.clamp(rarity, -1, IRarity.Highest);
    }

    @Override
    public GearRarity getRarity() {
        return Rarities.Gears.get(this.rarity);
    }

    public boolean changesItemStack() {
        return this.is_not_my_mod == false;
    }

    public ITextComponent name(ItemStack stack) {

        return stack.getDisplayName();

    }

    // Stats
    @Store
    public BaseStatsData baseStats = new BaseStatsData();
    @Store
    public GearAffixesData affixes = new GearAffixesData();
    @Store
    public UniqueStatsData uniqueStats;

    // Stats

    @Store
    public boolean isSalvagable = true;

    // used when upgrading item rarity
    public Item getItem() {
        if (is_unique) {
            return SlashRegistry.UniqueGears()
                .get(unique_id)
                .getUniqueItem();
        } else {
            if (gear_type.isEmpty()) {
                return Items.AIR;
            } else {
                return SlashRegistry.GearTypes()
                    .get(gear_type)
                    .getItem();
            }
        }

    }

    public void WriteOverDataThatShouldStay(GearItemData newdata) {

        newdata.isSalvagable = this.isSalvagable;
        newdata.is_not_my_mod = this.is_not_my_mod;

    }

    public GearItemSlot GetBaseGearType() {
        return SlashRegistry.GearTypes()
            .get(gear_type);
    }

    public ITextComponent getOnGroundDisplayName() {
        return new SText(getRarity().textFormatting() + "").appendSibling(GetBaseGearType().locName());
    }

    public ITextComponent GetDisplayName(ItemStack stack) {

        TextFormatting format = this.getRarity()
            .textFormatting();

        if (!isIdentified()) {
            ITextComponent text = new SText(format + "")
                .appendSibling(Words.Unidentified.locName())
                .appendText(" ")
                .appendSibling(getRarity().locName())
                .appendText(" ")
                .appendSibling(GetBaseGearType().locName());

            return text;
        }

        ITextComponent text = new StringTextComponent(format + "[");

        if (useAffixedName()) {

            if (affixes.hasPrefix()) {

                AffixData prefix = affixes.prefixes.get(0);

                text.appendText(format + "")
                    .appendSibling(prefix.BaseAffix()
                        .locName()
                        .appendText(" "));
            }
            text.appendSibling(GetBaseGearType().locName()
                .applyTextStyle(format));

            if (affixes.hasSuffix()) {
                AffixData suffix = affixes.suffixes.get(0);

                text.appendText(format + " ")
                    .appendSibling(suffix.BaseAffix()
                        .locName())
                    .appendText("");
            }
        } else {
            if (!isUnique()) {
                Words prefix = RareItemAffixNames.getPrefix(this);
                Words suffix = RareItemAffixNames.getSuffix(this);

                if (prefix != null && suffix != null) {
                    text.appendSibling(prefix.locName())
                        .appendText(" ")
                        .appendSibling(suffix.locName());
                }

            } else {
                text.appendSibling(this.uniqueStats.getUnique()
                    .locName());

            }

            text.appendText(" ")
                .appendSibling(GetBaseGearType().locName());
        }

        return text.appendText("]")
            .applyTextStyle(format);

    }

    private boolean useAffixedName() {

        if (isUnique()) {
            return false;
        }

        if (affixes.getNumberOfPrefixes() > 1) {
            return false;
        }
        if (affixes.getNumberOfSuffixes() > 1) {
            return false;
        }
        return true;
    }

    public List<IStatsContainer> GetAllStatContainers(boolean includeBase) {

        List<IStatsContainer> list = new ArrayList<IStatsContainer>();

        if (includeBase) {
            IfNotNullAdd(baseStats, list);
        }

        affixes.getAllAffixes()
            .forEach(x -> IfNotNullAdd(x, list));

        IfNotNullAdd(uniqueStats, list);

        return list;

    }

    public List<ExactStatData> GetAllStats(boolean includebase, boolean includelocaladditions) {

        List<ExactStatData> list = new ArrayList<>();
        GetAllStatContainers(includebase).stream()
            .forEach(x -> {

                List<ExactStatData> stats = x.GetAllStats(this);

                stats.forEach(s -> {

                    if (!x.isBaseStats() && s.shouldBeAddedToLocalStats(this)) {
                        if (includelocaladditions) {
                            list.add(s);
                        }
                    } else {
                        list.add(s);
                    }
                });

            });
        return list;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void BuildTooltip(TooltipContext ctx) {
        GearTooltipUtils.BuildTooltip(this, ctx.stack, ctx.event, ctx.data);
    }

    public List<IRerollable> GetAllRerollable() {
        List<IRerollable> list = new ArrayList<IRerollable>();
        IfNotNullAdd(baseStats, list);

        affixes.getAllAffixes()
            .forEach(x -> IfNotNullAdd(x, list));

        IfNotNullAdd(uniqueStats, list);
        return list;
    }

    private <T> void IfNotNullAdd(T obj, List<T> list) {
        if (obj != null) {
            list.add(obj);
        }
    }

    @Override
    public ItemStack getSalvageResult(float salvageBonus) {

        if (this.isSalvagable) { // problems with issalvagable?
            ItemStack stack = ItemStack.EMPTY;
            int tier = 0;

            int min = 1;
            int max = 2;

            if (rarity == IRarity.Common) {
                max = 1;
            }

            min = tryIncreaseAmount(salvageBonus, min);
            max = tryIncreaseAmount(salvageBonus, max);

            if (is_unique) {
                try {
                    tier = this.uniqueStats.getUnique()
                        .getTier();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (RandomUtils.roll(this.getRarity()
                .salvageLotteryWinChance())) {

                Item item = SlashRegistry.CurrencyItems()
                    .getWrapped()
                    .ofCurrencyUsableOnItemType(ItemType.GEAR)
                    .ofTierRange(tier - 5, tier + 2)
                    .random();

                int tierAmountBonus = (tier / 4);

                int amount = RandomUtils.RandomRange(min + tierAmountBonus, max + tierAmountBonus);
                stack = new ItemStack(item);
                stack.setCount(amount);

                return stack;

            } else {

                int amount = RandomUtils.RandomRange(min, max);

                Item item = ItemUtils.randomMagicEssence();
                stack = new ItemStack(item);
                stack.setCount(amount);

            }

            return stack;
        } else
            return ItemStack.EMPTY;

    }

    @Override
    public DataItemType getDataType() {
        return DataItemType.GEAR;
    }

    @Override
    public boolean isSalvagable(SalvageContext context) {

        return this.isSalvagable;
    }

    @Override
    public void saveToStack(ItemStack stack) {
        Gear.Save(stack, this);
    }

    @Override
    public int getTier() {

        if (this.isUnique()) {
            try {
                return this.uniqueStats.getUnique()
                    .getTier();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return 0;
    }

}
