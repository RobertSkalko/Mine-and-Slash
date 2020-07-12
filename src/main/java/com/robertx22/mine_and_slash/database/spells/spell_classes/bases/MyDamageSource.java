package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

public class MyDamageSource extends EntityDamageSource {

    public Elements element = Elements.Physical;
    public int realDamage = 0;

    @Nullable
    DamageSource source;

    public MyDamageSource(DamageSource s, String damageTypeIn, Entity source, Elements element, int dmg) {
        super(damageTypeIn, source);
        // this.setDamageBypassesArmor();
        this.element = element;
        realDamage = dmg;

        this.source = s;

    }

    @Override
    public ITextComponent getDeathMessage(LivingEntity en) {

        String type = this.damageType;

        if (source != null) {
            type = source.damageType;
        }

        ItemStack lvt_2_1_ = this.damageSourceEntity instanceof LivingEntity ? ((LivingEntity) this.damageSourceEntity).getHeldItemMainhand() : ItemStack.EMPTY;
        String lvt_3_1_ = "death.attack." + this.damageType;
        return !lvt_2_1_.isEmpty() && lvt_2_1_.hasDisplayName() ? new TranslationTextComponent(lvt_3_1_ + ".item", new Object[]{
            en.getDisplayName(),
            this.damageSourceEntity.getDisplayName(),
            lvt_2_1_.getTextComponent()
        }) : new TranslationTextComponent(lvt_3_1_, new Object[]{
            en.getDisplayName(),
            this.damageSourceEntity.getDisplayName()
        });

    }

}
