package com.robertx22.mine_and_slash.database.spells.synergies;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.level_based_numbers.LevelBased;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.SynergyContext;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public abstract class Synergy<T extends SynergyContext> implements ITooltipList, IAbility {

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

    public final SpellCalcData getCalc(EntityCap.UnitData data, PlayerSpellCap.ISpellsCap spells) {

        int abilityLvl = spells.getLevelOf(this);

        return SpellCalcData.scaleWithAttack(
            attackScalingValue().getValueFor(abilityLvl, this),
            baseValue().getValueFor(abilityLvl, this)
        );

    }

    public abstract LevelBased baseValue();

    public LevelBased attackScalingValue() {
        return new LevelBased(0, 0);
    }

    public abstract BaseSpell spellAffected();

    public ResourceLocation getIconLoc() {
        return new ResourceLocation(Ref.MODID, "textures/gui/spells/synergies/" + GUID() + ".png");

    }

    public abstract void tryActivate(T ctx);

    public boolean has(PlayerSpellCap.ISpellsCap spells) {
        return spells.hasSynergy(this);
    }

    public void addSpellName(List<ITextComponent> tooltip) {
        tooltip.add(
            new StringTextComponent(TextFormatting.GREEN + "").appendSibling(spellAffected().getName()
                .locName())
                .appendText(" Synergy"));
    }

}
