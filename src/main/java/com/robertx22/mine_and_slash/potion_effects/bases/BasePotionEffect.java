package com.robertx22.mine_and_slash.potion_effects.bases;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
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
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jline.utils.Log;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public abstract class BasePotionEffect extends Effect implements IAutoLocName, ITooltipList {

    public boolean needsTickTooltip = false;

    public abstract void onXTicks(LivingEntity entity, ExtraPotionData data, LivingEntity caster);

    public int getMaxStacks() {
        return 1;
    }

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
        return CLOC.blank("effect." + Ref.MODID + "." + GUID());
    }

    public abstract int performEachXTicks();

    public abstract List<ITextComponent> getEffectTooltip(TooltipInfo info);

    @Override
    public final List<ITextComponent> GetTooltipString(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.BOLD).appendSibling(
                locName()));

        list.addAll(getEffectTooltip(info));

        if (this instanceof IApplyStatPotion) {
            list.addAll(((IApplyStatPotion) this).getStatTooltip(info, this));
        }

        if (this.needsTickTooltip) {
            list.addAll(getTickTooltip());
        }
        list.addAll(getMaxStacksTooltip());
        list.addAll(getDurationTooltip());

        return list;
    }

    public ResourceLocation getIconTexture() {
        return new ResourceLocation(Ref.MODID, "textures/mob_effect/" + GUID() + ".png");
    }

    private List<ITextComponent> getMaxStacksTooltip() {
        List<ITextComponent> list = new ArrayList<>();

        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(
                TextFormatting.LIGHT_PURPLE + "Max Stacks: " + TextFormatting.DARK_PURPLE + getMaxStacks()));

        return list;

    }

    private List<ITextComponent> getDurationTooltip() {
        List<ITextComponent> list = new ArrayList<>();

        TooltipUtils.addEmpty(list);
        list.add(new StringTextComponent(
                TextFormatting.GOLD + "Duration: " + TextFormatting.YELLOW + getDurationInSeconds() + "s"));

        return list;

    }

    public abstract int getDurationInSeconds();

    public final int getDurationInTicks() {
        return getDurationInSeconds() * 20;
    }

    private List<ITextComponent> getTickTooltip() {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(
                TextFormatting.YELLOW + "Effect occurs every " + performEachXTicks() + " ticks."));

        return list;

    }

    @Override
    public void performEffect(LivingEntity en, int amplifier) {

        try {
            if (en.ticksExisted % performEachXTicks() == 0) {

                ExtraPotionData data = PotionEffectUtils.getDataForTooltips(this);

                if (data == null) {
                    Log.error("Extra potion data is null. Deleting potion");
                }

                LivingEntity caster = data.getCaster(en.world);

                if (caster == null) {
                    Log.error("Potion can't find caster. Deleting potion");
                }

                onXTicks(en, data, caster);
            }
        } catch (Exception e) {
            en.removePotionEffect(this);
            e.printStackTrace();
        }
    }

    protected BasePotionEffect(EffectType type, int liquidColorIn) {
        super(type, liquidColorIn);

    }

    public EffectInstance getInstanceFromEntity(LivingEntity entity) {
        return entity.getActivePotionEffect(this);
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
    public void affectEntity(Entity applier, Entity caster, @Nonnull LivingEntity target, int amplifier,
                             double health) {

        if (target.world.isRemote && isServerSideOnly())
            return;

        onEffectApplied(applier, caster, target, amplifier);
    }

    @Override
    public void applyAttributesModifiersToEntity(LivingEntity target, @Nonnull AbstractAttributeMap attributes,
                                                 int amplifier) {

        if (!target.world.isRemote || !isServerSideOnly()) {

            onPotionAdd(target);

        }

        // Called on application
        super.applyAttributesModifiersToEntity(target, attributes, amplifier);
    }

    @Override
    public void removeAttributesModifiersFromEntity(LivingEntity target, @Nonnull AbstractAttributeMap attributes,
                                                    int amplifier) {
        // called at end
        super.removeAttributesModifiersFromEntity(target, attributes, amplifier);

        this.onPotionRemove(target);
    }

    public void onPotionAdd(LivingEntity target) {
    }

    public void onPotionRemove(LivingEntity target) {
    }

    public void onEffectApplied(Entity applier, Entity caster, LivingEntity target, int amplifier) {

    }

    protected boolean shouldShowParticles() {
        return false;
    }

    protected boolean isAmbient() {
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    public void renderHUDEffect(EffectInstance effect, AbstractGui gui, int x, int y, float z, float alpha) {
        if (getIconTexture() != null) {
            Minecraft.getInstance().getTextureManager().bindTexture(getIconTexture());
            AbstractGui.blit(x + 4, y + 4, 0, 0, 16, 16, 16, 16);
        }
    }

}