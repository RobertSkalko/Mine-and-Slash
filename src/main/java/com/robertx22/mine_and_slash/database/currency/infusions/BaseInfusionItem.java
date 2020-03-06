package com.robertx22.mine_and_slash.database.currency.infusions;

import com.robertx22.mine_and_slash.database.currency.CrystalOfLegendItem;
import com.robertx22.mine_and_slash.database.currency.OrbOfTransmutationItem;
import com.robertx22.mine_and_slash.database.currency.base.CurrencyItem;
import com.robertx22.mine_and_slash.database.currency.base.ICurrencyItemEffect;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.GearEnumLocReq;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.IHasRecipe;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.professions.recipe.SimpleRecipe;
import com.robertx22.mine_and_slash.saveclasses.gearitem.InfusionData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseInfusionItem extends CurrencyItem implements ICurrencyItemEffect, IHasRecipe {

    public BaseInfusionItem(String name) {
        super(name);

    }

    @Override
    public int instabilityAddAmount() {
        return 15;
    }

    @Override
    public boolean activatesBreakRoll() {
        return false;
    }

    public void createInfusion(GearItemData gear) {

        gear.infusion = new InfusionData();

        List<StatMod> possible = new ArrayList();

        if (gear.GetBaseGearType()
            .slotType()
            .equals(GearItemSlot.GearSlotType.Armor)) {
            possible = this.armorInfusions();
        } else if (gear.GetBaseGearType()
            .slotType()
            .equals(GearItemSlot.GearSlotType.Weapon)) {
            possible = this.weaponInfusions();
        } else if (gear.GetBaseGearType()
            .slotType()
            .equals(GearItemSlot.GearSlotType.Jewerly)) {
            possible = this.jewerlyInfusions();
        } else {
            possible = this.jewerlyInfusions();
        }

        StatMod random = RandomUtils.weightedRandom(possible);

        gear.infusion.Mods = new ArrayList();

        gear.infusion.Mods.add(StatModData.Load(random, 0));

    }

    public abstract List<StatMod> weaponInfusions();

    public abstract List<StatMod> armorInfusions();

    public abstract List<StatMod> jewerlyInfusions();

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {
        GearItemData gear = Gear.Load(stack);

        this.createInfusion(gear);

        Gear.Save(stack, gear);
        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearEnumLocReq.INFUSIONS);
    }

    @Override
    public int Tier() {
        return 5;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Legendary;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Luck is Etheral and yet affects everything.");
    }

    @Override
    public BaseRecipe getRecipe() {
        return SimpleRecipe.Builder.create(GUID(), Professions.TINKERERING)
            .addMaterial(ItemOre.ItemOres.get(getRarityRank()), 10)
            .addMaterial(new CrystalOfLegendItem().getFromForgeRegistry(), 5)
            .addMaterial(new OrbOfTransmutationItem().getFromForgeRegistry(), 5)
            .addMaterial(Items.IRON_INGOT, 2)
            .buildMaterials()
            .setOutput(this)
            .levelReq(25)
            .expGained(15)
            .build();

    }

}