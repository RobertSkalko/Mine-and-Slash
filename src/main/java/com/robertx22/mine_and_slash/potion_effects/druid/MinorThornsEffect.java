package com.robertx22.mine_and_slash.potion_effects.druid;

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
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class MinorThornsEffect extends BasePotionEffect implements IApplyStatPotion {

    public static final MinorThornsEffect INSTANCE = new MinorThornsEffect();

    private MinorThornsEffect() {
        super(EffectType.HARMFUL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));
        this.needsTickTooltip = true;
    }

    static SpellCalcData CALC = SpellCalcData.one(new ElementalSpellDamage(Elements.Nature), 0.1F, 2);

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
        dmg.element = Elements.Nature;
        dmg.removeKnockback();
        dmg.Activate();

        /// SoundUtils.playSound(entity, SoundEvents., 1F, 1F);

        ParticleUtils.spawnParticles(
                new BlockParticleData(ParticleTypes.BLOCK, Blocks.BIRCH_LEAVES.getDefaultState()), entity, 2);
        ParticleUtils.spawnParticles(ParticleTypes.ITEM_SLIME, entity, 5);
    }

    @Override
    public String GUID() {
        return "minor_thorns";
    }

    @Override
    public int performEachXTicks() {
        return 20;
    }

    @Override
    public String locNameForLangFile() {
        return "Minor Thorns";
    }

    @Override
    public int getMaxStacks() {
        return 1;
    }

    public ExactStatData getStatMod(EntityCap.UnitData data, Elements ele, ExtraPotionData extraData) {
        int statAmount = -3 * extraData.getStacks();
        return new ExactStatData(statAmount, StatTypes.Flat, new ElementalResist(ele)).scaleToLvl(extraData.casterLvl);
    }

    @Override
    public List<ExactStatData> getStatsAffected(EntityCap.UnitData data, ExtraPotionData extraData) {

        List<ExactStatData> list = new ArrayList<>();

        list.add(getStatMod(data, Elements.Thunder, extraData));
        list.add(getStatMod(data, Elements.Nature, extraData));

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

