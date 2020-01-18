package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.entity.LivingEntity;

public class SpellEffectDamage extends BaseSpellEffect {

    public SpellEffectDamage() {
        super();

    }

    public boolean knockback = true;

    public SpellEffectDamage(Elements el) {
        super();

        this.element = el;
    }

    Elements element = Elements.Physical;

    @Override
    public void Activate(DamageData dmgdata, LivingEntity target) {

        int num = dmgdata.spellItemData.getDamage(dmgdata.casterData.getUnit());

        SpellDamageEffect dmg = new SpellDamageEffect(dmgdata.caster, target, num, dmgdata.casterData, Load
                .Unit(target), dmgdata.spellItemData.getSpell());

        if (knockback == false) {
            dmg.removeKnockback();
        }

        dmg.element = this.element;
        dmg.Activate();

    }

    @Override
    public String Name() {
        return "Spell Effect Damage";
    }

}