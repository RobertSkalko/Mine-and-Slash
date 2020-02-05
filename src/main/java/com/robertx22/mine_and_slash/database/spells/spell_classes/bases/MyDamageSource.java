package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.ITextComponent;

public class MyDamageSource extends EntityDamageSource {

    public Elements element = Elements.Physical;
    public int realDamage = 0;

    public MyDamageSource(String damageTypeIn, Entity source, Elements element, int dmg) {
        super(damageTypeIn, source);
        // this.setDamageBypassesArmor();
        this.setDamageIsAbsolute();
        this.element = element;
        realDamage = dmg;

    }

    @Override
    public ITextComponent getDeathMessage(LivingEntity entityLivingBaseIn) {

        return super.getDeathMessage(entityLivingBaseIn);
        /*
        try {
            return new StringTextComponent(entityLivingBaseIn.getDisplayName()
                    .getFormattedText() + " ").appendSibling(Chats.has_died_by_the_hands_of
                    .locName())
                    .appendText(" " + this.damageSourceEntity.getDisplayName()
                            .getFormattedText());
        } catch (Exception e) {
            e.printStackTrace();

            return new StringTextComponent("");
        }

         */

    }

}
