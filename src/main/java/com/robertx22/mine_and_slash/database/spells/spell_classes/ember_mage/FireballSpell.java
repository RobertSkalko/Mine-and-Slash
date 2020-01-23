package com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.proj.FireballEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseProjectileSpell;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FireballSpell extends BaseProjectileSpell {

    public FireballSpell() {
        super();
    }

    @Override
    public Elements getElement() {
        return Elements.Fire;
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.EMBER_MAGE;
    }

    @Override
    public int getCooldownInSeconds() {
        return 0;
    }

    @Override
    public String GUID() {
        return "fireball";
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.one(dmgStat(), 1F, 10);
    }

    @Override
    public boolean cast(PlayerEntity caster, int ticksInUse) {

        World world = caster.world;
        Vec3d pos = caster.getPositionVector();
        FireballEntity en = SpellUtils.getSpellEntity(new FireballEntity(world), this, caster);
        SpellUtils.setupProjectileForCasting(en, caster, 2);
        caster.world.addEntity(en);

        caster.world.playMovingSound(null, en, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);

        return true;
    }
}