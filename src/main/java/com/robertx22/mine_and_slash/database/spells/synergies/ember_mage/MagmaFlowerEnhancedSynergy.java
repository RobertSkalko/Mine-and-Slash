package com.robertx22.mine_and_slash.database.spells.synergies.ember_mage;

import com.robertx22.mine_and_slash.database.spells.blocks.magma_flower.MagmaFlowerTileEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage.MagmaFlowerSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.Synergy;
import com.robertx22.mine_and_slash.database.spells.synergies.ctx.CasterAndSpellEntityContext;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class MagmaFlowerEnhancedSynergy extends Synergy<CasterAndSpellEntityContext<MagmaFlowerTileEntity>> {

    @Override
    public String GUID() {
        return "magma_flower_enhanced_synergy";
    }

    @Override
    public List<ITextComponent> GetTooltipString(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Doubles the tickrate and the radius."));

        return list;
    }

    @Override

    public BaseSpell spellAffected() {
        return MagmaFlowerSpell.getInstance();
    }

    @Override
    public void tryActivate(CasterAndSpellEntityContext<MagmaFlowerTileEntity> ctx) {

        ctx.spellEntity.RADIUS *= 2;
        ctx.spellEntity.TICK_RATE /= 2;

    }
}
