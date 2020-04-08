package com.robertx22.mine_and_slash.potion_effects.bases;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
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

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public abstract class BasePotionEffect extends Effect implements ISlashRegistryEntry<BasePotionEffect>, IAutoLocName, ITooltipList, IAbility {

    public SpellCalcData getCalc(PlayerSpellCap.ISpellsCap cap) {
        PreCalcSpellConfigs p = getPreCalcConfig();
        return p.getCalc(cap, getAbilityThatDeterminesLevel());
    }

    @Override
    public ResourceLocation getIconLoc() {
        return getIconTexture();
    }

    @Override
    public Type getAbilityType() {
        return Type.EFFECT;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.EFFECT;
    }

    @Override
    public int getMaxSpellLevelNormal() {
        return 8;
    }

    @Override
    public int getMaxSpellLevelBuffed() {
        return getMaxSpellLevelNormal() + 5;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return null;
    }

    public IAbility getAbilityThatDeterminesLevel() {
        if (getSpell() != null) {
            return getSpell();
        }
        return this;
    }

    @Override
    public IAbility getRequiredAbility() {
        return null;
    }

    protected List<OnTickAction> tickActions = new ArrayList<>();

    public int getMaxStacks() {
        return 1;
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Potions;
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName()
            .toString();
    }

    public abstract String GUID();

    @Override
    public ITextComponent locName() {
        return CLOC.blank("effect." + Ref.MODID + "." + GUID());
    }

    //public abstract List<ITextComponent> getEffectTooltip(TooltipInfo info);

    public List<ITextComponent> getEffectTooltip(TooltipInfo info) {
        return new ArrayList<>();
    }

    @Override
    public final List<ITextComponent> GetTooltipString(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.LIGHT_PURPLE + "" + TextFormatting.BOLD).appendSibling(
            locName()));

        list.addAll(getEffectTooltip(info));

        if (this instanceof IApplyStatPotion) {
            list.addAll(((IApplyStatPotion) this).getStatTooltip(info, this));
        }

        list.add(new SText(""));

        tickActions.forEach(x -> list.addAll(x.getTooltip(info)));

        list.addAll(getMaxStacksTooltip());
        list.addAll(getDurationTooltip());

        list.add(new SText(""));

        TooltipUtils.abilityLevel(list, Load.spells(info.player)
            .getLevelOf(this), getMaxSpellLevelNormal());

        // list = TooltipUtils.removeDoubleBlankLines(list, 0);

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

    @Override
    public void performEffect(LivingEntity en, int amplifier) {

        try {

            boolean delete = false;

            for (OnTickAction x : this.tickActions) {
                if (en.ticksExisted % x.eachXticks == 0) {

                    EffectInstance instance = en.getActivePotionEffect(this);

                    if (instance == null) {
                        //Log.error("potion instance is null, Deleting potion");
                        delete = true;
                        return;
                    }

                    ExtraPotionData data = PotionDataSaving.getData(instance);

                    if (data == null) {
                        //Log.error("Extra potion data is null. Deleting potion");
                        delete = true;
                        return;
                    }

                    LivingEntity caster = data.getCaster(en.world);

                    if (caster == null) {
                        //Log.error("Potion can't find caster. Deleting potion");
                        delete = true;
                        return;
                    }

                    x.onTick(new PotionContext(en, data, caster));
                }
            }

            if (delete) {
                en.removePotionEffect(this);
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
            Minecraft.getInstance()
                .getTextureManager()
                .bindTexture(getIconTexture());
            AbstractGui.blit(x + 4, y + 4, 0, 0, 16, 16, 16, 16);
        }
    }

}