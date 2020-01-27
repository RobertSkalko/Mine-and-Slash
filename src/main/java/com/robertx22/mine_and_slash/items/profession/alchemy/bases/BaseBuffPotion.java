package com.robertx22.mine_and_slash.items.profession.alchemy.bases;

import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.potion_effects.alchemy_pot_buffs.BaseAlchemyEffect;
import com.robertx22.mine_and_slash.potion_effects.alchemy_pot_buffs.BaseEffect;
import com.robertx22.mine_and_slash.professions.blocks.bases.Professions;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseBuffPotion extends BasePotion {

    public BaseBuffPotion(Professions.Levels lvl) {
        super(lvl);

    }

    public int durationInMinutes() {
        return (int) (level.effectMultiplier * 10);
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Alchemy;
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }

    public int useDurationInTicks() {
        return 50;
    }

    public BaseEffect createEffect() {
        return new BaseAlchemyEffect(this);
    }

    public BaseEffect getEffect() {
        return (BaseEffect) ForgeRegistries.POTIONS.getValue(new ResourceLocation(Ref.MODID, GUID()));
    }

    public void onFinish(ItemStack stack, World world, LivingEntity player, EntityCap.UnitData unitdata) {

        player.getActivePotionEffects()
                .stream()
                .filter(x -> x.getPotion() instanceof BaseAlchemyEffect)
                .findFirst()
                .ifPresent(x -> player.removePotionEffect(x.getPotion()));

        EffectInstance instance = new EffectInstance(getEffect(), durationInMinutes() * 20 * 60, 0, false, false);
        player.addPotionEffect(instance);

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
                               ITooltipFlag flagIn) {

        TooltipInfo info = new TooltipInfo(null, new MinMax(100, 100), level.number);
        info.usePrettyStatSymbols = true;

        tooltip.add(Styles.GREENCOMP().appendText("Stats: "));

        for (StatModData mod : mods()) {
            tooltip.addAll(mod.GetTooltipString(info));
        }

        TooltipUtils.addEmpty(tooltip);

        tooltip.add(Styles.BLUECOMP().appendText("Duration: " + durationInMinutes() + " Minutes"));
        tooltip.add(TooltipUtils.level(this.level.number));

    }

    public abstract List<StatModData> mods();

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return useDurationInTicks();
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity player) {

        onFinish(stack, world, player, Load.Unit(player));
        stack.shrink(1);

        if (player instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity) player;
            p.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
        }

        return stack;
    }

    public boolean hasAlchemyPotion(LivingEntity en) {

        return en.getActivePotionEffects().stream().anyMatch(x -> x.getPotion() instanceof BaseAlchemyEffect);

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player, Hand handIn) {
        ItemStack itemstack = player.getHeldItem(handIn);

        if (Load.Unit(player).getLevel() >= this.level.number) {
            player.setActiveHand(handIn);
            return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
        } else {
            return new ActionResult<>(ActionResultType.FAIL, itemstack);
        }
    }

}

