package com.robertx22.mine_and_slash.potion_effects.ocean_mystic;

import com.robertx22.mine_and_slash.database.stats.types.generated.AllElementalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionDataSaving;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class FrostEssenceEffect extends BasePotionEffect implements IStatPotion {

    public static final FrostEssenceEffect INSTANCE = new FrostEssenceEffect();

    public static final int DURATION = 200;

    private FrostEssenceEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));
    }

    @Override
    public void onXTicks(LivingEntity entity, EffectInstance instance) {
        //ParticleUtils.spawnParticles(ParticleTypes.DOLPHIN, entity, 5);
    }

    @Override
    public String GUID() {
        return "frost_essence";
    }

    @Override
    public int performEachXTicks() {
        return 20;
    }

    @Override
    public String locNameForLangFile() {
        return "Frost Essence";
    }

    @Override
    public int maxStacks() {
        return 20;
    }

    public ExactStatData getCrit(EntityCap.UnitData data, ExtraPotionData extraData) {
        float statAmount = 0.5F * extraData.getStacks();
        return new ExactStatData(statAmount, StatTypes.Flat, CriticalDamage.INSTANCE);
    }

    public ExactStatData getWater(EntityCap.UnitData data, ExtraPotionData extraData) {
        int statAmount = 1 * extraData.getStacks();
        return new ExactStatData(statAmount, StatTypes.Flat, new AllElementalDamage(Elements.Water)).scaleToLvl(
                extraData.casterLvl);
    }

    @Override
    public void applyStats(EntityCap.UnitData data, EffectInstance instance) {

        ExtraPotionData extraData = PotionDataSaving.getData(instance);

        ExactStatData water = getWater(data, extraData);
        ExactStatData crit = getCrit(data, extraData);

        water.applyStats(data);
        crit.applyStats(data);

    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        list.add(locName());

        list.add(new StringTextComponent("Adds stats: "));

        ExactStatData water = getWater(info.unitdata, new ExtraPotionData());
        ExactStatData crit = getCrit(info.unitdata, new ExtraPotionData());

        list.addAll(water.GetTooltipString(info));
        list.addAll(crit.GetTooltipString(info));

        return list;

    }

}

