package com.robertx22.mine_and_slash.potion_effects.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.druid.RegenerateSpell;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.OnTickAction;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class RegenerateEffect extends BasePotionEffect {

    public static final RegenerateEffect INSTANCE = new RegenerateEffect();

    private RegenerateEffect() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.tickActions.add(new OnTickAction(30, ctx -> {
            if (ctx.entity.world.isRemote) {
                ParticleUtils.spawnParticles(ParticleTypes.HAPPY_VILLAGER, ctx.entity, 3);
            } else {

                int num = CALC.getCalculatedValue(ctx.casterData);

                ResourcesData.Context hp = new ResourcesData.Context(ctx.caster, ctx.entity, ctx.casterData,
                                                                     ctx.entityData, ResourcesData.Type.HEALTH, num,
                                                                     ResourcesData.Use.RESTORE,
                                                                     RegenerateSpell.getInstance()
                );

                ctx.entityData.modifyResource(hp);
            }
            return ctx;
        }, info -> {
            List<ITextComponent> list = new ArrayList<>();
            list.add(new StringTextComponent("Heals user."));
            list.addAll(CALC.GetTooltipString(info));
            return list;
        }));

    }

    @Override
    public String GUID() {
        return "self_regen";
    }

    public static SpellCalcData CALC = SpellCalcData.one(HealthRegen.getInstance(), 0.75F, 5);

    @Override
    public int getDurationInSeconds() {
        return 25;
    }

    @Override
    public String locNameForLangFile() {
        return "Regenerate";
    }

}
