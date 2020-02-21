package com.robertx22.mine_and_slash.potion_effects.shaman;

import com.robertx22.mine_and_slash.database.stats.types.generated.AllElementalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class ThunderEssenceEffect extends BasePotionEffect implements IApplyStatPotion {

    public static final ThunderEssenceEffect INSTANCE = new ThunderEssenceEffect();

    private ThunderEssenceEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

    }

    @Override
    public int getDurationInSeconds() {
        return 20;
    }

    @Override
    public String GUID() {
        return "thunder_essence";
    }

    @Override
    public String locNameForLangFile() {
        return "Thunder Essence";
    }

    @Override
    public int getMaxStacks() {
        return 20;
    }

    public ExactStatData getCrit(EntityCap.UnitData data, ExtraPotionData extraData) {
        float statAmount = 0.5F * extraData.getStacks();
        return new ExactStatData(statAmount, StatModTypes.Flat, CriticalHit.getInstance());
    }

    public ExactStatData getThunder(EntityCap.UnitData data, ExtraPotionData extraData) {
        int statAmount = 1 * extraData.getStacks();
        return new ExactStatData(statAmount, StatModTypes.Flat, new AllElementalDamage(Elements.Thunder)).scaleToLvl(
                extraData.casterLvl);
    }

    @Override
    public List<ExactStatData> getStatsAffected(EntityCap.UnitData data, ExtraPotionData extraData) {

        List<ExactStatData> list = new ArrayList<>();

        list.add(getThunder(data, extraData));
        list.add(getCrit(data, extraData));

        return list;

    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        return list;

    }

}