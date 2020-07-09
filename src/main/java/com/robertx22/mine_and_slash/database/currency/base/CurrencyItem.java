package com.robertx22.mine_and_slash.database.currency.base;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.data_generation.models.IAutoModel;
import com.robertx22.mine_and_slash.data_generation.models.ItemModelManager;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.LocReqContext;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.items.ItemDefault;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.datasaving.ItemType;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocDesc;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocMultiLore;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ISalvagable;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ITiered;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ItemUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public abstract class CurrencyItem extends Item implements IAddsInstability, ISlashRegistryEntry<CurrencyItem>, ISalvagable,
    ICurrencyItemEffect, IWeighted, ITiered, IAutoLocMultiLore, IAutoLocDesc, IAutoLocName, IAutoModel {

    public ItemType itemTypesUsableOn = ItemType.GEAR;

    public static TextFormatting nameColor = TextFormatting.RED;

    public abstract String GUID();

    public CurrencyItem(String name) {
        super(new ItemDefault().maxStackSize(64));
    }

    @Override
    public void generateModel(ItemModelManager manager) {
        manager.generated(this);
    }

    @Override
    public final boolean canItemBeModified(LocReqContext context) {

        if (this.itemTypesUsableOn.isType(context.stack) == false) {
            return false;
        }

        for (BaseLocRequirement req : requirements()) {
            if (req.isNotAllowed(context)) {
                return false;
            }

        }
        return true;
    }

    @Override
    public AutoLocGroup locDescGroup() {
        return AutoLocGroup.Currency_Items;
    }

    @Override
    public String locDescLangFileGUID() {
        return this.getRegistryName()
            .toString() + ".desc";
    }

    @Override
    public AutoLocGroup locLoresGroup() {
        return AutoLocGroup.Currency_Items;
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Currency_Items;
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName()
            .toString();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
                               ITooltipFlag flagIn) {

        if (this instanceof IAutoLocMultiLore) {
            IAutoLocMultiLore auto = (IAutoLocMultiLore) this;
            for (ITextComponent comp : auto.getComponents()) {
                tooltip.add(Styles.GREENCOMP()
                    .appendText("'")
                    .appendSibling(comp)
                    .appendText("'"));
            }
        }

        if (this instanceof IAutoLocDesc) {
            IAutoLocDesc auto = (IAutoLocDesc) this;
            tooltip.add(Styles.YELLOWCOMP()
                .appendSibling(auto.locDesc()));
        }

        tooltip.add(ItemType.getTooltipString(this.itemTypesUsableOn));

        TooltipUtils.addEmpty(tooltip);

        tooltip.add(TooltipUtils.tier(this.getTier()));
        tooltip.add(TooltipUtils.rarity(getRarity()));
        TooltipUtils.addEmpty(tooltip);

        if (this instanceof IAddsInstability && ModConfig.INSTANCE.Server.ENABLE_CURRENCY_ITEMS_INSTABILITY_SYSTEM.get()) {
            tooltip.add(Styles.REDCOMP()
                .appendText("Adds " + this.instabilityAddAmount() + " Instability"));
        }
        TooltipUtils.addEmpty(tooltip);

        tooltip.add(Styles.BLUECOMP()
            .appendSibling(Words.Item_modifiable_in_station.locName()));

        TooltipUtils.addEmpty(tooltip);

    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.CURRENCY_ITEMS;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Uncommon;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Mobs.get(getRarityRank());
    }

    @Override
    public int Weight() {

        if (getRarityRank() == 0) {
            return ModConfig.INSTANCE.RarityWeightConfig.CURRENCY.COMMON_WEIGHT.get();
        } else if (getRarityRank() == 1) {
            return ModConfig.INSTANCE.RarityWeightConfig.CURRENCY.UNCOMMON_WEIGHT.get();
        } else if (getRarityRank() == 2) {
            return ModConfig.INSTANCE.RarityWeightConfig.CURRENCY.RARE_WEIGHT.get();
        } else if (getRarityRank() == 3) {
            return ModConfig.INSTANCE.RarityWeightConfig.CURRENCY.EPIC_WEIGHT.get();
        } else if (getRarityRank() == 4) {
            return ModConfig.INSTANCE.RarityWeightConfig.CURRENCY.LEGENDARY_WEIGHT.get();
        }

        return 0;
    }

    @Override
    public ItemStack getSalvageResult(float salvageBonus) {

        int min = 1;
        int max = 2;

        min = tryIncreaseAmount(salvageBonus, min);
        max = tryIncreaseAmount(salvageBonus, max);

        int amount = RandomUtils.RandomRange(min, max);

        Item item = ItemUtils.randomMagicEssence();
        ItemStack stack = new ItemStack(item);
        stack.setCount(amount);

        return stack;
    }

    @Override
    public boolean isSalvagable(SalvageContext context) {
        return true;
    }

}
