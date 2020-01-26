package com.robertx22.mine_and_slash.potion_effects.ocean_mystic;

import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class ShiverEffect extends BasePotionEffect implements IApplyStatPotion {

    public static final ShiverEffect INSTANCE = new ShiverEffect();

    private ShiverEffect() {
        super(EffectType.HARMFUL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));
    }

    @Override
    public void onXTicks(LivingEntity entity, EffectInstance instance) {
        ParticleUtils.spawnParticles(ParticleTypes.DOLPHIN, entity, 5);
    }

    @Override
    public int getDurationInSeconds() {
        return 6;
    }

    @Override
    public String GUID() {
        return "shiver";
    }

    @Override
    public int performEachXTicks() {
        return 20;
    }

    @Override
    public String locNameForLangFile() {
        return "Shiver";
    }

    @Override
    public int getMaxStacks() {
        return 1;
    }

    public ExactStatData getFire(EntityCap.UnitData data, ExtraPotionData extraData) {
        int statAmount = -5 * extraData.getStacks();
        return new ExactStatData(statAmount, StatTypes.Flat, new ElementalResist(Elements.Fire)).scaleToLvl(
                extraData.casterLvl);
    }

    public ExactStatData getThunder(EntityCap.UnitData data, ExtraPotionData extraData) {
        int statAmount = -5 * extraData.getStacks();
        return new ExactStatData(statAmount, StatTypes.Flat, new ElementalResist(Elements.Thunder)).scaleToLvl(
                extraData.casterLvl);
    }

    public ExactStatData getWater(EntityCap.UnitData data, ExtraPotionData extraData) {
        int statAmount = -2 * extraData.getStacks();
        return new ExactStatData(statAmount, StatTypes.Flat, new ElementalResist(Elements.Water)).scaleToLvl(
                extraData.casterLvl);
    }

    @Override
    public List<ExactStatData> getStatsAffected(EntityCap.UnitData data, ExtraPotionData extraData) {

        List<ExactStatData> list = new ArrayList<>();

        list.add(getWater(data, extraData));
        list.add(getFire(data, extraData));
        list.add(getThunder(data, extraData));

        return list;

    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        return list;

    }

}

