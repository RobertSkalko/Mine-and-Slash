package com.robertx22.mine_and_slash.database.spells.synergies.divine;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.SC;
import com.robertx22.mine_and_slash.database.spells.spell_classes.divine.HolyFlowerSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.OnHealedSynergy;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellHealEffect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HolyFlowerCleanseSynergy extends OnHealedSynergy {

    @Override
    public List<ITextComponent> getSynergyTooltipInternal(TooltipInfo info) {
        List<ITextComponent> list = new ArrayList<>();

        addSpellName(list);

        list.add(new StringTextComponent("Cleanses 1 negative effect with each heal."));

        return list;
    }

    @Override
    public void alterSpell(PreCalcSpellConfigs c) {
        c.set(SC.MANA_COST, 1, 1);
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();
        return c;
    }

    @Override
    public int getMaxSpellLevelNormal() {
        return 1;
    }

    @Override
    public int getMaxSpellLevelBuffed() {
        return 1;
    }

    @Override
    public void tryActivate(SpellHealEffect ctx) {

        try {
            Optional<EffectInstance> effect = ctx.target.getActivePotionEffects()
                .stream()
                .filter(x -> x.getPotion()
                    .getEffectType()
                    .equals(EffectType.HARMFUL))
                .findFirst();

            if (effect.isPresent()) {
                ctx.target.removePotionEffect(effect.get()
                    .getPotion());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Place getSynergyPlace() {
        return Place.FIRST;
    }

    @Override
    public IAbility getRequiredAbility() {
        return HolyFlowerSpell.getInstance();
    }

    @Override
    public String locNameForLangFile() {
        return "Cleansing Formation";
    }

}
