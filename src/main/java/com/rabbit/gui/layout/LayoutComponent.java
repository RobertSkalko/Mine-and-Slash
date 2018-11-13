package com.rabbit.gui.layout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Annotated type or field can be used in the gui layout
 */
@SideOnly(Side.CLIENT)
@Retention(RetentionPolicy.RUNTIME)
public @interface LayoutComponent {
}
