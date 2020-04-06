package com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage;

import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellTooltips;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.ember_mage.BlazingInfernoEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
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
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class BlazingInfernoSpell extends BaseSpell {

    private BlazingInfernoSpell() {
    }

    public static BlazingInfernoSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.EMBER_MAGE;
    }

    @Override
    public int getCooldownInSeconds() {
        return 30;
    }

    @Override
    public BaseSpell.SpellType getSpellType() {
        return SpellType.LASTING_AOE;
    }

    @Override
    public String GUID() {
        return "blazing_inferno";
    }

    @Override
    public int getManaCost() {
        return 40;
    }

    @Override
    public int useTimeTicks() {
        return 20;
    }

    @Override
    public SpellCalcData getCalculation() {
        return BlazingInfernoEffect.CALC;
    }

    @Override
    public Elements getElement() {
        return Elements.Fire;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(SpellTooltips.buff());

        list.addAll(BlazingInfernoEffect.INSTANCE.GetTooltipString(info));

        return list;

    }

    public static float RADIUS = 3.5F;

    public static void damageMobsAroundYou(LivingEntity entity, LivingEntity caster) {

        if (!entity.world.isRemote) {

            ParticlePacketData pdata = new ParticlePacketData(entity.getPosition()
                .up(1), ParticleEnum.BLAZING_INFERNO);
            pdata.radius = RADIUS;
            ParticleEnum.BLAZING_INFERNO.sendToClients(entity, pdata);

            int num = CALC.getCalculatedValue(Load.Unit(caster));

            List<LivingEntity> entities = EntityFinder.start(entity, LivingEntity.class, entity.getPositionVector())
                .radius(RADIUS)
                .build();

            for (LivingEntity en : entities) {
                DamageEffect dmg = new DamageEffect(
                    null, caster, en, num, EffectData.EffectTypes.SPELL, WeaponTypes.None);
                dmg.element = Elements.Fire;
                dmg.Activate();

            }
        }
    }

    @Override
    public Words getName() {
        return Words.BlazingInferno;
    }

    @Override
    public boolean cast(LivingEntity caster, int ticksInUse) {

        PotionEffectUtils.applyToSelf(BlazingInfernoEffect.INSTANCE, caster);

        SoundUtils.playSound(caster, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, 1, 1);

        return true;
    }

    private static class SingletonHolder {
        private static final BlazingInfernoSpell INSTANCE = new BlazingInfernoSpell();
    }
}
