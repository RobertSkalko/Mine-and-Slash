package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.NoEnergyPacket;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSpell implements IWeighted, IGUID, ISlashRegistryEntry<BaseSpell>, ITooltipList {

    private SpellConfig config;

    public BaseSpell(SpellConfig config) {
        this.config = config;
    }

    public boolean shouldActivateCooldown(PlayerEntity player, PlayerSpellCap.ISpellsCap spells) {
        return true;
    }

    public final void onCastingTick(SpellCastContext ctx) {

        int timesToCast = (int) ctx.config.timesCasted.getValueFor(ctx);

        if (timesToCast == 1) {
            if (ctx.isLastCastTick) {
                this.cast(ctx);
            }
        } else if (timesToCast > 1) {

            int castTimeTicks = (int) ctx.config.castTimeTicks.getValueFor(ctx);
            // if i didnt do this then cast time reduction would reduce amount of spell hits.
            int castEveryXTicks = castTimeTicks / timesToCast;

            if (ctx.ticksInUse % castEveryXTicks == 0) {
                this.cast(ctx);
            }

        } else {
            System.out.println("Times to cast spell is: " + timesToCast + " . this seems like a bug.");
        }

    }

    public SpellConfig getConfigCopy() {
        return config; // todo if this is serializable, i can just re-serialize it to make copies
    }

    public void spawnParticles(SpellCastContext ctx) {

    }

    public final int getMaxSpellLevelNormal() {
        return config.maxSpellLevel;
    }

    public final int getMaxSpellLevelBuffed() {
        return getMaxSpellLevelNormal() + 5;
    }

    public enum AllowedAsRightClickOn {
        MAGE_WEAPON, MELEE_WEAPON, NONE
    }

    public boolean isAllowedAsRightClickFor(GearItemSlot slot) {
        switch (config.allowedAsRightClickOn) {
            case NONE: {
                return false;
            }
            case MELEE_WEAPON: {
                return slot.isMeleeWeapon();
            }
            case MAGE_WEAPON: {
                return slot.isMageWeapon();
            }
        }
        return false;
    }

    @Override
    public int getRarityRank() {
        return IRarity.Uncommon;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Gears.get(getRarityRank());
    }

    public final boolean goesOnCooldownIfCastCanceled() {
        // override for spells that do oncastingtick
        return config.goesOnCooldownIfCanceled;
    }

    public final ResourceLocation getIcon() {
        return new ResourceLocation(Ref.MODID, "textures/gui/spells/" + getSchool().id + "/" + GUID() + ".png");
    }

    public abstract SpellSchools getSchool();

    public int getCooldownInTicks() {
        return getCooldownInSeconds() * 20;
    }

    public abstract int getCooldownInSeconds();

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.SPELL;
    }

    public abstract String GUID();

    public abstract int getManaCost();

    public final int getCalculatedManaCost(SpellCastContext ctx) {
        return ctx.config.getCalculatedManaCost(this, ctx.data);
    }

    public abstract int useTimeTicks();

    public float getUseDurationInSeconds() {
        return (float) useTimeTicks() / 20;
    }

    public abstract SpellCalcData getCalculation();

    public abstract Elements getElement();

    public BaseSpell() {

    }

    public abstract List<ITextComponent> GetDescription(TooltipInfo info);

    public abstract Words getName();

    public int Weight() {
        return 1000;
    }

    public final boolean cast(SpellCastContext ctx) {
        return ctx.config.castType.cast(ctx);
    }

    public void spendResources(SpellCastContext ctx) {
        ctx.data.getResources()
            .modify(getManaCostCtx(ctx));
    }

    public ResourcesData.Context getManaCostCtx(SpellCastContext ctx) {
        return new ResourcesData.Context(
            ctx.data, ctx.caster, ResourcesData.Type.MANA, ctx.config.getCalculatedManaCost(this, ctx.data), ResourcesData.Use.SPEND);
    }

    public boolean canCast(SpellCastContext ctx) {

        LivingEntity caster = ctx.caster;

        if (caster instanceof PlayerEntity == false) {
            return true;
        }

        PlayerEntity player = (PlayerEntity) caster;

        if (!caster.world.isRemote) {

            UnitData data = Load.Unit(caster);

            if (data != null) {

                ResourcesData.Context rctx = getManaCostCtx(ctx);

                if (data.getResources()
                    .hasEnough(rctx)) {

                    if (this.config.castRequirements.stream()
                        .anyMatch(x -> !x.predicate.test(player))) {
                        return false;
                    }

                    return true;
                } else {
                    if (caster instanceof ServerPlayerEntity) {
                        MMORPG.sendToClient(new NoEnergyPacket(), (ServerPlayerEntity) caster);
                    }

                }
            }
        }
        return false;

    }

    @Override
    public final List<ITextComponent> GetTooltipString(TooltipInfo info) {

        SpellCastContext ctx = new SpellCastContext(info.player, 0, this);

        UnitData data = info.unitdata;

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(TextFormatting.BOLD + "" + getSchool().format).appendSibling(
            getName().locName()));

        TooltipUtils.addEmpty(list);

        list.addAll(GetDescription(info));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent(TextFormatting.BLUE + "Mana Cost: " + getCalculatedManaCost(ctx)));
        list.add(new StringTextComponent(TextFormatting.YELLOW + "Cooldown: " + getCooldownInSeconds() + "s"));
        list.add(new StringTextComponent(TextFormatting.GREEN + "Cast time: " + getUseDurationInSeconds() + "s"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent(getSchool().format + "School: ").appendSibling(getSchool().locName.locName()));

        TooltipUtils.addEmpty(list);

        this.config.castRequirements.forEach(x -> list.add(x.text));

        if (this.config.allowedAsRightClickOn == AllowedAsRightClickOn.MAGE_WEAPON) {
            TooltipUtils.addEmpty(list);
            list.add(new SText(TextFormatting.LIGHT_PURPLE + "Can be set as right click for a Mage Weapon"));
        }

        return list;

    }

}
