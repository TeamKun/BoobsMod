package net.kunmc.lab.boobsmod;



import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;

@OnlyIn(Dist.CLIENT)
public class RenderPlayer2 extends LivingRenderer<AbstractClientPlayerEntity, PlayerModel2<AbstractClientPlayerEntity>> {

    public PlayerEntity p;
    public ModelRenderer mp;
    private static boolean isSmallArms;

    public static int thickness = 0;
    public PlayerModel2 models;
    private PlayerRenderer man;
    public RenderPlayer2(PlayerRenderer renderManager) {
        this(renderManager, null);
    }
    public RenderPlayer2(PlayerRenderer renderManager, PlayerModel2 models) {

        super(renderManager.getRenderManager(),models , 0F);
        this.p = p;
        this.thickness = thickness;
        this.man = renderManager;
        this.models = models;
    }
/*
    public RenderPlayer2(PlayerRenderer renderManager, boolean useSmallArms, int thickness) {

        super(renderManager.getRenderManager(),new PlayerModel2<>(0.0F, useSmallArms,thickness) , 0F);
        this.p = p;
        this.thickness = thickness;
        this.man = renderManager;
    }
*/
    public void func_225623_a_(AbstractClientPlayerEntity p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
        this.setModelVisibilities(p_225623_1_);
        p_225623_4_.func_227860_a_();
        if(p_225623_1_.isCrouching()) {
            //LogManager.getLogger().info("ok");
            models.boobsL.rotateAngleX = man.getEntityModel().bipedBody.rotateAngleX;
            models.boobsL.rotationPointY = man.getEntityModel().bipedBody.rotationPointY;
            models.boobsR.rotateAngleX = man.getEntityModel().bipedBody.rotateAngleX;
            models.boobsR.rotationPointY = man.getEntityModel().bipedBody.rotationPointY;
            //models.Wall_YOSHIKOchann_konoa77kgSenyou_juyounai_wwwwww.rotateAngleX = man.getEntityModel().bipedBody.rotateAngleX;
            //models.Wall_YOSHIKOchann_konoa77kgSenyou_juyounai_wwwwww.rotateAngleX = man.getEntityModel().bipedBody.rotationPointY;
            models.boobsR.rotateAngleZ = 0;
            models.boobsL.rotateAngleZ = 0;
            if(models.bastSize>1) {
                models.boobsR.rotateAngleY = 0.26F;
                models.boobsL.rotateAngleY = 6.02F;
            }
        }else{

        }
        if(p_225623_1_.isCrouching()){
            //p_225623_4_.func_227861_a_(0,0.2f,0);
        }
        //this.getMainModel().func_225597_a_(p_225623_1_,p_225623_2_,p_225623_3_,0,0,0);
        p_225623_4_.func_227865_b_();
        super.func_225623_a_(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);

        }
/*
    public void render(AbstractClientPlayerEntity p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
        this.setModelVisibilities(p_225623_1_);
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderPlayerEvent.Pre(p_225623_1_, this, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_))) return;
        super.func_225623_a_(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderPlayerEvent.Post(p_225623_1_, this, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_));
    }

 */
    public void render(AbstractClientPlayerEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {

    }
    public Vec3d getRenderOffset(AbstractClientPlayerEntity p_225627_1_, float p_225627_2_) {
        return p_225627_1_.isCrouching() ? new Vec3d(0.0D, -0.125D, 0.0D) : super.func_225627_b_(p_225627_1_, p_225627_2_);
    }

    public PlayerModel2 getMainModel()
    {
        if (p != null){
            if (DefaultPlayerSkin.getSkinType(p.getUniqueID()).equals("slim")) {
                isSmallArms = true;
            }}else isSmallArms = false;
        return new PlayerModel2(0.0F, isSmallArms,this.thickness);
    }
    private void setModelVisibilities(AbstractClientPlayerEntity clientPlayer) {

        //PlayerModel2<AbstractClientPlayerEntity> playermodel = this.getMainModel();
        //playermodel.setVisible(false);
        if(true)return;

    }

    private BipedModel.ArmPose func_217766_a(AbstractClientPlayerEntity p_217766_1_, ItemStack p_217766_2_, ItemStack p_217766_3_, Hand p_217766_4_) {
        BipedModel.ArmPose bipedmodel$armpose = BipedModel.ArmPose.EMPTY;
        ItemStack itemstack = p_217766_4_ == Hand.MAIN_HAND ? p_217766_2_ : p_217766_3_;
        if (!itemstack.isEmpty()) {
            bipedmodel$armpose = BipedModel.ArmPose.ITEM;
            if (p_217766_1_.getItemInUseCount() > 0) {
                UseAction useaction = itemstack.getUseAction();
                if (useaction == UseAction.BLOCK) {
                    bipedmodel$armpose = BipedModel.ArmPose.BLOCK;
                } else if (useaction == UseAction.BOW) {
                    bipedmodel$armpose = BipedModel.ArmPose.BOW_AND_ARROW;
                } else if (useaction == UseAction.SPEAR) {
                    bipedmodel$armpose = BipedModel.ArmPose.THROW_SPEAR;
                } else if (useaction == UseAction.CROSSBOW && p_217766_4_ == p_217766_1_.getActiveHand()) {
                    bipedmodel$armpose = BipedModel.ArmPose.CROSSBOW_CHARGE;
                }
            } else {
                boolean flag3 = p_217766_2_.getItem() == Items.CROSSBOW;
                boolean flag = CrossbowItem.isCharged(p_217766_2_);
                boolean flag1 = p_217766_3_.getItem() == Items.CROSSBOW;
                boolean flag2 = CrossbowItem.isCharged(p_217766_3_);
                if (flag3 && flag) {
                    bipedmodel$armpose = BipedModel.ArmPose.CROSSBOW_HOLD;
                }

                if (flag1 && flag2 && p_217766_2_.getItem().getUseAction(p_217766_2_) == UseAction.NONE) {
                    bipedmodel$armpose = BipedModel.ArmPose.CROSSBOW_HOLD;
                }
            }
        }

        return bipedmodel$armpose;
    }

    public ResourceLocation getEntityTexture(AbstractClientPlayerEntity entity) {
        return entity.getLocationSkin();
    }

    protected void preRenderCallback(AbstractClientPlayerEntity p_225620_1_, MatrixStack p_225620_2_, float p_225620_3_) {
        float f = 0.9375F;
        p_225620_2_.func_227862_a_(0.9375F, 0.9375F, 0.9375F);
    }

    protected void renderName(AbstractClientPlayerEntity p_225629_1_, String p_225629_2_, MatrixStack p_225629_3_, IRenderTypeBuffer p_225629_4_, int p_225629_5_) {
        double d0 = this.renderManager.func_229099_b_(p_225629_1_);
        p_225629_3_.func_227860_a_();
        if (d0 < 100.0D) {
            Scoreboard scoreboard = p_225629_1_.getWorldScoreboard();
            ScoreObjective scoreobjective = scoreboard.getObjectiveInDisplaySlot(2);
            if (scoreobjective != null) {
                Score score = scoreboard.getOrCreateScore(p_225629_1_.getScoreboardName(), scoreobjective);
                super.func_225629_a_(p_225629_1_, score.getScorePoints() + " " + scoreobjective.getDisplayName().getFormattedText(), p_225629_3_, p_225629_4_, p_225629_5_);
                p_225629_3_.func_227861_a_(0.0D, (double)(9.0F * 1.15F * 0.025F), 0.0D);
            }
        }

        super.func_225629_a_(p_225629_1_, p_225629_2_, p_225629_3_, p_225629_4_, p_225629_5_);
        p_225629_3_.func_227865_b_();
    }



    protected void func_225621_a_(AbstractClientPlayerEntity p_225621_1_, MatrixStack p_225621_2_, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
        float f = p_225621_1_.getSwimAnimation(p_225621_5_);
        if (p_225621_1_.isElytraFlying()) {
            super.func_225621_a_(p_225621_1_, p_225621_2_, p_225621_3_, p_225621_4_, p_225621_5_);
            float f1 = (float)p_225621_1_.getTicksElytraFlying() + p_225621_5_;
            float f2 = MathHelper.clamp(f1 * f1 / 100.0F, 0.0F, 1.0F);
            if (!p_225621_1_.isSpinAttacking()) {
                p_225621_2_.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(f2 * (-90.0F - p_225621_1_.rotationPitch)));
            }

            Vec3d vec3d = p_225621_1_.getLook(p_225621_5_);
            Vec3d vec3d1 = p_225621_1_.getMotion();
            double d0 = Entity.horizontalMag(vec3d1);
            double d1 = Entity.horizontalMag(vec3d);
            if (d0 > 0.0D && d1 > 0.0D) {
                double d2 = (vec3d1.x * vec3d.x + vec3d1.z * vec3d.z) / (Math.sqrt(d0) * Math.sqrt(d1));
                double d3 = vec3d1.x * vec3d.z - vec3d1.z * vec3d.x;
                p_225621_2_.func_227863_a_(Vector3f.field_229181_d_.func_229193_c_((float)(Math.signum(d3) * Math.acos(d2))));
            }
        } else if (f > 0.0F) {
            super.func_225621_a_(p_225621_1_, p_225621_2_, p_225621_3_, p_225621_4_, p_225621_5_);
            float f3 = p_225621_1_.isInWater() ? -90.0F - p_225621_1_.rotationPitch : -90.0F;
            float f4 = MathHelper.lerp(f, 0.0F, f3);
            p_225621_2_.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(f4));
            if (p_225621_1_.isActualySwimming()) {
                p_225621_2_.func_227861_a_(0.0D, -1.0D, (double)0.3F);
            }
        } else {
            super.func_225621_a_(p_225621_1_, p_225621_2_, p_225621_3_, p_225621_4_, p_225621_5_);
        }

    }
}

