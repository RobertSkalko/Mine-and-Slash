package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityElementalBolt;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public abstract class BaseBolt extends BaseSpell {

    @Override
    public EffectCalculation ScalingValue() {
        return new EffectCalculation(new ElementalSpellDamage(this.getElement()), 1.5F);
    }

    @Override
    public boolean cast(PlayerEntity caster, int ticksInUse, SpellItemData data) {

        World world = caster.world;

        if (!world.isRemote) {
            EntityElementalBolt projectile = this.projectile(world);
            projectile.SpawnAndShoot(new SpellEffectDamage(this.getElement()), new DamageData(caster, data), caster);

        }

        SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_SPLASH_POTION_THROW, 1, 1);

        return true;
    }

    @Override
    public int getManaCost() {
        return 25;
    }

    @Override
    public int getBaseValue() {
        return 25;
    }

    @Override
    public ITextComponent GetDescription(SpellItemData data) {
        return CLOC.tooltip("single_target_spell_bolt");

    }

    public abstract EntityElementalBolt projectile(World world);
}
