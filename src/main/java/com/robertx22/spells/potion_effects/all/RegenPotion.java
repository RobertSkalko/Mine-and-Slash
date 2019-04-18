package com.robertx22.spells.potion_effects.all;

import com.robertx22.mmorpg.Ref;
import com.robertx22.spells.potion_effects.SpellCast;
import com.robertx22.spells.potion_effects.SpellPotionBase;
import com.robertx22.spells.self.SpellInstantHeal;
import com.robertx22.uncommon.capability.EntityData.UnitData;
import com.robertx22.uncommon.datasaving.Load;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegenPotion extends SpellPotionBase {

    public static final RegenPotion INSTANCE = new RegenPotion();

    @SubscribeEvent
    public static void register(RegistryEvent.Register<Potion> event) {
	event.getRegistry().register(INSTANCE.finish());
    }

    public static SpellCast Create(Entity source) {
	return INSTANCE.newSpellCast(source);
    }

    private RegenPotion() {
	// boolean isBadEffectIn, int liquidColorIn
	super(false, 4393423);
	setPotionName("effect.regen");
    }

    @Override
    public ResourceLocation getIconTexture() {
	return new ResourceLocation(Ref.MODID, "textures/status_effects/regen.png");
    }

    private static void apply(EntityLivingBase entity) {

    }

    @Override
    public boolean canSelfCast() {
	return true;
    }

    @Override
    public void doEffect(Entity applier, Entity caster, EntityLivingBase target, int amplifier, SpellCast cast) {

	apply(target);

    }

    @Override
    public void performEffectEverySetTime(EntityLivingBase entity, int amplifier) {

	UnitData data = Load.Unit(entity);

	float hp_percent = ((float) amplifier) / 100;

	if (entity.world.isRemote) {
	    SpellInstantHeal.spawnHealParticles(entity, 3);
	} else {
	    Load.Unit(entity).heal(entity, (int) ((int) data.getUnit().healthData().Value * hp_percent));

	    System.out.println("healed " + hp_percent);

	}

    }

    @Override
    public int performEachXTicks() {
	return 50;
    }
}
