package com.robertx22.mine_and_slash.items.consumables.bases;

import com.robertx22.mine_and_slash.potion_effects.SpellPotionBase;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public abstract class BaseGiveBuffItem extends BaseConsumabletem {

    @Override
    public ITextComponent tooltip() {
        return Styles.GREENCOMP()
                .appendSibling(new StringTextComponent("Gives ").appendSibling(potion().locName())
                        .appendText(" Buff"));
    }

    public abstract SpellPotionBase potion();

    public abstract int seconds();

    @Override
    public void onFinish(ItemStack stack, World world, LivingEntity player,
                         EntityCap.UnitData unitdata) {

        player.addPotionEffect(new EffectInstance(potion(), seconds() * 20));

    }

}
