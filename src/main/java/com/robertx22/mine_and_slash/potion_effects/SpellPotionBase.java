package com.robertx22.mine_and_slash.potion_effects;

import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;

public abstract class SpellPotionBase extends Effect implements IAutoLocName {

    public abstract void performEffectEverySetTime(LivingEntity entityLivingBaseIn,

                                                   int amplifier);

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Potions;
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }

    public abstract String GUID();

    @Override
    public ITextComponent locName() {
        return CLOC.blank("effect." + Ref.MODID + GUID());
    }

    public abstract int performEachXTicks();

    public List<LivingEntity> getEntitiesAround(Entity en, float radius) {

        return en.world.getEntitiesWithinAABB(LivingEntity.class, en.getBoundingBox()
                .grow(radius));
    }

    public ResourceLocation getIconTexture() {
        return new ResourceLocation(Ref.MODID, "textures/mob_effect/" + GUID() + ".png");
    }

    @Override
    public void performEffect(LivingEntity en, int amplifier) {

        if (en.ticksExisted % performEachXTicks() == 0)
            performEffectEverySetTime(en, amplifier);
    }

    protected SpellPotionBase(EffectType type, int liquidColorIn) {
        super(type, liquidColorIn);

    }

    protected boolean isServerSideOnly() {
        return true;
    }

    @Override
    public boolean isReady(int duration, int amplitude) {
        // Controls whether performEffect is called
        // System.out.printf("SpellPotionBase - isReady %d %d\n", duration, amplitude);
        return duration >= 1;
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    // Called when the potion is being applied by an
    // AreaEffect or thrown potion bottle
    @Override
    public void affectEntity(Entity applier, Entity caster, @Nonnull LivingEntity target,
                             int amplifier, double health) {

        if (target.world.isRemote && isServerSideOnly())
            return;

        doEffect(applier, caster, target, amplifier);
    }

    @Override
    public void applyAttributesModifiersToEntity(LivingEntity target,
                                                 @Nonnull AbstractAttributeMap attributes,
                                                 int amplifier) {

        if (!target.world.isRemote || !isServerSideOnly()) {

            onPotionAdd(target, attributes, amplifier);

        }

        // Called on application
        super.applyAttributesModifiersToEntity(target, attributes, amplifier);
    }

    @Override
    public void removeAttributesModifiersFromEntity(LivingEntity target,
                                                    @Nonnull AbstractAttributeMap attributes,
                                                    int amplifier) {

        // Called on removal
        super.removeAttributesModifiersFromEntity(target, attributes, amplifier);
    }

    public void onPotionAdd(LivingEntity target, AbstractAttributeMap attributes,
                            int amplifier) {
    }

    public void onPotionRemove(LivingEntity target, AbstractAttributeMap attributes,
                               int amplifier) {
    }

    public abstract void doEffect(Entity applier, Entity caster, LivingEntity target,
                                  int amplifier);

    protected boolean shouldShowParticles() {
        return true;
    }

    protected boolean isAmbient() {
        return false;
    }

    public EffectInstance toPotionEffect(int amplifier) {
        return toPotionEffect(1, amplifier);
    }

    public EffectInstance toPotionEffect(int duration, int amplifier) {
        return new EffectInstance(this, duration, amplifier, isAmbient(), shouldShowParticles());
    }

    @OnlyIn(Dist.CLIENT)
    public void renderHUDEffect(EffectInstance effect, AbstractGui gui, int x, int y,
                                float z, float alpha) {
        if (getIconTexture() != null) {
            Minecraft.getInstance().getTextureManager().bindTexture(getIconTexture());
            AbstractGui.blit(x + 4, y + 4, 0, 0, 16, 16, 16, 16);
        }
    }

}