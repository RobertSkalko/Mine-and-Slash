package com.robertx22.mine_and_slash.potion_effects.cleric;

import com.robertx22.mine_and_slash.database.stats.types.defense.DamageShield;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class DivineShieldEffect extends BasePotionEffect implements IApplyStatPotion {

    private DivineShieldEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

    }


    public static DivineShieldEffect getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "divine_shield";
    }

    @Override
    public int getDurationInSeconds() {
        return 30;
    }

    @Override
    public String locNameForLangFile() {
        return "Divine Shield";
    }

    @Override
    public int getMaxStacks() {
        return 1;
    }

    public ExactStatData getDmgShield(EntityCap.UnitData data, ExtraPotionData extraData) {
        float statAmount = 3F;
        return new ExactStatData(statAmount, StatModTypes.Flat, DamageShield.getInstance()).scaleToLvl(
            extraData.casterLvl);
    }

    @Override
    public List<ExactStatData> getStatsAffected(EntityCap.UnitData data, ExtraPotionData extraData) {

        List<ExactStatData> list = new ArrayList<>();

        list.add(getDmgShield(data, extraData));

        return list;

    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent("Protects you from harm."));

        return list;

    }

    private static class SingletonHolder {
        private static final DivineShieldEffect INSTANCE = new DivineShieldEffect();
    }
}

