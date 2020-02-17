package com.robertx22.mine_and_slash.items.gearitems.offhands;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGearItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NormalShield extends ShieldItem implements IEffectItem, IAutoLocName, IGearItem {

    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ResourceLocation resource = new ResourceLocation("");

    public NormalShield(int rarity, Properties prop) {

        super(prop);
        this.rarity = rarity;
        resource = getResource(rarity);

    }

    public static ResourceLocation getResource(int rarity) {
        return new ResourceLocation(Ref.MODID, "shield/normal_shield" + rarity);
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Gear_Items;
    }

    public int rarity = 0;

    @Override
    public String locNameForLangFile() {
        Rarity rar = Rarities.Gears.get(rarity);
        return rar.textFormatColor() + "Shield";
    }

    @Override
    public String locNameLangFileGUID() {
        return getFormatedForLangFile(this.getRegistryName()
            .toString());
    }

    @Override
    public String GUID() {
        return "";
    }

    @Override
    public boolean isShield(ItemStack stack, @Nullable LivingEntity entity) {
        return true;

    }

    @Override
    public List<ITextComponent> getEffectTooltip(boolean moreInfo) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(
            color() + "" + TextFormatting.BOLD + "[Active]: " + TextFormatting.RESET + color() + "Block"));
        if (moreInfo) {
            list.add(new StringTextComponent(color() + "DMG Reduced Based on Block Strength"));
        }
        return list;
    }

}