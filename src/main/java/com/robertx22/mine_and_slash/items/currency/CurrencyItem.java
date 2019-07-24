package com.robertx22.mine_and_slash.items.currency;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.items.ItemDefault;
import com.robertx22.mine_and_slash.uncommon.datasaving.ItemType;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocDesc;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocMultiLore;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ITiered;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Tooltip;
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
import java.util.ArrayList;
import java.util.List;

public abstract class CurrencyItem extends Item implements IGUID, ICurrencyItemEffect, IWeighted, ITiered, IAutoLocMultiLore, IAutoLocDesc, IAutoLocName {

    public static List<CurrencyItem> ITEMS = new ArrayList<>();

    public ItemType itemTypesUsableOn = ItemType.GEAR;

    public static TextFormatting nameColor = TextFormatting.RED;

    public abstract String GUID();

    public CurrencyItem(String name) {
        super(new ItemDefault().maxStackSize(64));

        RegisterItemUtils.RegisterItemName(this, name);

        ITEMS.add(this);

    }

    @Override
    public boolean canItemBeModified(ItemStack stack, ItemStack Currency) {

        if (this.itemTypesUsableOn.isType(stack)) {
            return this.canItemBeModifiedPROTECTED(stack, Currency);
        }
        return false;
    }

    @Override
    public AutoLocGroup locDescGroup() {
        return AutoLocGroup.Currency_Items;
    }

    @Override
    public String locDescLangFileGUID() {
        return this.getRegistryName().toString() + ".desc";
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
        return this.getRegistryName().toString();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

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
            tooltip.add(Styles.YELLOWCOMP().appendSibling(auto.locDesc()));
        }

        tooltip.add(ItemType.getTooltipString(this.itemTypesUsableOn));

        Tooltip.add("", tooltip);

        tooltip.add(TooltipUtils.tier(this.Tier()));
        tooltip.add(TooltipUtils.rarity(Rarities.Items.get(this.rarity())));

        Tooltip.add("", tooltip);

        Tooltip.add(Styles.BLUECOMP()
                .appendSibling(Words.Item_modifiable_in_station.locName()), tooltip);

    }

    public abstract int rarity();

    @Override
    public int Weight() {

        if (rarity() == 0) {
            return ModConfig.INSTANCE.RarityWeightConfig.CURRENCY.COMMON_WEIGHT.get();
        } else if (rarity() == 1) {
            return ModConfig.INSTANCE.RarityWeightConfig.CURRENCY.UNCOMMON_WEIGHT.get();
        } else if (rarity() == 2) {
            return ModConfig.INSTANCE.RarityWeightConfig.CURRENCY.RARE_WEIGHT.get();
        } else if (rarity() == 3) {
            return ModConfig.INSTANCE.RarityWeightConfig.CURRENCY.EPIC_WEIGHT.get();
        } else if (rarity() == 4) {
            return ModConfig.INSTANCE.RarityWeightConfig.CURRENCY.LEGENDARY_WEIGHT.get();
        } else if (rarity() == 5) {
            return ModConfig.INSTANCE.RarityWeightConfig.CURRENCY.MYTHICAL_WEIGHT.get();
        }
        return 0;

    }

}
