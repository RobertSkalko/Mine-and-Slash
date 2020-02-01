package com.robertx22.mine_and_slash.database.spells.spell_classes.shaman;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.mmorpg.registers.common.Sounds;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ThunderDashSpell extends BaseSpell {

    public ThunderDashSpell() {

    }

    public Elements element = Elements.Thunder;

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.SHAMAN;
    }

    @Override
    public int getCooldownInSeconds() {
        return 30;
    }

    @Override
    public BaseSpell.SpellType getSpellType() {
        return SpellType.Dash;
    }

    @Override
    public String GUID() {
        return "thunder_dash";
    }

    @Override
    public int getManaCost() {
        return 30;
    }

    @Override
    public int useTimeTicks() {
        return 0;
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.one(dmgStat(), 0.5F, 5);
    }

    @Override
    public Elements getElement() {
        return element;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Dash in your current direction,"));
        list.add(new StringTextComponent("damages all enemies in the path."));

        list.addAll(getCalculation().GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.ThunderDash;
    }

    public static void dashForward(LivingEntity caster) {

        float distance = 0.017453292f;

        caster.setMotion(new Vec3d(0, 0, 0));

        caster.knockBack(caster, 3.5f, (double) MathHelper.sin(caster.rotationYaw * distance),
                         (double) (-MathHelper.cos(caster.rotationYaw * distance))
        );

        if (caster instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity) caster).connection.sendPacket(new SEntityVelocityPacket(caster));
            caster.velocityChanged = false;
        }
    }

    @Override
    public boolean cast(LivingEntity caster, int ticksInUse) {
        World world = caster.world;

        dashForward(caster);

        int num = getCalculation().getCalculatedValue(Load.Unit(caster));

        List<LivingEntity> entities = EntityFinder.start(caster, LivingEntity.class, caster.getPositionVector())
                .radius(2)
                .distance(10)
                .finder(EntityFinder.Finder.IN_FRONT)
                .build();

        entities.forEach(x -> {
            DamageEffect dmg = new DamageEffect(null, caster, x, num, EffectData.EffectTypes.SPELL, WeaponTypes.None);
            dmg.element = Elements.Thunder;
            dmg.Activate();
        });

        SoundUtils.playSound(caster, Sounds.DASH, 1, 1);

        return true;
    }
}