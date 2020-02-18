package com.robertx22.mine_and_slash.database.spells.spell_classes.ranger;

import com.robertx22.mine_and_slash.database.spells.entities.proj.RangerArrowEntity;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseProjectileSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.spells.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ArrowBarrageSpell extends BaseProjectileSpell {

    private ArrowBarrageSpell() {
        this.castRequirements.add(BaseSpell.REQUIRE_SHOOTABLE_ITEM);
    }

    public static ArrowBarrageSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public SpellSchools getSchool() {
        return SpellSchools.RANGER;
    }

    @Override
    public int getCooldownInSeconds() {
        return 5;
    }

    @Override
    public BaseSpell.SpellType getSpellType() {
        return SpellType.Single_Target_Projectile;
    }

    @Override
    public float getShootSpeed() {
        return 1.5F;
    }

    @Override
    public AbstractArrowEntity newEntity(World world) {
        return new RangerArrowEntity(world);
    }

    @Override
    public SoundEvent getShootSound() {
        return SoundEvents.ENTITY_ARROW_SHOOT;
    }

    @Override
    public String GUID() {
        return "arrow_barrage";
    }

    @Override
    public int getManaCost() {
        return 20;
    }

    @Override
    public int useTimeTicks() {
        return 10;
    }

    @Override
    public SpellCalcData getCalculation() {
        return SpellCalcData.allAttackAndSpellDamages(0.05F, 0.1F, 1);
    }

    @Override
    public Elements getElement() {
        return Elements.Elemental;
    }

    @Override
    public void onCastingTick(PlayerEntity player, PlayerSpellCap.ISpellsCap spells, int tick) {
        if (tick % 2 == 0) {
            this.cast(player, 0);
        }
    }

    @Override
    public List<ITextComponent> GetDescription(TooltipInfo info) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent("Shoots out many arrows while casting: "));
        list.add(new StringTextComponent("Requires Bow/Crossbow to use: "));

        list.addAll(getCalculation().GetTooltipString(info));

        return list;

    }

    @Override
    public boolean goesOnCooldownIfCastCanceled() {
        return true;
    }

    @Override
    public Words getName() {
        return Words.ArrowBarrage;
    }

    private static class SingletonHolder {
        private static final ArrowBarrageSpell INSTANCE = new ArrowBarrageSpell();
    }
}
