package net.kunmc.lab.boobsmod;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;

import java.util.List;
import java.util.Random;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelPlaneRenderer;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;

@OnlyIn(Dist.CLIENT)
public class PlayerModel2<T extends LivingEntity> extends PlayerModel<T> {
    private List<ModelPlaneRenderer> ModelPlaneRenderers = Lists.newArrayList();
    public ModelPlaneRenderer boobsR;
    public BipedModel Bipmodel;
    public ModelPlaneRenderer boobsL;
    public int bastSize=0;
    public ModelPlaneRenderer Wall_YOSHIKOchann_konoa77kgSenyou_juyounai_wwwwww;
    public boolean smallArms = false;

    public PlayerModel2(float modelSize, boolean smallArmsIn, int bast) {
        super(modelSize,smallArmsIn);
        this.Wall_YOSHIKOchann_konoa77kgSenyou_juyounai_wwwwww = new ModelPlaneRenderer(this, 19, 22);
        this.Wall_YOSHIKOchann_konoa77kgSenyou_juyounai_wwwwww.func_228300_a_(-3.0F, 2.0F, -3F, 0F, 0F, 0F);

        bastSize = bast;
        if(bast==1) {
            this.boobsL = new ModelPlaneRenderer(this, 20, 21);
            this.boobsL.addTopPlane(-3.0F, 2.0F, -3F, 1, 1, 0F);
            this.boobsL.func_228300_a_(-3.0F, 2.0F, -3F, 0F, 0F, 0F);
            this.boobsR = new ModelPlaneRenderer(this, 19, 22);
            this.boobsR.func_228300_a_(-4.0F, 3.0F, -2.5F, 0F, 0F, 0F);
            this.boobsR.func_228300_a_(-3.0F, 2.0F, -2.25F, 0F, 0F, 0F);
            this.Wall_YOSHIKOchann_konoa77kgSenyou_juyounai_wwwwww.func_228300_a_(-4.0F, 3.0F, -2.5F, 8.0F, 3.0F, 1.0F);
            this.Wall_YOSHIKOchann_konoa77kgSenyou_juyounai_wwwwww.func_228300_a_(-3.0F, 2.0F, -2.25F, 6.0F, 1.0F, 1.0F);
            this.boobsR.func_78792_a(this.Wall_YOSHIKOchann_konoa77kgSenyou_juyounai_wwwwww);
        }else if(bast==2){
            this.boobsL = new ModelPlaneRenderer(this, 19, 22);
            this.boobsR = new ModelPlaneRenderer(this, 23, 22);
            this.boobsL.addTopPlane(-3.0F, 2.0F, -3F, 1, 1, 0F);

            this.boobsR.func_228301_a_(-2.9F, 2.5F, -3F, 3.0F, 1.0F, 1.0f,0.1f);
            this.boobsL.func_228301_a_(-0.1F, 2.5F, -3F, 3.0F, 1.0F, 1.0f,0.1f);

            this.boobsR.func_228301_a_(-3.9F, 3.5F, -4F, 4.0F, 3.0F, 2.0F,0.1f);
            this.boobsL.func_228301_a_(-0.1F, 3.5F, -4F, 4.0F, 3.0F, 2.0F,0.1f);
            this.boobsR.rotationPointY = 1;
            this.boobsL.rotationPointY = 1;
            this.boobsR.rotateAngleY = 0.08F;
            this.boobsL.rotateAngleY = 6.20F;
        }else if(bast==3){
            this.boobsL = new ModelPlaneRenderer(this, 19, 22);
            this.boobsR = new ModelPlaneRenderer(this, 23, 22);
            this.boobsR.func_228300_a_(-2.8F, 2.5F, -4F, 3.0F, 1.0F, 2.0F);
            this.boobsL.func_228300_a_(-0.2F, 2.5F, -4F, 3.0F, 1.0F, 2.0F);

            this.boobsR.func_228300_a_(-3.8F, 3.5F, -5F, 4.0F, 3.0F, 3.0F);
            this.boobsL.func_228300_a_(-0.2F, 3.5F, -5F, 4.0F, 3.0F, 3.0F);
            this.boobsR.rotationPointY = 1;
            this.boobsL.rotationPointY = 1;
            this.boobsR.rotateAngleX = 0.0872665f;
            this.boobsR.rotateAngleZ = -0.0872665f;
            this.boobsL.rotateAngleX = 0.0872665f;
            this.boobsL.rotateAngleZ = 0.0872665f;
            this.boobsR.rotateAngleY = 0.26F;
            this.boobsL.rotateAngleY = 6.02F;
        }else if(bast==4){
            this.boobsL = new ModelPlaneRenderer(this, 19, 21);
            this.boobsR = new ModelPlaneRenderer(this, 23, 21);
            this.boobsR.func_228300_a_(-2.8F, 2.4F, -4.5F, 3.0F, 1.0F, 2.5F);
            this.boobsL.func_228300_a_(-0.2F, 2.5F, -4.5F, 3.0F, 1.0F, 2.5F);

            this.boobsR.func_228300_a_(-3.8F, 3.4F, -6F, 4.0F, 3.0F, 4.0F);
            this.boobsL.func_228300_a_(-0.2F, 3.5F, -6F, 4.0F, 3.0F, 4.0F);
            this.boobsR.rotationPointY = 1;
            this.boobsL.rotationPointY = 1;
            this.boobsR.rotateAngleX = 0.0872665f;
            this.boobsR.rotateAngleZ = -0.0872665f;
            this.boobsL.rotateAngleX = 0.0872665f;
            this.boobsL.rotateAngleZ = 0.0872665f;
            this.boobsR.rotateAngleY = 0.26F;
            this.boobsL.rotateAngleY = 6.02F;
            //this.boobsR.addBackPlane(-4.0F, 3.0F, -5F, 8, 3);

        }else if(bast<1){
            this.boobsL = new ModelPlaneRenderer(this, 20, 21);
            this.boobsL.func_228300_a_(-3.0F, 2.0F, 1F, 0F, 0F, 0F);
            this.boobsL.setRotationPoint(0.0F, 0.0F, -4.0F);
            this.boobsR = new ModelPlaneRenderer(this, 19, 22);
            this.boobsR.func_228300_a_(-4.0F, 3.0F, 1F, 0F, 0F, 0F);
            this.boobsR.setRotationPoint(0.0F, 0.0F, -4.5F);

            this.boobsR.showModel = false;
            this.boobsL.showModel = false;

        }
    }

