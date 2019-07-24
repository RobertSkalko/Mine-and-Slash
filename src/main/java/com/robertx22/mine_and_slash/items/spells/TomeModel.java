package com.robertx22.mine_and_slash.items.spells;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TomeModel extends Model {
    private final RendererModel field_78102_a = (new RendererModel(this)).setTextureOffset(0, 0)
            .addBox(-6.0F, -5.0F, 0.0F, 6, 10, 0);
    private final RendererModel field_78100_b = (new RendererModel(this)).setTextureOffset(16, 0)
            .addBox(0.0F, -5.0F, 0.0F, 6, 10, 0);
    private final RendererModel field_78101_c = (new RendererModel(this)).setTextureOffset(0, 10)
            .addBox(0.0F, -4.0F, -0.99F, 5, 8, 1);
    private final RendererModel field_78098_d = (new RendererModel(this)).setTextureOffset(12, 10)
            .addBox(0.0F, -4.0F, -0.01F, 5, 8, 1);
    private final RendererModel field_78099_e = (new RendererModel(this)).setTextureOffset(24, 10)
            .addBox(0.0F, -4.0F, 0.0F, 5, 8, 0);
    private final RendererModel field_78096_f = (new RendererModel(this)).setTextureOffset(24, 10)
            .addBox(0.0F, -4.0F, 0.0F, 5, 8, 0);
    private final RendererModel field_78097_g = (new RendererModel(this)).setTextureOffset(12, 0)
            .addBox(-1.0F, -5.0F, 0.0F, 2, 10, 0);

    public TomeModel() {
        this.field_78102_a.setRotationPoint(0.0F, 0.0F, -1.0F);
        this.field_78100_b.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.field_78097_g.rotateAngleY = 1.5707964F;
    }

    public void render(float p_217103_1_, float p_217103_2_, float p_217103_3_,
                       float openPercent, float p_217103_5_, float p_217103_6_) {
        this.func_217102_b(p_217103_1_, p_217103_2_, p_217103_3_, openPercent, p_217103_5_, p_217103_6_);
        this.field_78102_a.render(p_217103_6_);
        this.field_78100_b.render(p_217103_6_);
        this.field_78097_g.render(p_217103_6_);
        this.field_78101_c.render(p_217103_6_);
        this.field_78098_d.render(p_217103_6_);
        this.field_78099_e.render(p_217103_6_);
        this.field_78096_f.render(p_217103_6_);
    }

    private void func_217102_b(float p_217102_1_, float p_217102_2_, float p_217102_3_,
                               float openPercent, float p_217102_5_, float p_217102_6_) {
        float lvt_7_1_ = (MathHelper.sin(p_217102_1_ * 0.02F) * 0.1F + 1.25F) * openPercent;
        this.field_78102_a.rotateAngleY = 3.1415927F + lvt_7_1_;
        this.field_78100_b.rotateAngleY = -lvt_7_1_;
        this.field_78101_c.rotateAngleY = lvt_7_1_;
        this.field_78098_d.rotateAngleY = -lvt_7_1_;
        this.field_78099_e.rotateAngleY = lvt_7_1_ - lvt_7_1_ * 2.0F * p_217102_2_;
        this.field_78096_f.rotateAngleY = lvt_7_1_ - lvt_7_1_ * 2.0F * p_217102_3_;
        this.field_78101_c.rotationPointX = MathHelper.sin(lvt_7_1_);
        this.field_78098_d.rotationPointX = MathHelper.sin(lvt_7_1_);
        this.field_78099_e.rotationPointX = MathHelper.sin(lvt_7_1_);
        this.field_78096_f.rotationPointX = MathHelper.sin(lvt_7_1_);
    }
}
