package com.robertx22.mine_and_slash.items.gearitems.weapons;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.entities.weapon_proj.EntityStaffProjectile;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.items.gearitems.bases.BaseWeaponItem;
import com.robertx22.mine_and_slash.items.gearitems.bases.IWeapon;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.offhands.IEffectItem;
import com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics.StaffWeaponMechanic;
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

public class ItemStaff extends BaseWeaponItem implements IWeapon, IEffectItem {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public ItemStaff(int rar) {
        super(rar);
    }

    @Override
    public String locNameForLangFile() {
        Rarity rar = Rarities.Gears.get(rarity);
        return rar.textFormatColor() + "Staff";
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

                float velocity = getArrowVelocity(charge);

                if (velocity > 0.4F) {

                    float multi = 1 + velocity;

                    if (data.tryUseWeapon(weapondata, player, multi)) {

                        Vec3d pos = player.getPositionVector();
                        EntityStaffProjectile en = SpellUtils.getSpellEntity(
                                new EntityStaffProjectile(world), null, player);
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

    public static float getArrowVelocity(int charge) {
        float f = (float) charge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player, Hand handIn) {
        ItemStack itemstack = player.getHeldItem(handIn);
        player.setActiveHand(handIn);
        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }

    @Override
    public WeaponMechanic mechanic() {
        return new StaffWeaponMechanic();
    }

    @Override
    public List<ITextComponent> getEffectTooltip(boolean moreInfo) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(color() + "" + TextFormatting.BOLD + "[Active]: " + "Magic Projectile"));
        if (moreInfo) {
            list.add(new StringTextComponent(color() + "Casts an orb that damages first enemy hit"));
        }
        return list;
    }
}