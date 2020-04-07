package com.robertx22.mine_and_slash.database.spells.synergies;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SynergyDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public abstract class Synergy implements ITooltipList, IAbility, ISlashRegistryEntry<Synergy> {

    public boolean has(LivingEntity en) {

        if (en instanceof PlayerEntity) {
            return Load.spells((PlayerEntity) en)
                .hasSynergy(this);
        } else {
            if (Load.isBoss(en)) {
                return Load.boss(en)
                    .hasSynergy(this);
            }
        }
        return false;
    }

    public SynergyDamageEffect getSynergyDamage(SpellDamageEffect ctx, int num) {
        SynergyDamageEffect dmg = new SynergyDamageEffect(this,
            ctx.source, ctx.target, num, ctx.sourceData, ctx.targetData, ctx.getSpell());
        dmg.element = ctx.getSpell()
            .getElement();
        return dmg;

    }

    public SynergyDamageEffect getSynergyDamage(SpellDamageEffect ctx, LivingEntity target, int num) {
        SynergyDamageEffect dmg = new SynergyDamageEffect(this,
            ctx.source, target, num, ctx.sourceData, Load.Unit(target), ctx.getSpell());
        dmg.element = ctx.getSpell()
            .getElement();
        return dmg;

    }

    public SpellCastContext getContext(LivingEntity en) {
        return new SpellCastContext(en, 0, this);

    }

    public SpellCalcData getCalc(PlayerSpellCap.ISpellsCap cap) {
        return getPreCalcConfig().getCalc(cap, this);
    }

    @Override
    public final Type getAbilityType() {
        return Type.SYNERGY;
    }

    @Override
    public final ResourceLocation getIconLoc() {
        return new ResourceLocation(Ref.MODID, "textures/gui/spells/synergies/" + GUID() + ".png");
    }

    @Override
    public BaseSpell getSpell() {
        return (BaseSpell) getRequiredAbility();
    }

    // like increase mana cost, reduce cooldown etc
    public abstract PreCalcSpellConfigs getConfigsAffectingSpell();

    @Override
    public final SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.SPELL_SYNERGY;
    }

    @Override
    public int getMaxSpellLevelNormal() {
        return 8;
    }

    @Override
    public final int getMaxSpellLevelBuffed() {
        return getMaxSpellLevelNormal() + 5;
    }

    //public abstract void tryActivate(T ctx);  make it so each one has different

    public boolean has(PlayerSpellCap.ISpellsCap spells) {
        return spells.hasSynergy(this);
    }

    @Override
    public final SpellSchools getSchool() {
        return getRequiredAbility().getSchool();
    }

    public void addSpellName(List<ITextComponent> tooltip) {
        tooltip.add(
            new StringTextComponent(TextFormatting.GREEN + "").appendSibling(((BaseSpell) getRequiredAbility()).getName()
                .locName())
                .appendText(" Synergy"));
    }

}
