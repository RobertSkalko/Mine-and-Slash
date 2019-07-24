package com.robertx22.mine_and_slash.items.runes.base;

import com.robertx22.mine_and_slash.database.ElementalStatMod;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.*;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.items.currency.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.RuneItemData;
import com.robertx22.mine_and_slash.saveclasses.rune.RunesData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.Rune;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseRuneItem extends Item implements IWeighted, ICurrencyItemEffect, IAutoLocName, ISlashRegistryEntry<BaseRuneItem> {

    public int rarity;

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.RUNE;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Uncommon;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Runes.get(getRarityRank());
    }

    @Override
    public int Tier() {
        return 0;
    }

    @Override
    public String locNameForLangFile() {

        Rarity rar = Rarities.Runes.get(rarity);

        return rar.textFormatColor() + this.name()
                .toUpperCase() + " - " + rar.locNameForLangFile() + " Rune";

    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Runes;
    }

    @Override
    public String GUID() {
        return name();
    }

    @Override
    public int Weight() {
        return 1000;
    }

    public abstract BaseRuneItem byRarity(int rar);

    public BaseRuneItem(int rarity) {

        super(new Properties().maxStackSize(1).defaultMaxDamage(0));
        this.rarity = rarity;

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
    public boolean canItemBeModifiedPROTECTED(ItemStack stack, ItemStack currency) {

        GearItemData gear = Gear.Load(stack);

        if (gear != null && gear.isRuned()) {
            if (gear.runes == null) {
                gear.runes = new RunesData();
                Gear.Save(stack, gear);
            }

            RuneItemData rune = Rune.Load(currency);

            if (rune != null) {
                return gear.runes.canFit(gear, rune);
            }
        }

        return false;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

    }

    public abstract String name();

    public abstract List<StatMod> weaponStat();

    public abstract List<StatMod> armorStat();

    public abstract List<StatMod> jewerlyStat();

    private List<StatMod> allElements(ElementalStatMod mod) {

        return mod.generateAllPossibleStatVariations()
                .stream()
                .filter(x -> ((ElementalStatMod) x).element.isSingleElement)
                .collect(Collectors.toList());
    }

    public List<StatMod> spellDamageFlats() {
        return allElements(new ElementalSpellDamageFlat(Elements.Physical));
    }

    public List<StatMod> spellDamageMultis() {
        return allElements(new ElementalSpellDamageMulti(Elements.Physical));
    }

    public List<StatMod> resistFlats() {
        return allElements(new ElementalResistFlat(Elements.Physical));
    }

    public List<StatMod> peneFlats() {
        return allElements(new ElementalPeneFlat(Elements.Physical));
    }

    public List<StatMod> penePercents() {
        return allElements(new ElementalPenePercent(Elements.Physical));
    }

    public List<StatMod> spellDamagePercents() {
        return allElements(new ElementalSpellDamagePercent(Elements.Physical));
    }

}
