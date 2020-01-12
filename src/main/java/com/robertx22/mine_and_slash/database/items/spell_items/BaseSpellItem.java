package com.robertx22.mine_and_slash.database.items.spell_items;

import com.robertx22.mine_and_slash.database.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.items.gearitems.bases.MyForgeItem;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Spell;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ItemUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RepairUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
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

public abstract class BaseSpellItem extends Item implements IAutoLocName, MyForgeItem {

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

        Properties prop = ItemUtils.getDefaultGearProperties()
                .maxStackSize(0)
                .defaultMaxDamage(500);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            //  prop.setTEISR(TomeRenderer::new);
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
        return Spell().useTimeTicks();
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn,
                                     LivingEntity playerIn) {

        if (playerIn instanceof PlayerEntity) {
            try {
                SpellItemData data = Spell.Load(stack);

                if (Spell().CanCast((PlayerEntity) playerIn, data)) {
                    Spell().cast(worldIn, (PlayerEntity) playerIn, playerIn.getActiveHand(), 5, data);

                    stack.damageItem(1, playerIn, (entity) -> {
                        if (entity != null) {
                            entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
                        }
                    });

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

        ItemStack stack = player.getHeldItem(handIn);

        if (RepairUtils.isItemBroken(stack)) {
            return new ActionResult<>(ActionResultType.FAIL, stack);
        }

        player.setActiveHand(handIn);
        return new ActionResult<>(ActionResultType.SUCCESS, stack);
    }

}
