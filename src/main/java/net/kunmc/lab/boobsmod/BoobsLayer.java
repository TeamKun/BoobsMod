package net.kunmc.lab.boobsmod;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;

@OnlyIn(Dist.CLIENT)
public class BoobsLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M>  {
    private static final ResourceLocation TEXTURE_ELYTRA = new ResourceLocation("textures/entity/elytra.png");
    public PlayerModel2<T> modelElytra = new PlayerModel2<>(0.0F, false,0);

    public BoobsLayer(IEntityRenderer<T, M> p_i50942_1_) {
        super(p_i50942_1_);
    }


    public void func_225628_a_(MatrixStack p_225628_1_, IRenderTypeBuffer p_225628_2_, int p_225628_3_, T p_225628_4_, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        ItemStack itemstack = p_225628_4_.getItemStackFromSlot(EquipmentSlotType.CHEST);
        if (itemstack.getItem() == Items.ELYTRA) {


            p_225628_1_.func_227860_a_();
            p_225628_1_.func_227861_a_(0.0D, 0.0D, 0.125D);
            this.getEntityModel().setModelAttributes(this.modelElytra);
            this.modelElytra.func_225597_a_(p_225628_4_, p_225628_5_, p_225628_6_, p_225628_8_, p_225628_9_, p_225628_10_);
            p_225628_1_.func_227865_b_();
            //LogManager.getLogger().info("OKSSDASDSAD");
        }
    }
}

