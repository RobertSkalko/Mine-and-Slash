package com.robertx22.mine_and_slash.database.spells.spell_classes.druid;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpellHeal;
import com.robertx22.mine_and_slash.potion_effects.bases.PotionEffectUtils;
import com.robertx22.mine_and_slash.potion_effects.druid.ThornArmorEffect;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ThornArmorSpell extends BaseSpellHeal {

    @Override
    public int useTimeTicks() {
        return 10;
    }

    @Override
    public String GUID() {
        return "thorn_armor";
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.DRUID;
    }

    @Override
    public int getCooldownInSeconds() {
        return 45;
    }

    @Override
    public int getManaCost() {
        return 30;
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.empty();
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Applies buff: "));

        list.addAll(ThornArmorEffect.INSTANCE.GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.ThornArmor;
    }

    @Override
    public boolean cast(PlayerEntity caster, int ticksInUse) {
        try {
            World world = caster.world;

            if (!world.isRemote) {

                SoundUtils.playSound(caster, SoundEvents.ENTITY_WITCH_DRINK, 1, 1);

                PotionEffectUtils.applyToSelf(ThornArmorEffect.INSTANCE, caster);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

}
