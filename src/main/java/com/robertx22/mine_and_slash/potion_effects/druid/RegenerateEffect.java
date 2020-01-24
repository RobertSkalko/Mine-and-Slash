package com.robertx22.mine_and_slash.potion_effects.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.RegenerateSpell;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class RegenerateEffect extends BasePotionEffect {

    public static final RegenerateEffect INSTANCE = new RegenerateEffect();

    private RegenerateEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

    }

    @Override
    public String GUID() {
        return "self_regen";
    }

    public static SpellCalcData CALC = SpellCalcData.one(HealthRegen.INSTANCE, 0.75F, 5);

    @Override
    public void onXTicks(LivingEntity entity, EffectInstance instance) {

        try {

            if (entity.world.isRemote) {
                ParticleUtils.spawnParticles(ParticleTypes.HAPPY_VILLAGER, entity, 3);
            } else {
                UnitData data = Load.Unit(entity);

                // ExtraPotionData extraData = PotionDataSaving.getData(instance);

                int num = CALC.getCalculatedValue(data);

                ResourcesData.Context hp = new ResourcesData.Context(data, entity, ResourcesData.Type.HEALTH, num,
                                                                     ResourcesData.Use.RESTORE, new RegenerateSpell()
                );

                data.modifyResource(hp);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int performEachXTicks() {
        return 30;
    }

    @Override
    public String locNameForLangFile() {
        return "Regenerate";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        list.add(locName());

        list.addAll(CALC.GetTooltipString(info));

        return list;
    }
}