/*
@SideOnly(Side.CLIENT)
public class RenderPlayer2 extends RenderLivingBase<AbstractClientPlayer> {
    private static final Logger LOGGER = LogManager.getLogger();

    public AbstractClientPlayer p;
    public float HP;

    private static boolean isSmallArms;

    public static int thickness = 0;

    private RenderManager man;
    public RenderPlayer2(RenderManager renderManager)
    {
        this(renderManager, null,0);
    }

    public RenderPlayer2(RenderManager renderManager, AbstractClientPlayer p, float HP){

        super(renderManager,new ModelPlayer1(0.0F, isSmallArms,HP), 0.5F);
        this.HP = HP;
        this.p = p;
        this.man = renderManager;
        this.addLayer(new LayerBipedArmor(this));
        //this.addLayer(new LayerHeldItem2(this));
        this.addLayer(new LayerArrow(this));
        //this.addLayer(new net.minecraft.client.renderer.entity.layers.LayerCape((RenderPlayer) this));
        this.addLayer(new LayerCustomHead(this.getMainModel().bipedHead));
        this.addLayer(new LayerElytra(this));
        this.addLayer(new LayerEntityOnShoulder(renderManager));
    }

    public ModelPlayer1 getMainModel()
    {
        if (p != null){
        if (DefaultPlayerSkin.getSkinType(p.getUniqueID()).equals("slim")) {
            isSmallArms = true;
        }}else isSmallArms = false;
        return new ModelPlayer1(0.0F, isSmallArms,this.HP);
    }

    public void doRender(AbstractClientPlayer entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        //if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderPlayerEvent.Pre(entity, this, partialTicks, x, y, z))) return;
        if (!entity.isUser() || this.renderManager.renderViewEntity == entity)
        {
            double d0 = y;

            if (entity.isSneaking())
            {
                d0 = y - 0.125D;
            }

            this.setModelVisibilities(entity);
            GlStateManager.enableBlendProfile(GlStateManager.Profile.PLAYER_SKIN);

            doRender2(entity, x, d0, z, entityYaw, partialTicks);

            GlStateManager.disableBlendProfile(GlStateManager.Profile.PLAYER_SKIN);
        }
        //net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.RenderPlayerEvent.Post(entity, this, partialTicks, x, y, z));
    }
    public void doRender2(AbstractClientPlayer entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        this.mainModel = getMainModel();
        this.mainModel.swingProgress = getSwingProgress((AbstractClientPlayer) entity, partialTicks);
        boolean shouldSit = (entity.isRiding() && entity.getRidingEntity() != null && entity.getRidingEntity().shouldRiderSit());
        this.mainModel.isRiding = shouldSit;
        try {
            float f = interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, partialTicks);
            float f1 = interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, partialTicks);
            float f2 = f1 - f;
            if (shouldSit && entity.getRidingEntity() instanceof EntityLivingBase) {
                EntityLivingBase entitylivingbase = (EntityLivingBase)entity.getRidingEntity();
                f = interpolateRotation(entitylivingbase.prevRenderYawOffset, entitylivingbase.renderYawOffset, partialTicks);
                f2 = f1 - f;
                float f3 = MathHelper.wrapDegrees(f2);
                if (f3 < -85.0F)
                    f3 = -85.0F;
                if (f3 >= 85.0F)
                    f3 = 85.0F;
                f = f1 - f3;
                if (f3 * f3 > 2500.0F)
                    f += f3 * 0.2F;
                f2 = f1 - f;
            }
            float f7 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
            renderLivingAt(entity, x, y, z);
            float f8 = handleRotationFloat((AbstractClientPlayer)entity, partialTicks);
            applyRotations(entity, f8, f, partialTicks);
            float f4 = prepareScale((AbstractClientPlayer) entity, partialTicks);
            float f5 = 0.0F;
            float f6 = 0.0F;
            if (!entity.isRiding()) {
                f5 = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * partialTicks;
                f6 = entity.limbSwing - entity.limbSwingAmount * (1.0F - partialTicks);
                if (entity.isChild())
                    f6 *= 3.0F;
                if (f5 > 1.0F)
                    f5 = 1.0F;
                f2 = f1 - f;
            }
            GlStateManager.enableAlpha();
            this.mainModel.setLivingAnimations((EntityLivingBase)entity, f6, f5, partialTicks);
            this.mainModel.setRotationAngles(f6, f5, f8, f2, f7, f4, (Entity)entity);
            if (this.renderOutlines) {
                boolean flag1 = setScoreTeamColor((AbstractClientPlayer) entity);
                GlStateManager.enableColorMaterial();
                GlStateManager.enableOutlineMode(getTeamColor((AbstractClientPlayer) entity));
                if (!this.renderMarker)
                    renderModel((AbstractClientPlayer) entity, f6, f5, f8, f2, f7, f4);
                if (!(entity instanceof AbstractClientPlayer) || !entity.isSpectator())
                    renderLayers((AbstractClientPlayer) entity, f6, f5, partialTicks, f8, f2, f7, f4);
                GlStateManager.disableOutlineMode();
                GlStateManager.disableColorMaterial();
                if (flag1)
                    unsetScoreTeamColor();
            } else {
                boolean flag = setDoRenderBrightness((AbstractClientPlayer)entity, partialTicks);
                renderModel((AbstractClientPlayer) entity, f6, f5, f8, f2, f7, f4);
                if (flag)
                    unsetBrightness();
                GlStateManager.depthMask(true);
                if (!(entity instanceof EntityPlayer) || !entity.isSpectator())
                    renderLayers((AbstractClientPlayer) entity, f6, f5, partialTicks, f8, f2, f7, f4);
            }
            GlStateManager.disableRescaleNormal();
        } catch (Exception exception) {
            LOGGER.error("Couldn't render entity", exception);
        }

        this.mainModel.isChild = false;
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.enableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.enableCull();
        GlStateManager.popMatrix();
    }
    private void setModelVisibilities(AbstractClientPlayer clientPlayer)
    {
        ModelPlayer1 modelplayer1 = this.getMainModel();

        if (clientPlayer.isSpectator())
        {
            modelplayer1.setVisible(false);
            modelplayer1.bipedHead.showModel = true;
            modelplayer1.bipedHeadwear.showModel = true;
        }
        else
        {
            ItemStack itemstack = clientPlayer.getHeldItemMainhand();
            ItemStack itemstack1 = clientPlayer.getHeldItemOffhand();
            modelplayer1.setVisible(true);
            modelplayer1.bipedHeadwear.showModel = clientPlayer.isWearing(EnumPlayerModelParts.HAT);
            modelplayer1.bipedBodyWear.showModel = clientPlayer.isWearing(EnumPlayerModelParts.JACKET);
            modelplayer1.bipedLeftLegwear.showModel = clientPlayer.isWearing(EnumPlayerModelParts.LEFT_PANTS_LEG);
            modelplayer1.bipedRightLegwear.showModel = clientPlayer.isWearing(EnumPlayerModelParts.RIGHT_PANTS_LEG);
            modelplayer1.bipedLeftArmwear.showModel = clientPlayer.isWearing(EnumPlayerModelParts.LEFT_SLEEVE);
            modelplayer1.bipedRightArmwear.showModel = clientPlayer.isWearing(EnumPlayerModelParts.RIGHT_SLEEVE);
            modelplayer1.isSneak = clientPlayer.isSneaking();
            ModelBiped.ArmPose modelbiped$armpose = ModelBiped.ArmPose.EMPTY;
            ModelBiped.ArmPose modelbiped$armpose1 = ModelBiped.ArmPose.EMPTY;
            if (!itemstack.isEmpty())
            {

                modelbiped$armpose = ModelBiped.ArmPose.ITEM;

                if (clientPlayer.getItemInUseCount() > 0)
                {
                    EnumAction enumaction = itemstack.getItemUseAction();

                    if (enumaction == EnumAction.BLOCK)
                    {
                        modelbiped$armpose = ModelBiped.ArmPose.BLOCK;
                    }
                    else if (enumaction == EnumAction.BOW)
                    {
                        modelbiped$armpose = ModelBiped.ArmPose.BOW_AND_ARROW;
                    }
                }
            }

            if (!itemstack1.isEmpty())
            {
                modelbiped$armpose1 = ModelBiped.ArmPose.ITEM;

                if (clientPlayer.getItemInUseCount() > 0)
                {
                    EnumAction enumaction1 = itemstack1.getItemUseAction();

                    if (enumaction1 == EnumAction.BLOCK)
                    {
                        modelbiped$armpose1 = ModelBiped.ArmPose.BLOCK;
                    }
                    // FORGE: fix MC-88356 allow offhand to use bow and arrow animation
                    else if (enumaction1 == EnumAction.BOW)
                    {
                        modelbiped$armpose1 = ModelBiped.ArmPose.BOW_AND_ARROW;
                    }
                }
            }

            if (clientPlayer.getPrimaryHand() == EnumHandSide.RIGHT)
            {
                modelplayer1.rightArmPose = modelbiped$armpose;
                modelplayer1.leftArmPose = modelbiped$armpose1;
            }
            else
            {
                modelplayer1.rightArmPose = modelbiped$armpose1;
                modelplayer1.leftArmPose = modelbiped$armpose;
            }
        }
    }



    public ResourceLocation getEntityTexture(AbstractClientPlayer entity)
    {
        return entity.getLocationSkin();
    }

    /*public void transformHeldFull3DItemLayer()
    {
        GlStateManager.translate(0.0F, 0.1875F, 0.0F);
    }

     */
    /*

    protected void preRenderCallback(AbstractClientPlayer entitylivingbaseIn, float partialTickTime)
    {
        float f = 0.9375F;
        GlStateManager.scale(0.9375F, 0.9375F, 0.9375F);
    }

    protected void renderEntityName(AbstractClientPlayer entityIn, double x, double y, double z, String name, double distanceSq)
    {
        if (distanceSq < 100.0D)
        {
            Scoreboard scoreboard = entityIn.getWorldScoreboard();
            ScoreObjective scoreobjective = scoreboard.getObjectiveInDisplaySlot(2);

            if (scoreobjective != null)
            {
                Score score = scoreboard.getOrCreateScore(entityIn.getName(), scoreobjective);
                this.renderLivingLabel(entityIn, score.getScorePoints() + " " + scoreobjective.getDisplayName(), x, y, z, 64);
                y += (double)((float)this.getFontRendererFromRenderManager().FONT_HEIGHT * 1.15F * 0.025F);
            }
        }

        super.renderEntityName(entityIn, x, y, z, name, distanceSq);
    }

    public void renderRightArm(AbstractClientPlayer clientPlayer)
    {
        float f = 1.0F;
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        float f1 = 0.0625F;
        ModelPlayer1 modelplayer1 = this.getMainModel();
        this.setModelVisibilities(clientPlayer);
        GlStateManager.enableBlend();
        modelplayer1.swingProgress = 0.0F;
        modelplayer1.isSneak = false;
        modelplayer1.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, clientPlayer);
        modelplayer1.bipedRightArm.rotateAngleX = 0.0F;
        //modelplayer1.bipedRightArm.render(0.0625F);
        modelplayer1.bipedRightArmwear.rotateAngleX = 0.0F;
        modelplayer1.bipedRightArmwear.render(0.0625F);
        GlStateManager.disableBlend();
    }

    public void renderLeftArm(AbstractClientPlayer clientPlayer)
    {
        float f = 1.0F;
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        float f1 = 0.0625F;
        ModelPlayer1 modelplayer1 = this.getMainModel();
        this.setModelVisibilities(clientPlayer);
        GlStateManager.enableBlend();
        modelplayer1.isSneak = false;
        modelplayer1.swingProgress = 0.0F;
        modelplayer1.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, clientPlayer);
        modelplayer1.bipedLeftArm.rotateAngleX = 0.0F;
        //modelplayer1.bipedLeftArm.render(0.0625F);
        modelplayer1.bipedLeftArmwear.rotateAngleX = 0.0F;
        modelplayer1.bipedLeftArmwear.render(0.0625F);
        GlStateManager.disableBlend();
    }

    protected void renderLivingAt(AbstractClientPlayer entityLivingBaseIn, double x, double y, double z)
    {
        if (entityLivingBaseIn.isEntityAlive() && entityLivingBaseIn.isPlayerSleeping())
        {
            super.renderLivingAt(entityLivingBaseIn, x + (double)entityLivingBaseIn.renderOffsetX, y + (double)entityLivingBaseIn.renderOffsetY, z + (double)entityLivingBaseIn.renderOffsetZ);
        }
        else
        {
            super.renderLivingAt(entityLivingBaseIn, x, y, z);
        }
    }

    protected void applyRotations(AbstractClientPlayer entityLiving, float ageInTicks, float rotationYaw, float partialTicks)
    {
        if (entityLiving.isEntityAlive() && entityLiving.isPlayerSleeping())
        {
            GlStateManager.rotate(entityLiving.getBedOrientationInDegrees(), 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(this.getDeathMaxRotation(entityLiving), 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
        }
        else if (entityLiving.isElytraFlying())
        {
            super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
            float f = (float)entityLiving.getTicksElytraFlying() + partialTicks;
            float f1 = MathHelper.clamp(f * f / 100.0F, 0.0F, 1.0F);
            GlStateManager.rotate(f1 * (-90.0F - entityLiving.rotationPitch), 1.0F, 0.0F, 0.0F);
            Vec3d vec3d = entityLiving.getLook(partialTicks);
            double d0 = entityLiving.motionX * entityLiving.motionX + entityLiving.motionZ * entityLiving.motionZ;
            double d1 = vec3d.x * vec3d.x + vec3d.z * vec3d.z;

            if (d0 > 0.0D && d1 > 0.0D)
            {
                double d2 = (entityLiving.motionX * vec3d.x + entityLiving.motionZ * vec3d.z) / (Math.sqrt(d0) * Math.sqrt(d1));
                double d3 = entityLiving.motionX * vec3d.z - entityLiving.motionZ * vec3d.x;
                GlStateManager.rotate((float)(Math.signum(d3) * Math.acos(d2)) * 180.0F / (float)Math.PI, 0.0F, 1.0F, 0.0F);
            }
        }
        else
        {
            super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
        }
    }
}
*/