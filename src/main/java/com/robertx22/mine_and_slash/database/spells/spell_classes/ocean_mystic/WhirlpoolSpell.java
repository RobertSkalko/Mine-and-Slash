package com.robertx22.mine_and_slash.database.spells.spell_classes.ocean_mystic;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.proj.WhirlpoolEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class WhirlpoolSpell extends BaseSpell {

    public WhirlpoolSpell() {

    }

    public Elements element = Elements.Water;

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.OCEAN_MYSTIC;
    }

    @Override
    public int getCooldownInSeconds() {
        return 20;
    }

    @Override
    public BaseSpell.SpellType getSpellType() {
        return SpellType.LASTING_AOE;
    }

    @Override
    public String GUID() {
        return "whirlpool";
    }

    @Override
    public int getManaCost() {
        return 35;
    }

    @Override
    public int useTimeTicks() {
        return 25;
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.one(dmgStat(), 0.2F, 2);
    }

    @Override
    public Elements getElement() {
        return element;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Summons a whirpool that slows and damages enemies: "));

        list.addAll(getCalculation().GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.Whirpool;
    }

    @Override
    public boolean cast(PlayerEntity caster, int ticksInUse) {
        World world = caster.world;

        WhirlpoolEntity en = SpellUtils.getSpellEntity(new WhirlpoolEntity(world), this, caster);
        SpellUtils.setupProjectileForCasting(en, caster, 1);

        caster.world.addEntity(en);

        return true;
    }

}
