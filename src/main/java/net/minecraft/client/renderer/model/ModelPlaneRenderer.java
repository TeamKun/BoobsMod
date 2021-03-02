package net.minecraft.client.renderer.model;

import net.minecraft.util.Direction;
import net.kunmc.lab.boobsmod.constants.EnumPlanePosition;

public class ModelPlaneRenderer extends ModelRenderer {
    private int textureOffsetX;

    private int textureOffsetY;

    public ModelPlaneRenderer(Model modelbase, int i, int j) {
        super(modelbase, i, j);
        this.textureOffsetX = i;
        this.textureOffsetY = j;

    }

    public void addBackPlane(float f, float f1, float f2, int i, int j) {
        addPlane(f, f1, f2, i, j, 0, 0.0F, EnumPlanePosition.BACK);
    }

    public void addSidePlane(float f, float f1, float f2, int j, int k) {
        addPlane(f, f1, f2, 0, j, k, 0.0F, EnumPlanePosition.LEFT);
    }

    public void addTopPlane(float f, float f1, float f2, int i, int k) {
        addPlane(f, f1, f2, i, 0, k, 0.0F, EnumPlanePosition.TOP);
    }

    public void addBackPlane(float f, float f1, float f2, int i, int j, float scale) {
        addPlane(f, f1, f2, i, j, 0, scale, EnumPlanePosition.BACK);
    }

    public void addSidePlane(float f, float f1, float f2, int j, int k, float scale) {
        addPlane(f, f1, f2, 0, j, k, scale, EnumPlanePosition.LEFT);
    }

    public void addTopPlane(float f, float f1, float f2, int i, int k, float scale) {
        addPlane(f, f1, f2, i, 0, k, scale, EnumPlanePosition.TOP);
    }

    public void addPlane(float x, float y, float z, int dx, int dy, int dz, float f3, EnumPlanePosition pos) {
        this.cubeList.add(new ModelRenderer.ModelBox(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, this.textureWidth, this.textureHeight));

        ModelRenderer.PositionTextureVertex lvt_18_2_ = new ModelRenderer.PositionTextureVertex(x, y, z, 0.0F, 0.0F);
        ModelRenderer.PositionTextureVertex lvt_19_1_ = new ModelRenderer.PositionTextureVertex(x + dx, y, z, 0.0F, 8.0F);
        ModelRenderer.PositionTextureVertex lvt_20_1_ = new ModelRenderer.PositionTextureVertex(x + dx, y + dy, z, 8.0F, 8.0F);
        ModelRenderer.PositionTextureVertex lvt_21_1_ = new ModelRenderer.PositionTextureVertex(x, y + dy, z, 8.0F, 0.0F);
        ModelRenderer.PositionTextureVertex lvt_22_1_ = new ModelRenderer.PositionTextureVertex(x, y, z + dz, 0.0F, 0.0F);
        ModelRenderer.PositionTextureVertex lvt_23_1_ = new ModelRenderer.PositionTextureVertex(x + dx, y, z + dz, 0.0F, 8.0F);
        ModelRenderer.PositionTextureVertex lvt_24_1_ = new ModelRenderer.PositionTextureVertex(x + dx, y + dy, z + dz, 8.0F, 8.0F);
        ModelRenderer.PositionTextureVertex lvt_25_1_ = new ModelRenderer.PositionTextureVertex(x, y + dy, z + dz, 8.0F, 0.0F);
        ModelBox box = (ModelBox)this.cubeList.get(this.cubeList.size() - 1);
        if (pos == EnumPlanePosition.TOP)
            box.quads = new ModelRenderer.TexturedQuad[] { new ModelRenderer.TexturedQuad(new ModelRenderer.PositionTextureVertex[] { lvt_20_1_, lvt_21_1_, lvt_25_1_, lvt_24_1_ }, this.textureOffsetX, this.textureOffsetY, (this.textureOffsetX + dx), (this.textureOffsetY + dz), this.textureWidth, this.textureHeight, this.mirror, Direction.UP) };
        if (pos == EnumPlanePosition.BOTTOM)
            box.quads = new ModelRenderer.TexturedQuad[] { new ModelRenderer.TexturedQuad(new ModelRenderer.PositionTextureVertex[] { lvt_23_1_, lvt_22_1_, lvt_18_2_, lvt_19_1_ }, (this.textureOffsetX + dx), (this.textureOffsetY + dz), this.textureOffsetX, this.textureOffsetY, this.textureWidth, this.textureHeight, this.mirror, Direction.DOWN) };
        if (pos == EnumPlanePosition.LEFT)
            box.quads = new ModelRenderer.TexturedQuad[] { new ModelRenderer.TexturedQuad(new ModelRenderer.PositionTextureVertex[] { lvt_18_2_, lvt_22_1_, lvt_25_1_, lvt_21_1_ }, this.textureOffsetX, this.textureOffsetY, (this.textureOffsetX + dz), (this.textureOffsetY + dy), this.textureWidth, this.textureHeight, this.mirror, Direction.WEST) };
        if (pos == EnumPlanePosition.RIGHT)
            box.quads = new ModelRenderer.TexturedQuad[] { new ModelRenderer.TexturedQuad(new ModelRenderer.PositionTextureVertex[] { lvt_23_1_, lvt_19_1_, lvt_20_1_, lvt_24_1_ }, (this.textureOffsetX + dz), (this.textureOffsetY + dy), this.textureOffsetX, this.textureOffsetY, this.textureWidth, this.textureHeight, this.mirror, Direction.EAST) };
        if (pos == EnumPlanePosition.BACK)
            box.quads = new ModelRenderer.TexturedQuad[] { new ModelRenderer.TexturedQuad(new ModelRenderer.PositionTextureVertex[] { lvt_22_1_, lvt_23_1_, lvt_24_1_, lvt_25_1_ }, this.textureOffsetX, this.textureOffsetY, (this.textureOffsetX + dy), (this.textureOffsetY + dx), this.textureWidth, this.textureHeight, this.mirror, Direction.SOUTH) };
        if (pos == EnumPlanePosition.FRONT)
            box.quads = new ModelRenderer.TexturedQuad[] { new ModelRenderer.TexturedQuad(new ModelRenderer.PositionTextureVertex[] { lvt_19_1_, lvt_18_2_, lvt_21_1_, lvt_20_1_ }, (this.textureOffsetX + dy), (this.textureOffsetY + dx), this.textureOffsetX, this.textureOffsetY, this.textureWidth, this.textureHeight, this.mirror, Direction.NORTH) };
    }

}
