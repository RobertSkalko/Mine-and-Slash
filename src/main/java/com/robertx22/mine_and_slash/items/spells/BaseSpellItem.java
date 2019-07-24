package com.robertx22.mine_and_slash.items.spells;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.uncommon.datasaving.Spell;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public abstract class BaseSpellItem extends Item implements IAutoLocName {

    public abstract String GUID();

    public TextFormatting color = TextFormatting.RED;

    public abstract BaseSpell Spell();

    public Elements element;

    public ResourceLocation texture = null;

    public BaseSpellItem(Elements element) {
        super(getSpellItemProp());
        this.element = element;
        setupTexture();
        this.setRegistryName(GUID().toLowerCase());
    }

    public BaseSpellItem() {
        super(getSpellItemProp());
        setupTexture();
        this.setRegistryName(GUID().toLowerCase());

    }

    private void setupTexture() {
        texture = new ResourceLocation(Ref.MODID, "textures/tomes/" + this.Spell()
                .Element()
                .name()
                .toLowerCase() + ".png");
    }

    public static Item.Properties getSpellItemProp() {

        Properties prop = new Properties().maxStackSize(0).defaultMaxDamage(0);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            prop.setTEISR(TomeRenderer::new);
        });

        return prop;
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Spells;
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    @Override
    final public int getUseDuration(ItemStack stack) {
        if (ModConfig.INSTANCE.Server.USE_ATTACK_COOLDOWN.get()) {
            return Spell().useTimeTicks();
        } else {
            return 1;
        }
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn,
                                     LivingEntity playerIn) {

        if (playerIn instanceof PlayerEntity) {
            try {
                SpellItemData data = Spell.Load(stack);

                if (Spell().CanCast((PlayerEntity) playerIn, data)) {
                    Spell().cast(worldIn, (PlayerEntity) playerIn, playerIn.getActiveHand(), 5, data);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return stack;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player,
                                                    Hand handIn) {
        ItemStack itemstack = player.getHeldItem(handIn);
        player.setActiveHand(handIn);
        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }

}
