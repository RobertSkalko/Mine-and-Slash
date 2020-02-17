package com.robertx22.mine_and_slash.database.currency;

import com.robertx22.mine_and_slash.database.currency.loc_reqs.BaseLocRequirement;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.GearEnumLocReq;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.SimpleGearLocReq;
import com.robertx22.mine_and_slash.database.currency.loc_reqs.item_types.GearReq;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.items.profession.alchemy.bases.IHasRecipe;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.professions.recipe.BaseRecipe;
import com.robertx22.mine_and_slash.professions.recipe.SimpleRecipe;
import com.robertx22.mine_and_slash.saveclasses.gearitem.ChaosStatsData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.interfaces.IRenamed;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.List;

public class ItemChaosOrb extends CurrencyItem implements ICurrencyItemEffect, IRenamed, IHasRecipe {
    @Override
    public String GUID() {
        return "currency/chaos_orb";
    }

    public static final String ID = Ref.MODID + ":currency/chaos_orb";

    @Override
    public List<String> oldNames() {
        return Arrays.asList(Ref.MODID + ":chaos_orb");
    }

    public ItemChaosOrb() {

        super(ID);

    }

    @Override
    public ItemStack ModifyItem(ItemStack stack, ItemStack Currency) {

        GearItemData gear = Gear.Load(stack);
        gear.chaosStats = new ChaosStatsData();
        gear.chaosStats.RerollFully(gear);
        Gear.Save(stack, gear);

        return stack;
    }

    @Override
    public List<BaseLocRequirement> requirements() {
        return Arrays.asList(GearReq.INSTANCE, GearEnumLocReq.CAN_CHAOS_STATS, SimpleGearLocReq.NO_CHAOS_STATS);
    }

    @Override
    public int Tier() {
        return 3;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Do not gamble what you are not willing to lose.");
    }

    @Override
    public String locNameForLangFile() {
        return nameColor + "Chaos Orb";
    }

    @Override
    public String locDescForLangFile() {
        return "Permanently adds a Chaos stat";
    }

    @Override
    public int instabilityAddAmount() {
        return -25;
    }

    @Override
    public boolean activatesBreakRoll() {
        return false;
    }

    @Override
    public BaseRecipe getRecipe() {
        return SimpleRecipe.Builder.create(GUID(), Professions.TINKERERING)
                .addMaterial(ItemOre.ItemOres.get(getRarityRank()), 3)
                .addMaterial(new ItemOrbOfTransmutation().getFromForgeRegistry(), 2)
                .addMaterial(Items.GOLD_INGOT, 1)
                .addMaterial(Items.REDSTONE, 5)
                .buildMaterials()
                .setOutput(this)
                .levelReq(10)
                .expGained(5)
                .build();

    }

}
