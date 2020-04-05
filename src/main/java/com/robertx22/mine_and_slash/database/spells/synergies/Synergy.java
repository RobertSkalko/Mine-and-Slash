package com.robertx22.mine_and_slash.database.spells.synergies;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.SynergyContext;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public abstract class Synergy<T extends SynergyContext> implements ITooltipList, IAbility, ISlashRegistryEntry<Synergy> {

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

    public abstract void tryActivate(T ctx, SpellCastContext sc);

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
