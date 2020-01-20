package com.robertx22.mine_and_slash.database.spells.spell_classes.projectile;

import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityElementalBolt;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.DamageData;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellEffectDamage;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public abstract class BaseSpellProjectile extends BaseBolt {

    @Override
    public int useTimeTicks() {
        return 10;
    }

    public BaseSpellProjectile() {
        super();
    }

    @Override
    public SpellType getSpellType() {
        return SpellType.Single_Target_Projectile;
    }

    @Override
    public boolean cast(PlayerEntity caster, int ticksInUse, SpellItemData data) {

        World world = caster.world;

        if (!world.isRemote) {

            EntityElementalBolt projectile = this.projectile(world);
            projectile.SpawnAndShoot(new SpellEffectDamage(this.getElement()), new DamageData(caster, data), caster);

        }

        SoundUtils.playSoundAtPlayer(caster, SoundEvents.ENTITY_SNOWBALL_THROW, 1, 1);

        return true;
    }

}