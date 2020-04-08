package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.synergies.OnSpellCastSynergy;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;
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
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
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
import java.util.stream.Collectors;

public abstract class BaseSpell implements ISlashRegistryEntry<BaseSpell>, ITooltipList, IAbility {

    private final ImmutableSpellConfigs immutableConfigs;

    public BaseSpell(ImmutableSpellConfigs immutable) {
        this.immutableConfigs = immutable;
    }

    /*
    private List<String> synergies = new ArrayList<>();

    public void addSynergy(Synergy syn) {

        if (syn.getSpell() == null || syn.getSpell()
            .GUID()
            .equals(GUID()) == false) {
            throw new RuntimeException("Wrong synergy for spell!: " + this.GUID() + ":" + syn.GUID());
        }
        this.synergies.add(syn.GUID());
    }

     */

    public final ImmutableSpellConfigs getImmutableConfigs() {
        return immutableConfigs;
    }

    public final void onCastingTick(SpellCastContext ctx) {

        int timesToCast = (int) ctx.getConfigFor(this)
            .get(SC.TIMES_TO_CAST)
            .get(ctx.spellsCap, this);

        if (timesToCast == 1) {
            if (ctx.isLastCastTick) {
                this.cast(ctx);
            }
        } else if (timesToCast > 1) {

            int castTimeTicks = (int) ctx.getConfigFor(this)
                .get(SC.CAST_TIME_TICKS)
                .get(ctx.spellsCap, this);

            // if i didnt do this then cast time reduction would reduce amount of spell hits.
            int castEveryXTicks = castTimeTicks / timesToCast;

            if (ctx.ticksInUse % castEveryXTicks == 0) {
                this.cast(ctx);
            }

        } else {
            System.out.println("Times to cast spell is: " + timesToCast + " . this seems like a bug.");
        }

    }

    public void spawnParticles(SpellCastContext ctx) {

    }

    public final List<Synergy> getAllocatedSynergies(PlayerSpellCap.ISpellsCap cap) {

        /*
        List<Synergy> list = new ArrayList<>();

        this.synergies.forEach(x -> {

            Synergy syn = SlashRegistry.Synergies()
                .get(x);
            if (syn != null) {
                if (cap.getLevelOf(syn) > 0) {
                    list.add(syn);
                }
            }
        });

        return list;

         */

        return cap.getAbilitiesData()
            .getAllocatedSynergies()
            .stream()
            .filter(x -> x.getRequiredAbility()
                .GUID()
                .equals(this.GUID()))
            .collect(Collectors.toList());
    }

    @Override
    public BaseSpell getSpell() {
        return this;
    }

    public final int getMaxSpellLevelNormal() {
        return getPreCalcConfig().maxSpellLevel;
    }

    public final int getMaxSpellLevelBuffed() {
        return getMaxSpellLevelNormal() + 5;
    }

    public boolean shouldActivateCooldown(PlayerEntity player, PlayerSpellCap.ISpellsCap spells) {
        return true;
    }

    public enum AllowedAsRightClickOn {
        MAGE_WEAPON, MELEE_WEAPON, NONE
    }

    @Override
    public Type getAbilityType() {
        return Type.SPELL;
    }

    @Override
    public IAbility getRequiredAbility() {
        return null;
    }

