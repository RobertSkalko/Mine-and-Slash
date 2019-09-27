package com.robertx22.mine_and_slash.saveclasses.item_classes;

import com.robertx22.mine_and_slash.config.ClientContainer;
import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.rarities.GearRarity;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.saveclasses.gearitem.*;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IRerollable;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatModsContainer;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipContext;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.rune.RunesData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.DataItemType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
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
        if (data.getLevel() >= this.getLevel()) {
            if (requirements == null || requirements.meetsRequirements(data, this)) {
                return true;
            }
        }
        return false;
    }

    @Store
    public boolean isUnique = false;

    @Store
    public String uniqueGUID = "";

    @Store
    public int Rarity;

    @Store
    public boolean isNotFromMyMod = false;

    @Store
    public String gearTypeName = "";

    @Store
    public RunesData runes;

    public boolean isRuned() {
        return runes != null;
    }

    public GearItemEnum getGearEnum() {

        if (this.isUnique()) {
            return GearItemEnum.UNIQUE;
        }
        if (this.isRuned()) {
            return GearItemEnum.RUNED;
        }

        return GearItemEnum.NORMAL;
    }

    public void setLevel(int lvl) {
        this.level = MathHelper.clamp(lvl, 1, ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL
                .get());
    }

    @Override
    public int getRarityRank() {
        return this.Rarity;
    }

    @Override
    public GearRarity getRarity() {
        return Rarities.Items.get(this.Rarity);
    }

    public boolean changesItemStack() {
        return this.isNotFromMyMod == false;
    }

    public ITextComponent name(ItemStack stack) {

        return stack.getDisplayName();

    }

    @Store
    public int level;

    // Stats
    @Store
    public UniqueStatsData uniqueStats;
    @Store
    public PrimaryStatsData primaryStats;
    @Store
    public SecondaryStatsData secondaryStats;
    @Store
    public SuffixData suffix;
    @Store
    public PrefixData prefix;
    @Store
    public SetData set;
    @Store
    public ChaosStatsData chaosStats;
    @Store
    public InfusionData infusion;
    // Stats

    @Store
    public boolean isSalvagable = true;
    // crafting limits
    @Store
    public int timesLeveledUp = 0;

    @Store
    public int instability = 0;

    //

    // used when upgrading item rarity
    public Item getItem() {

        if (isUnique) {
            return (Item) SlashRegistry.UniqueGears().get(uniqueGUID);
        } else {
            if (gearTypeName.isEmpty()) {
                return Items.AIR;
            } else {
                return SlashRegistry.GearTypes()
                        .get(gearTypeName)
                        .GetItemForRarity(getRarity().Rank());
            }
        }

    }

    public void WriteOverDataThatShouldStay(GearItemData newdata) {

        newdata.timesLeveledUp = this.timesLeveledUp;
        newdata.isSalvagable = this.isSalvagable;

    }

    public GearItemSlot GetBaseGearType() {

        return SlashRegistry.GearTypes().get(gearTypeName);
    }

    public int getPowerLevel() {

        int power = 0;

        for (IStatModsContainer container : this.GetAllStatContainers()) {
            for (IStatModsContainer.LevelAndStats stats : container.GetAllStats(1)) {
                for (StatModData mod : stats.mods) {
                    power += mod.getPercent();
                }
            }
        }

        return power;

    }

    public List<ITextComponent> getMergedStatsTooltip(
            List<IStatModsContainer.LevelAndStats> lvlstats, TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<ITextComponent>();

        for (IStatModsContainer.LevelAndStats part : lvlstats) {
            for (StatModData data : part.mods) {
                list.addAll(data.GetTooltipString(info.withLevel(part.level)));
            }
        }

        return list;

    }

    public ITextComponent GetDisplayName(ItemStack stack) {

        ITextComponent text = new StringTextComponent(this.getRarity()
                .textFormatColor() + "");

        if (this.isRuned()) {
            text.appendSibling(Words.Runed.locName()
                    .appendText(" ")
                    .appendSibling(name(stack)));
        } else {

            if (prefix != null && showAffix()) {
                text.appendSibling(prefix.BaseAffix().locName().appendText(" "));
            }
            text.appendSibling(name(stack));

            if (suffix != null && showAffix()) {
                text.appendText(" ")
                        .appendSibling(suffix.BaseAffix().locName())
                        .appendText(" ");
            }

        }

        return text;

    }

    private boolean showAffix() {

        return !this.isUnique() && ClientContainer.INSTANCE.SHOW_AFFIXED_NAME.get();
    }

    public List<IStatModsContainer> GetAllStatContainers() {

        List<IStatModsContainer> list = new ArrayList<IStatModsContainer>();

        IfNotNullAdd(secondaryStats, list);
        IfNotNullAdd(primaryStats, list);
        IfNotNullAdd(prefix, list);
        IfNotNullAdd(suffix, list);
        IfNotNullAdd(chaosStats, list);
        IfNotNullAdd(uniqueStats, list);
        IfNotNullAdd(infusion, list);
        IfNotNullAdd(runes, list);

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
        GearTooltipUtils.BuildTooltip(this, ctx.stack, ctx.event, ctx.data.getUnit(), ctx.data);
    }

    public List<IRerollable> GetAllRerollable() {
        List<IRerollable> list = new ArrayList<IRerollable>();
        IfNotNullAdd(secondaryStats, list);
        IfNotNullAdd(primaryStats, list);
        IfNotNullAdd(prefix, list);
        IfNotNullAdd(suffix, list);
        IfNotNullAdd(chaosStats, list);
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

            min = tryIncreaseAmount(salvageBonus, min);
            max = tryIncreaseAmount(salvageBonus, max);

            if (isUnique) {
                try {
                    tier = this.uniqueStats.getUniqueItem().Tier();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (isUnique || RandomUtils.roll(this.getRarity().specialItemChance())) {

                Item item = SlashRegistry.CurrencyItems()
                        .getWrapped()
                        .ofTierRange(tier - 5, tier + 2)
                        .random();

                int tierAmountBonus = (tier / 4);

                int amount = RandomUtils.RandomRange(min + tierAmountBonus, max + tierAmountBonus);
                stack = new ItemStack(item);
                stack.setCount(amount);

                return stack;

            } else {

                int amount = RandomUtils.RandomRange(min, max);

                ItemOre ore = (ItemOre) ItemOre.ItemOres.get(Rarity);
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

        return this.uniqueGUID;
    }

    @Override
    public int Tier() {

        if (this.isUnique()) {
            try {
                return this.uniqueStats.getUniqueItem().Tier();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return 0;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public String getSpecificType() {
        return this.gearTypeName;
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
