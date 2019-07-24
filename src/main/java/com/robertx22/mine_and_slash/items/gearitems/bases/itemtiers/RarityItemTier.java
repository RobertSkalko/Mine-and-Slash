package com.robertx22.mine_and_slash.items.gearitems.bases.itemtiers;

import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

public class RarityItemTier implements IItemTier {

    public RarityItemTier(int rar) {
        this.rar = Rarities.Items.get(rar);
    }

    ItemRarity rar;

    @Override
    public int getMaxUses() {
        return (int) (net.minecraft.item.ItemTier.IRON.getMaxUses() + (net.minecraft.item.ItemTier.WOOD
                .getMaxUses() * rar.itemTierPower()));
    }

    @Override
    public float getEfficiency() {
        return net.minecraft.item.ItemTier.IRON.getEfficiency() + net.minecraft.item.ItemTier.WOOD
                .getEfficiency() * rar.itemTierPower();
    }

    @Override
    public float getAttackDamage() {
        return net.minecraft.item.ItemTier.IRON.getAttackDamage() + net.minecraft.item.ItemTier.WOOD
                .getAttackDamage() * rar.itemTierPower();
    }

    @Override
    public int getHarvestLevel() {
        return (int) (net.minecraft.item.ItemTier.IRON.getHarvestLevel() + 1 * rar.itemTierPower());
    }

    @Override
    public int getEnchantability() {
        return (int) (net.minecraft.item.ItemTier.IRON.getEnchantability() + (net.minecraft.item.ItemTier.IRON
                .getEnchantability() * rar.itemTierPower()));
    }

    @Override
    public Ingredient getRepairMaterial() {
        return Ingredient.fromItems(Items.STRUCTURE_BLOCK);
    }
}
