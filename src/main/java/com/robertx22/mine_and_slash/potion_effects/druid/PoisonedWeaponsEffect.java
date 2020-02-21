package com.robertx22.mine_and_slash.potion_effects.druid;

import com.robertx22.mine_and_slash.database.spells.synergies.Synergies;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterTargetContext;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalAttackDamage;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.IOnBasicAttackPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class PoisonedWeaponsEffect extends BasePotionEffect implements IApplyStatPotion, IOnBasicAttackPotion {

    private PoisonedWeaponsEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));
    }

    public static PoisonedWeaponsEffect getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "poisoned_weapons";
    }

    @Override
    public String locNameForLangFile() {
        return "Poisoned Weapons";
    }

    @Override
    public int getMaxStacks() {
        return 1;
    }

    @Override
    public int getDurationInSeconds() {
        return 30;
    }

    public ExactStatData getDmg(EntityCap.UnitData data, ExtraPotionData extraData) {
        float statAmount = 2.5F;
        return new ExactStatData(statAmount, StatModTypes.Flat, ElementalAttackDamage.MAP.get(Elements.Nature)).scaleToLvl(
                extraData.casterLvl);
    }

    @Override
    public List<ExactStatData> getStatsAffected(EntityCap.UnitData data, ExtraPotionData extraData) {

        List<ExactStatData> list = new ArrayList<>();

        list.add(getDmg(data, extraData));

        return list;

    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        return list;

    }

    @Override
    public void OnBasicAttack(LivingEntity source, LivingEntity target) {
        if (Synergies.POISONED_WEAPONS_THORNS.has(source)) {
            Synergies.POISONED_WEAPONS_THORNS.tryActivate(new CasterTargetContext(source, target));
        }
    }

    private static class SingletonHolder {
        private static final PoisonedWeaponsEffect INSTANCE = new PoisonedWeaponsEffect();
    }
}

