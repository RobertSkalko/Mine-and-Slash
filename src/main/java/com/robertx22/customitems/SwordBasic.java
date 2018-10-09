package com.robertx22.customitems;

import com.robertx22.utilityclasses.Utils;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemSword;

public class SwordBasic extends ItemSword {

    private ItemArmor.ToolMaterial mat;

    public SwordBasic(ToolMaterial mat) {
        super(mat);
        this.mat = mat;

        this.setMaxStackSize(1);
        this.setCreativeTab(NewItemCreator.MyModTab);

        String name = mat.name() + "_sword";
        this.setRegistryName(name);
        this.setUnlocalizedName(Utils.setName(name));

    }

}
