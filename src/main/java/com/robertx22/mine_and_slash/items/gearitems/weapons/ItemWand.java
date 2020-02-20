package com.robertx22.mine_and_slash.items.gearitems.weapons;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.weapon_proj.EntityWandProjectile;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.items.gearitems.bases.BaseWeaponItem;
import com.robertx22.mine_and_slash.items.gearitems.offhands.IEffectItem;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ItemWand extends BaseWeaponItem implements IEffectItem {

    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ItemWand(int rar) {
        super(rar);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player, Hand handIn) {
        ItemStack itemstack = player.getHeldItem(handIn);
        player.setActiveHand(handIn);
        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 25000;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, LivingEntity player, int timeLeft) {

        try {

            if (!world.isRemote) {

                UnitData data = Load.Unit(player);
                data.tryRecalculateStats(player);
                GearItemData weapondata = data.getWeaponData(player);

                int charge = this.getUseDuration(stack) - timeLeft;

                float velocity = ItemStaff.getArrowVelocity(charge);

                if (velocity > 0.4F) {

                    float multi = 1 + velocity;

                    float costmulti = multi * 1.5F;

                    if (data.tryUseWeapon(weapondata, player, costmulti)) {

                        Vec3d pos = player.getPositionVector();
                        EntityWandProjectile en = SpellUtils.getSpellEntity(
                            new EntityWandProjectile(world), null, player);
                        SpellUtils.setupProjectileForCasting(en, player, 2);
                        en.getSpellData().charge = multi;
                        world.addEntity(en);

                        stack.attemptDamageItem(1, new Random(), (ServerPlayerEntity) player);

                        SoundUtils.playSound((PlayerEntity) player, SoundEvents.ENTITY_SNOWBALL_THROW, 1, 1);

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String locNameForLangFile() {
        Rarity rar = Rarities.Gears.get(rarity);
        return rar.textFormatColor() + "Wand";
    }

    @Override
    public List<ITextComponent> getEffectTooltip(boolean moreInfo) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(color() + "" + TextFormatting.BOLD + "[Active]: " + "Magic Aoe Projectile"));
        if (moreInfo) {
            list.add(new StringTextComponent(color() + "Casts an orb that damages enemies in AOE"));
        }
        return list;
    }

}