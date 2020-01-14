package com.robertx22.mine_and_slash.database.spells.spell_classes;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.blizzard.ElementStormCloudEntity;
import com.robertx22.mine_and_slash.database.spells.items.ItemStormCloud;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.EffectCalculation;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellEffectDamage;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalSpellDamage;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpellStormCloud extends BaseSpell implements IGenerated<BaseSpell> {

    HashMap<Elements, SpellStormCloud> MAP = new HashMap<>();

    public SpellStormCloud(Elements element) {
        this.element = element;
    }

    public Elements element = Elements.Physical;

    @Override
    public BaseSpell.SpellType getSpellType() {
        return SpellType.LASTING_AOE;
    }

    @Override
    public String GUID() {
        return element.dmgName.toLowerCase() + "storm_cloud";
    }

    @Override
    public int getManaCost() {
        return 80;
    }

    @Override
    public int useTimeTicks() {
        return 20;
    }

    @Override
    public int getBaseValue() {
        return 5;
    }

    @Override
    public EffectCalculation ScalingValue() {
        return new EffectCalculation(new ElementalSpellDamage(element), 0.5F);
    }

    @Override
    public Elements getElement() {
        return element;
    }

    @Override
    public Item getSpellItem() {
        return ItemStormCloud.MAP.get(element);
    }

    @Override
    public ITextComponent GetDescription(SpellItemData data) {
        return Words.StormCloudSpellDesc.locName();
    }

    @Override
    public boolean cast(World world, PlayerEntity caster, Hand hand, int ticksInUse,
                        SpellItemData data) {

        RayTraceResult ray = caster.pick(10D, 0.0F, false);

        Vec3d pos = ray.getHitVec();

        Entity en = SpellUtils.getSpellEntity(new ElementStormCloudEntity(world), new SpellEffectDamage(getElement()), data, caster);

        en.setPosition(pos.x, pos.y, pos.z);

        world.addEntity(en);

        return true;
    }

    @Override
    public List<BaseSpell> generateAllPossibleStatVariations() {
        Elements.getAllSingleElements().forEach(x -> MAP.put(x, new SpellStormCloud(x)));
        return new ArrayList<>(MAP.values());
    }
}
