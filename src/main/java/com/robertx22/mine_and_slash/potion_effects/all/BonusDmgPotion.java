package com.robertx22.mine_and_slash.potion_effects.all;

import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalBonusDmgOnBasic;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.IStatPotion;
import com.robertx22.mine_and_slash.potion_effects.SpellPotionBase;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BonusDmgPotion extends SpellPotionBase implements IStatPotion, IGenerated<BonusDmgPotion> {

    public static final BonusDmgPotion INSTANCE = new BonusDmgPotion(Elements.Physical);

    public static final HashMap<Elements, BonusDmgPotion> MAP = new HashMap<>();

    private BonusDmgPotion(Elements element) {
        // boolean isBadEffectIn, int liquidColorIn
        super(EffectType.BENEFICIAL, 4393423);
        this.element = element;
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

    }

    public Elements element;

    public static EffectInstance getFirstEffect(LivingEntity entity) {

        for (BonusDmgPotion effect : BonusDmgPotion.MAP.values()) {
            EffectInstance potion = entity.getActivePotionEffect(effect);
            if (potion != null && potion.getPotion() instanceof BonusDmgPotion) {
                return potion;
            }
        }
        return null;
    }

    @Override
    public String GUID() {
        return "bonus_" + element.dmgName.toLowerCase() + "_dmg";
    }

    @Override
    public void doEffect(Entity applier, Entity caster, LivingEntity target,
                         int amplifier) {

    }

    @Override
    public void performEffectEverySetTime(LivingEntity entity, int amplifier) {

    }

    @Override
    public int performEachXTicks() {
        return 40;
    }

    @Override
    public String locNameForLangFile() {
        return "+ " + element.dmgName + " Dmg";
    }

    @Override
    public List<BonusDmgPotion> generateAllPossibleStatVariations() {
        Elements.getAllSingleElements().forEach(x -> MAP.put(x, new BonusDmgPotion(x)));
        return new ArrayList<>(MAP.values());
    }

    @Override
    public void applyStats(EntityCap.UnitData data, EffectInstance instance) {
        data.getUnit()
                .getStat(new ElementalBonusDmgOnBasic(element)).Flat += instance.getAmplifier();

    }
}