    public boolean isAllowedAsRightClickFor(GearItemSlot slot) {
        switch (immutableConfigs.allowedAsRightClickOn()) {
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
        return immutableConfigs.goesOnCooldownIfCanceled();
    }

    @Override
    public final ResourceLocation getIconLoc() {
        return new ResourceLocation(Ref.MODID, "textures/gui/spells/" + getSchool().id + "/" + GUID() + ".png");
    }

    @Override
    public final SpellSchools getSchool() {
        return immutableConfigs.school();
    }

    public int getCooldownInTicks(SpellCastContext ctx) {
        int ticks = (int) ctx.getConfigFor(this)
            .get(SC.COOLDOWN_TICKS)
            .get(ctx.spellsCap, this);

        int seconds = (int) ctx.getConfigFor(this)
            .get(SC.COOLDOWN_SECONDS)
            .get(ctx.spellsCap, this);

        return (int) ((seconds * 20F) + ticks);
    }

    public final int getCooldownInSeconds(SpellCastContext ctx) {
        return getCooldownInTicks(ctx) / 20;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.SPELL;
    }

    public abstract String GUID();

    public final int getCalculatedManaCost(SpellCastContext ctx) {
        return (int) Mana.getInstance()
            .calculateScalingStatGrowth((int) ctx.getConfigFor(this)
                .get(SC.MANA_COST)
                .get(ctx.spellsCap, this), ctx.data.getLevel());
    }

    public final int useTimeTicks(SpellCastContext ctx) {
        return (int) ctx.getConfigFor(this)
            .get(SC.CAST_TIME_TICKS)
            .get(ctx.spellsCap, this);
    }

    public final float getUseDurationInSeconds(SpellCastContext ctx) {
        return (float) useTimeTicks(ctx) / 20;
    }

    public final SpellCalcData getCalculation(SpellCastContext ctx) {
        return ctx.getConfigFor(this)
            .getCalc(ctx.spellsCap, this);
    }

    public final Elements getElement() {
        return this.immutableConfigs.element();
    }

    public abstract List<ITextComponent> GetDescription(TooltipInfo info, SpellCastContext ctx);

    public abstract Words getName();

    public final boolean cast(SpellCastContext ctx) {
        boolean bool = immutableConfigs.castType()
            .cast(ctx);

        getAllocatedSynergies(ctx.spellsCap).forEach(x -> {
            if (x instanceof OnSpellCastSynergy) {
                OnSpellCastSynergy s = (OnSpellCastSynergy) x;
                s.tryActivate(ctx);
            }
        });

        castExtra(ctx);
        return bool;
    }

    public void castExtra(SpellCastContext ctx) {

    }

    public void spendResources(SpellCastContext ctx) {
        ctx.data.getResources()
            .modify(getManaCostCtx(ctx));
    }

    public ResourcesData.Context getManaCostCtx(SpellCastContext ctx) {

        float cost = 0;

        for (Synergy x : getAllocatedSynergies(ctx.spellsCap)) {
            if (ctx.getConfigFor(x)
                .has(SC.MANA_COST)) {
                cost += ctx.getConfigFor(x)
                    .get(SC.MANA_COST)
                    .get(ctx.spellsCap, x);
            }
        }

        cost += this.getCalculatedManaCost(ctx);

        return new ResourcesData.Context(
            ctx.data, ctx.caster, ResourcesData.Type.MANA, cost, ResourcesData.Use.SPEND);
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

                    if (immutableConfigs.castRequirements()
                        .stream()
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

        list.addAll(GetDescription(info, ctx));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent(TextFormatting.BLUE + "Mana Cost: " + getCalculatedManaCost(ctx)));
        list.add(new StringTextComponent(TextFormatting.YELLOW + "Cooldown: " + getCooldownInSeconds(ctx) + "s"));
        list.add(new StringTextComponent(TextFormatting.GREEN + "Cast time: " + getUseDurationInSeconds(ctx) + "s"));

        TooltipUtils.addEmpty(list);

        list.add(new StringTextComponent(getSchool().format + "School: ").appendSibling(getSchool().locName.locName()));

        TooltipUtils.addEmpty(list);

        this.immutableConfigs.castRequirements()
            .forEach(x -> list.add(x.text));

        if (this.immutableConfigs.allowedAsRightClickOn() == AllowedAsRightClickOn.MAGE_WEAPON) {
            TooltipUtils.addEmpty(list);
            list.add(new SText(TextFormatting.LIGHT_PURPLE + "Can be set as right click for a Mage Weapon"));
        }
        TooltipUtils.addEmpty(list);

        TooltipUtils.abilityLevel(list, ctx.spellsCap.getLevelOf(this), getMaxSpellLevelNormal());

        return list;

    }

}
