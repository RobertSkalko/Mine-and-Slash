package com.robertx22.mine_and_slash.database.runes.base;

import com.robertx22.mine_and_slash.data_generation.models.IAutoModel;
import com.robertx22.mine_and_slash.data_generation.models.ItemModelManager;
import com.robertx22.mine_and_slash.database.currency.base.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.*;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.RuneItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.Rune;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class RuneItem extends Item implements IAutoLocName, ICurrencyItemEffect, IAutoModel {

    public int rarity;
    public String rune;

    public RuneItem(BaseRune rune, int rarity) {
        super(new Properties().maxStackSize(1)
            .defaultMaxDamage(0));
        this.rarity = rarity;
        this.rune = rune.GUID();

    }

    @Override
    public void generateModel(ItemModelManager manager) {
        manager.generated(this);
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Runes;
    }

    @Override
    public String locNameLangFileGUID() {
        return getRegistryName()
            .toString();
    }

    @Override
    public String locNameForLangFile() {
        Rarity rar = Rarities.Runes.get(rarity);
        return rar.textFormatColor() + rune
            .toUpperCase(Locale.ROOT) + " - " + rar.locNameForLangFile() + " Rune";

    }

    @Override
    public String GUID() {
        return rune;
    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack currency) {
        GearItemData gear = Gear.Load(stack);

        RuneItemData rune = Rune.Load(currency);

        if (gear != null && rune != null) {
            gear.runes.insert(rune, gear);
            Gear.Save(stack, gear);
        }
        return stack;

    }

    @Override
    public boolean canItemBeModified(LocReqContext context) {

        for (BaseLocRequirement req : requirements()) {
            if (req.isNotAllowed(context)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(
            RuneEmptySlotReq.INSTANCE, OnlyOneUniqueRuneReq.getInstance(), RuneNoDuplicateReq.INSTANCE,
            RuneLvlReq.INSTANCE
        );
    }
}
