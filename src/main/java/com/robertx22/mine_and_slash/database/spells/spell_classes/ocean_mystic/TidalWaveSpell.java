package com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic;

import com.robertx22.mine_and_slash.database.spells.ProjectileBuilder;
import com.robertx22.mine_and_slash.database.spells.entities.proj.TidalWaveEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseProjectileSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class TidalWaveSpell extends BaseProjectileSpell {

    private TidalWaveSpell() {
    }

    public static TidalWaveSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.OCEAN_MYSTIC;
    }

    @Override
    public int getCooldownInSeconds() {
        return 0;
    }

    @Override
    public BaseSpell.SpellType getSpellType() {
        return SpellType.Single_Target_Projectile;
    }

    @Override
    public float getShootSpeed() {
        return 0.9F;
    }

    @Override
    public AbstractArrowEntity newEntity(World world) {
        return new TidalWaveEntity(world);
    }

    @Override
    public SoundEvent getShootSound() {
        return SoundEvents.BLOCK_BUBBLE_COLUMN_UPWARDS_INSIDE;
    }

    @Override
    public String GUID() {
        return "tidal_wave";
    }

    @Override
    public int getManaCost() {
        return 15;
    }

    @Override
    public int useTimeTicks() {
        return 50;
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.allAttackAndSpellDamages(0.5F, 0.6F, 5);
    }

    @Override
    public Elements getElement() {
        return Elements.Water;
    }

    @Override
    public boolean cast(LivingEntity caster, int ticksInUse) {

        ProjectileBuilder builder = new ProjectileBuilder(this, (world) -> newEntity(world), caster);
        builder.projectilesAmount = 5;
        builder.shootSpeed = getShootSpeed();
        builder.apart = 75;
        builder.cast();

        if (getShootSound() != null) {
            SoundUtils.playSound(caster, getShootSound(), 1.0F, 1.0F);
        }

        return true;
    }

    @Override
    public void onCastingTick(PlayerEntity player, PlayerSpellCap.ISpellsCap spells, int tick) {
        if (tick % 10 == 0) {
            this.cast(player, 0);
        }
    }

    @Override
    public boolean goesOnCooldownIfCastCanceled() {
        return true;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Throw waves in a cone, damaging enemies: "));

        list.addAll(getCalculation().GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.TidalWave;
    }

    private static class SingletonHolder {
        private static final TidalWaveSpell INSTANCE = new TidalWaveSpell();
    }
}
