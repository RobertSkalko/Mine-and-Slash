package com.robertx22.mine_and_slash.new_content_test.potion_datas;

import com.robertx22.mine_and_slash.new_content_test.potion_datas.interfaces.IPotionEffect;
import net.minecraft.entity.LivingEntity;

public class PotionContext {

    private PotionContext(IPotionEffect.Type type, PotionData data) {
        this.effectType = type;
        this.potionData = data;
    }

    public static PotionContext onTick(PotionData data, LivingEntity en) {
        PotionContext ctx = new PotionContext(IPotionEffect.Type.Tick, data);
        ctx.source = en;
        ctx.target = en;
        return ctx;
    }

    public static PotionContext onStatCalc(PotionData data, LivingEntity en) {
        PotionContext ctx = new PotionContext(IPotionEffect.Type.StatGive, data);
        ctx.source = en;
        ctx.target = en;
        return ctx;
    }

    public static PotionContext onAttack(PotionData data, LivingEntity source,
                                         LivingEntity target) {
        PotionContext ctx = new PotionContext(IPotionEffect.Type.OnAttack, data);
        ctx.source = source;
        ctx.target = target;
        return ctx;
    }

    public static PotionContext onAttacked(PotionData data, LivingEntity source,
                                           LivingEntity target) {
        PotionContext ctx = new PotionContext(IPotionEffect.Type.OnAttacked, data);
        ctx.effectType = IPotionEffect.Type.OnAttacked;
        ctx.potionData = data;
        ctx.source = source;
        ctx.target = target;
        return ctx;
    }

    public PotionData potionData;
    public IPotionEffect.Type effectType;
    public LivingEntity source;
    public LivingEntity target;

}
