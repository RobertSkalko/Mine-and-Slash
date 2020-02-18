package com.robertx22.mine_and_slash.database.spells.spell_classes.ranger;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.proj.RangerArrowEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseProjectileSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class TripleShotSpell extends BaseProjectileSpell {

    private TripleShotSpell() {
        this.castRequirements.add(BaseSpell.REQUIRE_SHOOTABLE_ITEM);
    }

    public static TripleShotSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.RANGER;
    }

    @Override
    public int getCooldownInSeconds() {
        return 5;
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
        return "triple_shot";
    }

    @Override
    public int getManaCost() {
        return 20;
    }

    @Override
    public int useTimeTicks() {
        return 10;
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.allAttackDamages(0.5F, 4);
    }

    @Override
    public Elements getElement() {
        return Elements.Elemental;
    }

    @Override
    public boolean cast(LivingEntity caster, int ticksInUse) {

        World world = caster.world;

        float apart = 1.5F;

        for (int i = 0; i < 3; i++) {

            float f = 0;

            if (i == 0) {
                f = apart;
            }
            if (i == 2) {
                f = -apart;
            }
            f *= 10;

            RangerArrowEntity en = (RangerArrowEntity) SpellUtils.getSpellEntity(newEntity(world), this, caster);
            SpellUtils.setupProjectileForCasting(en, caster, getShootSpeed(), caster.rotationPitch,
                caster.rotationYaw + f
            );

            caster.world.addEntity(en);

            if (getShootSound() != null) {
                SoundUtils.playSound(caster, getShootSound(), 1.0F, 1.0F);
            }
        }
        return true;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Shoots multiple arrows in an arc: "));
        list.add(new StringTextComponent("Requires Bow/Crossbow to use: "));

        list.addAll(getCalculation().GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.TripleShot;
    }

    private static class SingletonHolder {
        private static final TripleShotSpell INSTANCE = new TripleShotSpell();
    }
}

