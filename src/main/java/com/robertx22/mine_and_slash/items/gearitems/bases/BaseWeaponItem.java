package com.robertx22.mine_and_slash.items.gearitems.bases;

import com.google.common.collect.Sets;
import com.robertx22.mine_and_slash.items.gearitems.bases.itemtiers.RarityItemTier;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGearItem;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;

import java.util.Set;

public abstract class BaseWeaponItem extends TieredItem implements IWeapon, IAutoLocName, IGearItem {

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet();

    public BaseWeaponItem(int rar) {

        super(new RarityItemTier(rar), new Properties().defaultMaxDamage(1000));
        this.rarity = rar;
    }

    public int rarity = 0;

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Gear_Items;
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }

    @Override
    public String GUID() {
        return "";
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target,
                             LivingEntity attacker) {
        stack.damageItem(1, attacker, (entity) -> {
            entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
        });
        return true;
    }

}