    protected Iterable<ModelRenderer> func_225602_a_() {
        return ImmutableList.of();
    }

    protected Iterable<ModelRenderer> func_225600_b_() {
        return ImmutableList.of(this.boobsL, this.boobsR);
    }




    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if(true)return;

    }
    public void setBip(BipedModel model){
        this.Bipmodel = model;
    }
    public void setAngle(){

    }
    public void func_225597_a_(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        super.func_225597_a_(p_225597_1_, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
        if(true)return;
        if(p_225597_1_.isCrouching()) {
            bipedBody.showModel = false;
            bipedHead.showModel = false;
            this.boobsL.rotateAngleX = 0.5F;
            this.boobsL.rotationPointY += 5.3F;
            this.boobsR.rotateAngleX = 0.5F;
            this.boobsR.rotationPointY += 5.3F;
        }else{
            this.boobsL.rotationPointX += 1.3F;
            this.boobsR.rotationPointX += 1.3F;

        }
    }

    public ModelPlaneRenderer getRandomModelPlaneRenderer(Random randomIn) {
        return this.ModelPlaneRenderers.get(randomIn.nextInt(this.ModelPlaneRenderers.size()));
    }

    public void accept(ModelPlaneRenderer p_accept_1_) {
        if (this.ModelPlaneRenderers == null) {
            this.ModelPlaneRenderers = Lists.newArrayList();
        }

        this.ModelPlaneRenderers.add(p_accept_1_);
    }
}
