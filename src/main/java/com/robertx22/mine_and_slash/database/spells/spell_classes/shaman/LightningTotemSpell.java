package com.robertx22.mine_and_slash.database.spells.spell_classes.shaman;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.proj.LightningTotemEntity;
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

public class LightningTotemSpell extends BaseSpell {

    public LightningTotemSpell() {

    }

    public Elements element = Elements.Thunder;

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.SHAMAN;
    }

    @Override
    public int getCooldownInSeconds() {
        return 10;
    }

    @Override
    public BaseSpell.SpellType getSpellType() {
        return SpellType.LASTING_AOE;
    }

    @Override
    public String GUID() {
        return "lightning_totem";
    }

    @Override
    public int getManaCost() {
        return 25;
    }

    @Override
    public int useTimeTicks() {
        return 10;
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.one(dmgStat(), 0.1F, 2);
    }

    @Override
    public Elements getElement() {
        return element;
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Summons a totem that damages enemies: "));

        list.addAll(getCalculation().GetTooltipString(info));

        return list;

    }

    @Override
    public Words getName() {
        return Words.LightningTotem;
    }

    @Override
    public boolean cast(PlayerEntity caster, int ticksInUse) {
        World world = caster.world;

        LightningTotemEntity en = SpellUtils.getSpellEntity(new LightningTotemEntity(world), this, caster);
        SpellUtils.setupProjectileForCasting(en, caster, 1);

        caster.world.addEntity(en);

        return true;
    }

}
