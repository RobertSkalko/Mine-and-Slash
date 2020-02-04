package com.robertx22.mine_and_slash.potion_effects.all;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.potion_effects.bases.OnTickAction;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public class CleansePoisonPotion extends BasePotionEffect {

    public static final CleansePoisonPotion INSTANCE = new CleansePoisonPotion();

    private CleansePoisonPotion() {
        super(EffectType.BENEFICIAL, 4393423);
        this.setRegistryName(new ResourceLocation(Ref.MODID, GUID()));

        this.tickActions.add(new OnTickAction(20, ctx -> {
            if (ctx.entity.world.isRemote) {
                ParticleUtils.spawnHealParticles(ctx.entity, 1);
            } else {

                if (ctx.entity.getActivePotionEffect(Effects.POISON) != null) {
                    ctx.entity.removePotionEffect(Effects.POISON);
                }
            }
            return ctx;
        }, null));

    }

    @Override
    public int getDurationInSeconds() {
        return 5;
    }

    @Override
    public String GUID() {
        return "cleanse_poison";
    }

    @Override
    public String locNameForLangFile() {
        return "Cleanse Poison";
    }

    @Override
    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        return new ArrayList<>();
    }
}