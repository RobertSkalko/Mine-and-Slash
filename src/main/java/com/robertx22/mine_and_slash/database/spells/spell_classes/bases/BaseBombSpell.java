package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import net.minecraft.util.text.ITextComponent;

public abstract class BaseBombSpell extends BaseProjectileSpell {

    @Override
    public int getBaseValue() {
        return 15;
    }

    @Override
    public int useTimeTicks() {
        return 20;
    }

    @Override
    public ITextComponent GetDescription(SpellItemData data) {
        return CLOC.tooltip("aoe_bomb_spell");

    }

    @Override
    public EffectCalculation ScalingValue() {
        return new EffectCalculation(new ElementalSpellDamage(this.getElement()), this.damageScaling);
    }

    @Override
    public SpellType getSpellType() {
        return SpellType.Aoe_Bomb_Projectile;
    }

    @Override
    public int getManaCost() {
        return 40;
    }

    public float damageScaling = 0.75F;

}
