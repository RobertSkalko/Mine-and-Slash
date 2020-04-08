package com.robertx22.mine_and_slash.database.spells.synergies.ember_mage;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ember_mage.MagmaFlowerSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.OnDamageDoneSynergy;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.AbilityPlace;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellDamageEffect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MagmaFlowerHealSynergy extends OnDamageDoneSynergy {

    @Override
    public String GUID() {
        return "magma_flower_heal_synergy";
    }

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Heals the caster"));

        list.addAll(getCalc(Load.spells(info.player))
            .GetTooltipString(info));

        return list;
    }

    @Override
    public PreCalcSpellConfigs getConfigsAffectingSpell() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.MANA_COST, 1, 3);
        return c;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        c.set(SC.BASE_VALUE, 1, 6);
        c.setMaxLevel(8);
        return c;
    }

    @Override
    public AbilityPlace getAbilityPlace() {
        return AbilityPlace.upFrom(new MagmaFlowerEnhancedSynergy());
    }

    @Nullable
    @Override
    public IAbility getRequiredAbility() {
        return MagmaFlowerSpell.getInstance();
    }

    @Override
    public void tryActivate(SpellDamageEffect effect) {

        float amount = getCalc(Load.spells(effect.source)).getCalculatedValue(effect.sourceData);

        SpellUtils.heal(getSpell(), effect.source, amount);

    }
}
