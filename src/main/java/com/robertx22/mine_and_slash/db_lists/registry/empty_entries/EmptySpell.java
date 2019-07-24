package com.robertx22.mine_and_slash.db_lists.registry.empty_entries;

import com.robertx22.mine_and_slash.items.spells.projectile.ItemFrostBolt;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.spells.bases.EffectCalculation;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class EmptySpell extends BaseSpell {
    @Override
    public SpellType Type() {
        return SpellType.Self_Heal;
    }

    @Override
    public String GUID() {
        return "";
    }

    @Override
    public int ManaCost() {
        return 0;
    }

    @Override
    public int useTimeTicks() {
        return 0;
    }

    @Override
    public int BaseValue() {
        return 0;
    }

    @Override
    public EffectCalculation ScalingValue() {
        return new EffectCalculation(new EmptyStat(), 1);
    }

    @Override
    public Elements Element() {
        return Elements.Physical;
    }

    @Override
    public Item SpellItem() {
        return ItemFrostBolt.ITEM;
    }

    @Override
    public ITextComponent GetDescription(SpellItemData data) {
        return new StringTextComponent("");
    }

    @Override
    public boolean cast(World world, PlayerEntity caster, Hand hand, int ticksInUse,
                        SpellItemData data) {
        return false;
    }
}
