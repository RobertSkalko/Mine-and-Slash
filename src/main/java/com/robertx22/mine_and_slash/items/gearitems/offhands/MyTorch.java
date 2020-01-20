package com.robertx22.mine_and_slash.items.gearitems.offhands;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGearItem;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ItemUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTorch extends Item implements IEffectItem, IAutoLocName, IGearItem {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public MyTorch(int rar) {
        super(ItemUtils.getDefaultGearProperties()
                .maxStackSize(1)
                .defaultMaxDamage(1000));
        this.rarity = rar;
    }

    public int rarity = 0;

    @Override
    public String locNameForLangFile() {
        Rarity rar = Rarities.Items.get(rarity);
        return rar.textFormatColor() + "Torch";
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Gear_Items;
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }

    @Override
    public String GUID() {
        return "";
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 40;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player,
                                                    Hand handIn) {
        ItemStack itemstack = player.getHeldItem(handIn);

        player.setActiveHand(handIn);
        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn,
                                     LivingEntity player) {

        if (worldIn.isRemote == false) {

            EntityCap.UnitData data = Load.Unit(player);

            float manarestored = restoreBasedOnMissing(data.getResources().getMana(), data
                    .getUnit()
                    .manaData().val);

            float energyrestored = restoreBasedOnMissing(data.getResources()
                    .getEnergy(), data.getUnit().energyData().val);

            ResourcesData.Context mana = new ResourcesData.Context(data, player, ResourcesData.Type.MANA, manarestored, ResourcesData.Use.RESTORE);
            ResourcesData.Context ene = new ResourcesData.Context(data, player, ResourcesData.Type.ENERGY, energyrestored, ResourcesData.Use.RESTORE);

            boolean restored = false;

            if (manarestored > energyrestored) {
                if (manarestored > 0) {
                    data.modifyResource(mana);
                    restored = true;
                }
            } else {
                if (energyrestored > 0) {
                    data.modifyResource(ene);
                    restored = true;
                }
            }

            if (restored) {
                player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 3));
                player.addPotionEffect(new EffectInstance(Effects.HUNGER, 350, 2));
            }

        } else {
            ParticleUtils.spawnEnergyRestoreParticles(player, 4);
            ParticleUtils.spawnManaRestoreParticles(player, 4);
            player.playSound(SoundEvents.AMBIENT_UNDERWATER_ENTER, 0.3F, 0);
        }

        return stack;
    }

    private float restoreBasedOnMissing(float current, float max) {

        float missing = max - current;

        if (missing > 20) {
            missing /= 10;
            return missing;
        }
        return 0;

    }

    @Override
    public List<ITextComponent> getEffectTooltip(boolean moreInfo) {

        List<ITextComponent> list = new ArrayList<>();

        list.add(new StringTextComponent(color() + "" + TextFormatting.BOLD + "[Active]: " + TextFormatting.RESET + color() + "Restoration"));
        if (moreInfo) {
            list.add(new StringTextComponent(color() + "Restores Mana/Energy Based on Missing Amount"));
        }
        return list;
    }
}