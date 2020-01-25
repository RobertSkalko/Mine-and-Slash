package com.robertx22.mine_and_slash.potion_effects.druid;

import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.IApplyStatPotion;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionDataSaving;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
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

public class ThornArmorEffect extends BasePotionEffect implements IApplyStatPotion {

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
    public int maxStacks() {
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
    public void applyStats(EntityCap.UnitData data, EffectInstance instance) {

        ExtraPotionData extraData = PotionDataSaving.getData(instance);

        ExactStatData nature = getNatureRes(data, extraData);
        ExactStatData armor = getArmor(data, extraData);

        nature.applyStats(data);
        armor.applyStats(data);

    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        ExtraPotionData data = PotionEffectUtils.getDataForTooltips(this);

        list.add(locName());

        list.add(new StringTextComponent("Adds stats: "));

        ExactStatData nature = getNatureRes(info.unitdata, data);
        ExactStatData armor = getArmor(info.unitdata, data);

        list.addAll(nature.GetTooltipString(info));
        list.addAll(armor.GetTooltipString(info));

        return list;

    }

}

