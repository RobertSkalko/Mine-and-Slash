package com.robertx22.mine_and_slash.potion_effects.druid;

import com.robertx22.mine_and_slash.database.spells.synergies.Synergies;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterTargetContext;
import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.IOnBasicAttackPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.IOnBasicAttackedPotion;
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

import java.util.ArrayList;
import java.util.List;

public class ThornArmorEffect extends BasePotionEffect implements IApplyStatPotion, IOnBasicAttackedPotion,
        IOnBasicAttackPotion {

    public static final ThornArmorEffect INSTANCE = new ThornArmorEffect();

    private ThornArmorEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));
    }

    @Override
    public void onXTicks(LivingEntity entity, EffectInstance instance) {
        //ParticleUtils.spawnParticles(ParticleTypes.DOLPHIN, entity, 5);

    }

    @Override
    public String GUID() {
        return "thorn_armor";
    }

    @Override
    public int performEachXTicks() {
        return 20;
    }

    @Override
    public String locNameForLangFile() {
        return "Thorn Armor";
    }

    @Override
    public int getMaxStacks() {
        return 1;
    }

    @Override
    public int getDurationInSeconds() {
        return 15;
    }

    public ExactStatData getNatureRes(EntityCap.UnitData data, ExtraPotionData extraData) {
        int statAmount = 3 * extraData.getStacks();
        return new ExactStatData(statAmount, StatTypes.Flat, new ElementalResist(Elements.Nature)).scaleToLvl(
                extraData.casterLvl);
    }

    public ExactStatData getArmor(EntityCap.UnitData data, ExtraPotionData extraData) {
        int statAmount = 1 * extraData.getStacks();
        return new ExactStatData(statAmount, StatTypes.Flat, Armor.INSTANCE).scaleToLvl(extraData.casterLvl);
    }

    @Override
    public List<ExactStatData> getStatsAffected(EntityCap.UnitData data, ExtraPotionData extraData) {

        List<ExactStatData> list = new ArrayList<>();

        list.add(getNatureRes(data, extraData));
        list.add(getArmor(data, extraData));

        return list;

    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        return list;

    }

    @Override
    public void onBasicAttacked(LivingEntity source, LivingEntity target) {
        if (Synergies.THORN_ARMOR_THORNS.has(target)) {
            Synergies.THORN_ARMOR_THORNS.tryActivate(new CasterTargetContext(source, target));
        }
    }

    @Override
    public void OnBasicAttack(LivingEntity source, LivingEntity target) {
        if (Synergies.THORN_ARMOR_THORNS.has(source)) {
            Synergies.THORN_ARMOR_THORNS.tryActivate(new CasterTargetContext(source, target));
        }
    }
}

