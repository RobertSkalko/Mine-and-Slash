package com.robertx22.mine_and_slash.items.gearitems.weapons;

import com.google.common.collect.Multimap;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.items.gearitems.bases.BaseArmorItem;
import com.robertx22.mine_and_slash.items.gearitems.bases.itemtiers.RarityItemTier;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGearItem;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ItemUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

import java.util.HashMap;

public class ItemSword extends SwordItem implements IAutoLocName, IGearItem {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ItemSword(int rar) {
        super(
            new RarityItemTier(rar), 5 + (rar + 1), -2.4F, (ItemUtils.getDefaultGearProperties()
                .defaultMaxDamage(BaseArmorItem.GetMat(BaseArmorItem.Type.PLATE, rar)
                    .getDurability())));
        this.rarity = rar;
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Gear_Items;
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName()
            .toString();
    }

    @Override
    public String GUID() {
        return "";
    }

    @Override
    public String locNameForLangFile() {
        Rarity rar = Rarities.Gears.get(rarity);
        return rar.textFormatColor() + "Sword";
    }

    public int rarity = 0;

    /**
     * Check whether this Item can harvest the given Block
     */
    public boolean canHarvestBlock(BlockState blockIn) {
        return blockIn.getBlock() == Blocks.COBWEB;
    }

    public float getDestroySpeed(ItemStack stack, BlockState state) {
        Block block = state.getBlock();

        if (block == Blocks.COBWEB) {
            return 15.0F;
        } else {
            Material material = state.getMaterial();
            return material != Material.PLANTS && material != Material.CORAL && material != Material.LEAVES && material != Material.GOURD ? 1.0F : 1.5F;
        }
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot) {

        float attackSpeed = -1.5F;

        Multimap<String, AttributeModifier> map = super.getAttributeModifiers(slot);
        if (slot == EquipmentSlotType.MAINHAND) {
            map.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
                new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double) attackSpeed,
                    AttributeModifier.Operation.ADDITION
                )
            );
            map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
                new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", 5 + (this.rarity + 1),
                    AttributeModifier.Operation.ADDITION
                )
            );

        }

        return map;
    }

}
