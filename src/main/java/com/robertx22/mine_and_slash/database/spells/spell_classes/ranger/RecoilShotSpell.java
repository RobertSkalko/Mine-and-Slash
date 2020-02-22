package com.robertx22.mine_and_slash.database.spells.spell_classes.ranger;

import com.robertx22.mine_and_slash.database.spells.entities.proj.RangerArrowEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseProjectileSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RecoilShotSpell extends BaseProjectileSpell {

    private RecoilShotSpell() {
        this.castRequirements.add(BaseSpell.REQUIRE_SHOOTABLE);
    }

    public static RecoilShotSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.RANGER;
    }

    @Override
    public int getCooldownInSeconds() {
        return 15;
    }

    @Override
    public BaseSpell.SpellType getSpellType() {
        return SpellType.Single_Target_Projectile;
    }

    @Override
    public float getShootSpeed() {
        return 1.5F;
    }

    @Override
    public AbstractArrowEntity newEntity(World world) {
        return new RangerArrowEntity(world);
    }

    @Override
    public SoundEvent getShootSound() {
        return SoundEvents.ENTITY_ARROW_SHOOT;
    }

    @Override
    public String GUID() {
        return "recoil_shot";
    }

    @Override
    public int getManaCost() {
        return 20;
    }

    @Override
    public int useTimeTicks() {
        return 5;
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.allAttackAndSpellDamages(1, 0.5F, 5);
    }

    @Override
    public Elements getElement() {
        return Elements.Elemental;
    }

    public static void dashBackward(LivingEntity caster) {

        float distance = 0.017453292f;
        caster.setMotion(new Vec3d(0, 0, 0));
        double x = (double) -MathHelper.sin(caster.rotationYaw * distance);
        double z = (double) (MathHelper.cos(caster.rotationYaw * distance));

        caster.knockBack(caster, 1.8f, x, z);

        if (caster instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity) caster).connection.sendPacket(new SEntityVelocityPacket(caster));
            caster.velocityChanged = false;
        }
    }

    @Override
    public boolean cast(LivingEntity caster, int ticksInUse) {
        boolean bool = super.cast(caster, ticksInUse);
        dashBackward(caster);
        return bool;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Shoots an arrow and dash back: "));

        list.addAll(getCalculation().GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.RecoilShot;
    }

    private static class SingletonHolder {
        private static final RecoilShotSpell INSTANCE = new RecoilShotSpell();
    }
}
