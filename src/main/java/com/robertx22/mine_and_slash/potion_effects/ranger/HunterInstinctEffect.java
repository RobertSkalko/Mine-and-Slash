package com.robertx22.mine_and_slash.potion_effects.ranger;

import com.robertx22.mine_and_slash.database.stats.types.defense.DodgeRating;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class HunterInstinctEffect extends BasePotionEffect implements IApplyStatPotion {

    private HunterInstinctEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));
    }

    public static HunterInstinctEffect getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "hunter_instinct";
    }

    @Override
    public String locNameForLangFile() {
        return "Hunter Instinct";
    }

    @Override
    public int getMaxStacks() {
        return 20;
    }

    @Override
    public int getDurationInSeconds() {
        return 20;
    }

    public ExactStatData getCritRate(EntityCap.UnitData data, ExtraPotionData extraData) {
        float statAmount = 0.5F * extraData.getStacks();
        return new ExactStatData(statAmount, StatModTypes.Flat, CriticalHit.getInstance()).scaleToLvl(
            extraData.casterLvl);
    }

    public ExactStatData getDodge(EntityCap.UnitData data, ExtraPotionData extraData) {
        int statAmount = 1 * extraData.getStacks();
        return new ExactStatData(statAmount, StatModTypes.Flat, DodgeRating.getInstance()).scaleToLvl(extraData.casterLvl);
    }

    @Override
    public List<ExactStatData> getStatsAffected(EntityCap.UnitData data, ExtraPotionData extraData) {

        List<ExactStatData> list = new ArrayList<>();

        list.add(getCritRate(data, extraData));
        list.add(getDodge(data, extraData));

        return list;

    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        return list;

    }

    private static class SingletonHolder {
        private static final HunterInstinctEffect INSTANCE = new HunterInstinctEffect();
    }
}


