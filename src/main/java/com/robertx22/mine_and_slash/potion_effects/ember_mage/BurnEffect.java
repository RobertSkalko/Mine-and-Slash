package com.robertx22.mine_and_slash.potion_effects.ember_mage;

import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionDataSaving;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class BurnEffect extends BasePotionEffect implements IApplyStatPotion {

    public static final BurnEffect INSTANCE = new BurnEffect();

    private BurnEffect() {
        super(EffectType.HARMFUL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));
        this.needsTickTooltip = true;
    }

    static SpellCalcData CALC = SpellCalcData.one(new ElementalSpellDamage(Elements.Fire), 0.25F, 2);

    @Override
    public int getDurationInSeconds() {
        return 15;
    }

    @Override
    public void onXTicks(LivingEntity entity, EffectInstance instance) {

        ExtraPotionData extraData = PotionDataSaving.getData(instance);

        LivingEntity caster = extraData.getCaster(entity.world);

        int num = CALC.getCalculatedValue(Load.Unit(caster));

        DamageEffect dmg = new DamageEffect(null, caster, entity, num, EffectData.EffectTypes.SPELL, WeaponTypes.None);
        dmg.element = Elements.Fire;
        dmg.removeKnockback();
        dmg.Activate();

        SoundUtils.playSound(entity, SoundEvents.BLOCK_CAMPFIRE_CRACKLE, 0.5F, 1F);

        ParticleUtils.spawnParticles(ParticleTypes.FLAME, entity, 5);
        //  ParticleUtils.spawnParticles(ParticleTypes.LAVA, entity, 5);
    }

    @Override
    public String GUID() {
        return "burn";
    }

    @Override
    public int performEachXTicks() {
        return 20;
    }

    @Override
    public String locNameForLangFile() {
        return "Burn";
    }

    @Override
    public int getMaxStacks() {
        return 3;
    }

    public ExactStatData fire(EntityCap.UnitData data, ExtraPotionData extraData) {
        int statAmount = -1 * extraData.getStacks();
        return new ExactStatData(statAmount, StatTypes.Flat, new ElementalResist(Elements.Fire)).scaleToLvl(
                extraData.casterLvl);
    }

    public ExactStatData water(EntityCap.UnitData data, ExtraPotionData extraData) {
        int statAmount = -2 * extraData.getStacks();
        return new ExactStatData(statAmount, StatTypes.Flat, new ElementalResist(Elements.Water)).scaleToLvl(
                extraData.casterLvl);
    }

    @Override
    public List<ExactStatData> getStatsAffected(EntityCap.UnitData data, ExtraPotionData extraData) {

        List<ExactStatData> list = new ArrayList<>();

        list.add(fire(data, extraData));
        list.add(water(data, extraData));

        return list;

    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Does damage:"));

        list.addAll(CALC.GetTooltipString(info));

        return list;
    }
}

