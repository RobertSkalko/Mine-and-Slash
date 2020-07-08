package com.robertx22.mine_and_slash.saveclasses.item_classes;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.BaseStatsData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.PrefixData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.SuffixData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.UniqueStatsData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.*;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.ItemType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.DataItemType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@Storable
public class GearItemData implements ICommonDataItem<GearRarity>, IInstability {

    @Store
    public StatRequirementsData requirements = null;

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
    public UniqueStatsData uniqueStats;
    @Store
    public BaseStatsData primaryStats;
    @Store
    public List<SuffixData> suffixes;
    @Store
    public List<PrefixData> prefixes;

    // Stats

    @Store
    public boolean isSalvagable = true;

    // crafting limits
    @Store
    public int instability = 0;

    //

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
                    .getItemForRarity(getRarity().Rank());
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

    public List<ITextComponent> getMergedStatsTooltip(List<IStatModsContainer.LevelAndStats> lvlstats,
                                                      TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<ITextComponent>();
// todo remove this
        for (IStatModsContainer.LevelAndStats part : lvlstats) {
            for (StatModData data : part.mods) {
                list.addAll(data.GetTooltipString(info.withLevel(part.level)));
            }
        }

        return list;

    }

    public ITextComponent getOnGroundDisplayName() {
        return new SText(getRarity().textFormatting() + "").appendSibling(GetBaseGearType().locName());
    }

    public ITextComponent GetDisplayName(ItemStack stack) {

        if (!isIdentified()) {
            ITextComponent text = new SText(getRarity().textFormatting() + "")
                .appendSibling(Words.Unidentified.locName())
                .appendText(" ")
                .appendSibling(getRarity().locName())
                .appendText(" ")
                .appendSibling(GetBaseGearType().locName());

            return text;
        }

        ITextComponent text = new StringTextComponent(this.getRarity()
            .textFormatting() + "");

        if (addNormalAffixedPrefixesAndSuffixes()) {

            if (prefixes.size() > 0) {

                PrefixData prefix = prefixes.get(0);

                text.appendSibling(prefix.BaseAffix()
                    .locName()
                    .appendText(" "));
            }
            text.appendSibling(name(stack));

            if (prefixes.size() > 0) {
                SuffixData suffix = suffixes.get(0);

                text.appendText(" ")
                    .appendSibling(suffix.BaseAffix()
                        .locName())
                    .appendText(" ");
            }
        } else {
            if (!isUnique()) {
                text.appendText("Test Rare Name")
                    .appendText(" ");

                text.appendSibling(name(stack));
            }
        }

        return text;

    }

    private boolean addNormalAffixedPrefixesAndSuffixes() {

        return !this.isUnique() && prefixes.size() < 2 && suffixes.size() < 2;
    }

    public List<IStatModsContainer> GetAllStatContainers() {

        List<IStatModsContainer> list = new ArrayList<IStatModsContainer>();

        IfNotNullAdd(primaryStats, list);

        for (PrefixData d : prefixes) {
            IfNotNullAdd(d, list);
        }
        for (SuffixData d : suffixes) {
            IfNotNullAdd(d, list);
        }

        IfNotNullAdd(uniqueStats, list);

        return list;

    }

    public List<IStatModsContainer.LevelAndStats> GetAllStats(int level) {

        List<IStatModsContainer.LevelAndStats> datas = new ArrayList<IStatModsContainer.LevelAndStats>();

        for (IStatModsContainer con : GetAllStatContainers()) {
            datas.addAll(con.GetAllStats(this.level));
        }

        return datas;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void BuildTooltip(TooltipContext ctx) {
        GearTooltipUtils.BuildTooltip(this, ctx.stack, ctx.event, ctx.data);
    }

    public List<IRerollable> GetAllRerollable() {
        List<IRerollable> list = new ArrayList<IRerollable>();
        IfNotNullAdd(primaryStats, list);

        for (PrefixData d : prefixes) {
            IfNotNullAdd(d, list);
        }
        for (SuffixData d : suffixes) {
            IfNotNullAdd(d, list);
        }
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

                ItemOre ore = (ItemOre) ItemOre.ItemOres.get(getSalvagedOreRarity(getRarityRank()));
                stack = new ItemStack(ore);
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

        if (context == SalvageContext.AUTO_SALVAGE_BAG) {
            return this.isUnique() == false && this.isSalvagable;

        }
        return this.isSalvagable;
    }

    @Override
    public void saveToStack(ItemStack stack) {
        Gear.Save(stack, this);
    }

    @Override
    public String getUniqueGUID() {

        try {
            return this.uniqueStats.uniqueGUID;
        } catch (Exception e) {

        }

        return this.unique_id;
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

    @Override
    public String getSpecificType() {
        return this.gear_type;
    }

    @Override
    public int getInstability() {
        return this.instability;
    }

    @Override
    public void increaseInstability(int amount) {
        this.instability += amount;
    }

}
