package com.robertx22.mine_and_slash.database.spells.synergies;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.SynergyContext;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltipList;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public abstract class Synergy<T extends SynergyContext> implements ITooltipList, IGUID {

    public boolean has(PlayerEntity player) {
        return Load.spells(player).hasSynergy(this);
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
                new StringTextComponent(TextFormatting.GREEN + "").appendSibling(spellAffected().getName().locName())
                        .appendText(" Synergy"));
    }

}
