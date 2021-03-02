package net.minecraft.client.renderer.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class MPMRenderEntityUtil {
    public static ResourceLocation getResource(Entity entity) {
        EntityRenderer render = Minecraft.getInstance().getRenderManager().getRenderer(entity);
        if (render == null)
            return null;
        return render.getEntityTexture(entity);
    }

    public static void setModel(LivingRenderer renderer, EntityModel model) {
        renderer.entityModel = model;
    }

    public static List<LayerRenderer> getLayers(LivingRenderer renderer) {
        return renderer.layerRenderers;
    }
}