package net.kunmc.lab.boobsmod;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.client.renderer.Matrix3f;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.Vector4f;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelRenderer2 extends ModelRenderer {
    private float textureWidth = 64.0F;
    private float textureHeight = 32.0F;
    private int textureOffsetX;
    private int textureOffsetY;
    public float rotationPointX;
    public float rotationPointY;
    public float rotationPointZ;
    public float rotateAngleX;
    public float rotateAngleY;
    public float rotateAngleZ;
    public boolean mirror;
    public boolean showModel = true;
    private final ObjectList<ModelRenderer2.ModelBox> cubeList = new ObjectArrayList<>();
    private final ObjectList<ModelRenderer2> childModels = new ObjectArrayList<>();

    public ModelRenderer2(Model model) {
        super(model);
        model.accept(this);
        this.setTextureSize(model.textureWidth, model.textureHeight);
    }


    public ModelRenderer2(Model model, int texOffX, int texOffY)
    {
        this(model);
        this.setTextureOffset(texOffX, texOffY);
    }
    public void copyModelAngles(ModelRenderer2 modelRendererIn) {
        this.rotateAngleX = modelRendererIn.rotateAngleX;
        this.rotateAngleY = modelRendererIn.rotateAngleY;
        this.rotateAngleZ = modelRendererIn.rotateAngleZ;
        this.rotationPointX = modelRendererIn.rotationPointX;
        this.rotationPointY = modelRendererIn.rotationPointY;
        this.rotationPointZ = modelRendererIn.rotationPointZ;
    }

    public void addChild(ModelRenderer2 renderer) {
        this.childModels.add(renderer);
    }

    public ModelRenderer2 setTextureOffset(int x, int y) {
        this.textureOffsetX = x;
        this.textureOffsetY = y;
        return this;
    }

    public ModelRenderer2 addBox(String partName, float x, float y, float z, int width, int height, int depth, float delta, int texX, int texY) {
        this.setTextureOffset(texX, texY);
        this.addBox(this.textureOffsetX, this.textureOffsetY, x, y, z, (float)width, (float)height, (float)depth, delta, delta, delta, this.mirror, false);
        return this;
    }

    public ModelRenderer2 addBox(float x, float y, float z, float width, float height, float depth) {
        this.addBox(this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, 0.0F, 0.0F, 0.0F, this.mirror, false);
        return this;
    }

    public ModelRenderer2 addBox(float x, float y, float z, float width, float height, float depth, boolean mirrorIn) {
        this.addBox(this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, 0.0F, 0.0F, 0.0F, mirrorIn, false);
        return this;
    }

    public void addBox(float x, float y, float z, float width, float height, float depth, float delta) {
        this.addBox(this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, delta, delta, delta, this.mirror, false);
    }

    public void addBox(float x, float y, float z, float width, float height, float depth, float deltaX, float deltaY, float deltaZ) {
        this.addBox(this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, deltaX, deltaY, deltaZ, this.mirror, false);
    }

    public void addBox(float x, float y, float z, float width, float height, float depth, float delta, boolean mirrorIn) {
        this.addBox(this.textureOffsetX, this.textureOffsetY, x, y, z, width, height, depth, delta, delta, delta, mirrorIn, false);
    }

    private void addBox(int texOffX, int texOffY, float x, float y, float z, float width, float height, float depth, float deltaX, float deltaY, float deltaZ, boolean mirror, boolean p_228305_13_) {
        this.cubeList.add(new ModelRenderer2.ModelBox(texOffX, texOffY, x, y, z, width, height, depth, deltaX, deltaY, deltaZ, mirror, this.textureWidth, this.textureHeight));
    }

    public void setRotationPoint(float rotationPointXIn, float rotationPointYIn, float rotationPointZIn) {
        this.rotationPointX = rotationPointXIn;
        this.rotationPointY = rotationPointYIn;
        this.rotationPointZ = rotationPointZIn;
    }

    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn) {
        this.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (this.showModel) {
            if (!this.cubeList.isEmpty() || !this.childModels.isEmpty()) {
                matrixStackIn.func_227860_a_();
                this.translateRotate(matrixStackIn);
                this.doRender(matrixStackIn.func_227866_c_(), bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);

                for(ModelRenderer2 modelrenderer2 : this.childModels) {
                    modelrenderer2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
                }

                matrixStackIn.func_227865_b_();
            }
        }
    }

    public void translateRotate(MatrixStack matrixStackIn) {
        matrixStackIn.func_227861_a_((double)(this.rotationPointX / 16.0F), (double)(this.rotationPointY / 16.0F), (double)(this.rotationPointZ / 16.0F));
        if (this.rotateAngleZ != 0.0F) {
            matrixStackIn.func_227863_a_(Vector3f.field_229183_f_.func_229193_c_(this.rotateAngleZ));
        }

        if (this.rotateAngleY != 0.0F) {
            matrixStackIn.func_227863_a_(Vector3f.field_229181_d_.func_229193_c_(this.rotateAngleY));
        }

        if (this.rotateAngleX != 0.0F) {
            matrixStackIn.func_227863_a_(Vector3f.field_229179_b_.func_229193_c_(this.rotateAngleX));
        }

    }

    private void doRender(MatrixStack.Entry matrixEntryIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        Matrix4f matrix4f = matrixEntryIn.func_227870_a_();
        Matrix3f matrix3f = matrixEntryIn.func_227872_b_();

        for(ModelRenderer2.ModelBox modelrenderer2$modelbox : this.cubeList) {
            for(ModelRenderer2.TexturedQuad modelrenderer2$texturedquad : modelrenderer2$modelbox.quads) {
                Vector3f vector3f = modelrenderer2$texturedquad.normal.func_229195_e_();
                vector3f.func_229188_a_(matrix3f);
                float f = vector3f.getX();
                float f1 = vector3f.getY();
                float f2 = vector3f.getZ();

                for(int i = 0; i < 4; ++i) {
                    ModelRenderer2.PositionTextureVertex modelrenderer2$positiontexturevertex = modelrenderer2$texturedquad.vertexPositions[i];
                    float f3 = modelrenderer2$positiontexturevertex.vector3D.getX() / 16.0F;
                    float f4 = modelrenderer2$positiontexturevertex.vector3D.getY() / 16.0F;
                    float f5 = modelrenderer2$positiontexturevertex.vector3D.getZ() / 16.0F;
                    Vector4f vector4f = new Vector4f(f3, f4, f5, 1.0F);
                    vector4f.func_229372_a_(matrix4f);
                    bufferIn.func_225588_a_(vector4f.getX(), vector4f.getY(), vector4f.getZ(), red, green, blue, alpha, modelrenderer2$positiontexturevertex.texturePositionX, modelrenderer2$positiontexturevertex.texturePositionY, packedOverlayIn, packedLightIn, f, f1, f2);
                }
            }
        }

    }

    public ModelRenderer2 setTextureSize(int textureWidthIn, int textureHeightIn) {
        this.textureWidth = (float)textureWidthIn;
        this.textureHeight = (float)textureHeightIn;
        return this;
    }


    @OnlyIn(Dist.CLIENT)
    public static class ModelBox {
        private final ModelRenderer2.TexturedQuad[] quads;
        public final float posX1;
        public final float posY1;
        public final float posZ1;
        public final float posX2;
        public final float posY2;
        public final float posZ2;

        public ModelBox(int texOffX, int texOffY, float x, float y, float z, float width, float height, float depth, float deltaX, float deltaY, float deltaZ, boolean mirror, float texWidth, float texHeight) {
            this.posX1 = x;
            this.posY1 = y;
            this.posZ1 = z;
            this.posX2 = x + width;
            this.posY2 = y + height;
            this.posZ2 = z + depth;
            this.quads = new ModelRenderer2.TexturedQuad[6];
            float f = x + width;
            float f1 = y + height;
            float f2 = z + depth;
            x = x - deltaX;
            y = y - deltaY;
            z = z - deltaZ;
            f = f + deltaX;
            f1 = f1 + deltaY;
            f2 = f2 + deltaZ;
            if (mirror) {
                float f3 = f;
                f = x;
                x = f3;
            }

            ModelRenderer2.PositionTextureVertex modelrenderer2$positiontexturevertex7 = new ModelRenderer2.PositionTextureVertex(x, y, z, 0.0F, 0.0F);
            ModelRenderer2.PositionTextureVertex modelrenderer2$positiontexturevertex = new ModelRenderer2.PositionTextureVertex(f, y, z, 0.0F, 8.0F);
            ModelRenderer2.PositionTextureVertex modelrenderer2$positiontexturevertex1 = new ModelRenderer2.PositionTextureVertex(f, f1, z, 8.0F, 8.0F);
            ModelRenderer2.PositionTextureVertex modelrenderer2$positiontexturevertex2 = new ModelRenderer2.PositionTextureVertex(x, f1, z, 8.0F, 0.0F);
            ModelRenderer2.PositionTextureVertex modelrenderer2$positiontexturevertex3 = new ModelRenderer2.PositionTextureVertex(x, y, f2, 0.0F, 0.0F);
            ModelRenderer2.PositionTextureVertex modelrenderer2$positiontexturevertex4 = new ModelRenderer2.PositionTextureVertex(f, y, f2, 0.0F, 8.0F);
            ModelRenderer2.PositionTextureVertex modelrenderer2$positiontexturevertex5 = new ModelRenderer2.PositionTextureVertex(f, f1, f2, 8.0F, 8.0F);
            ModelRenderer2.PositionTextureVertex modelrenderer2$positiontexturevertex6 = new ModelRenderer2.PositionTextureVertex(x, f1, f2, 8.0F, 0.0F);
            float f4 = (float)texOffX;
            float f5 = (float)texOffX + depth;
            float f6 = (float)texOffX + depth + width;
            float f7 = (float)texOffX + depth + width + width;
            float f8 = (float)texOffX + depth + width + depth;
            float f9 = (float)texOffX + depth + width + depth + width;
            float f10 = (float)texOffY;
            float f11 = (float)texOffY + depth;
            float f12 = (float)texOffY + depth + height;
            this.quads[2] = new ModelRenderer2.TexturedQuad(new ModelRenderer2.PositionTextureVertex[]{modelrenderer2$positiontexturevertex4, modelrenderer2$positiontexturevertex3, modelrenderer2$positiontexturevertex7, modelrenderer2$positiontexturevertex}, f5, f10, f6, f11, texWidth, texHeight, mirror, Direction.DOWN);
            this.quads[3] = new ModelRenderer2.TexturedQuad(new ModelRenderer2.PositionTextureVertex[]{modelrenderer2$positiontexturevertex1, modelrenderer2$positiontexturevertex2, modelrenderer2$positiontexturevertex6, modelrenderer2$positiontexturevertex5}, f6, f11, f7, f10, texWidth, texHeight, mirror, Direction.UP);
            this.quads[1] = new ModelRenderer2.TexturedQuad(new ModelRenderer2.PositionTextureVertex[]{modelrenderer2$positiontexturevertex7, modelrenderer2$positiontexturevertex3, modelrenderer2$positiontexturevertex6, modelrenderer2$positiontexturevertex2}, f4, f11, f5, f12, texWidth, texHeight, mirror, Direction.WEST);
            this.quads[4] = new ModelRenderer2.TexturedQuad(new ModelRenderer2.PositionTextureVertex[]{modelrenderer2$positiontexturevertex, modelrenderer2$positiontexturevertex7, modelrenderer2$positiontexturevertex2, modelrenderer2$positiontexturevertex1}, f5, f11, f6, f12, texWidth, texHeight, mirror, Direction.NORTH);
            this.quads[0] = new ModelRenderer2.TexturedQuad(new ModelRenderer2.PositionTextureVertex[]{modelrenderer2$positiontexturevertex4, modelrenderer2$positiontexturevertex, modelrenderer2$positiontexturevertex1, modelrenderer2$positiontexturevertex5}, f6, f11, f8, f12, texWidth, texHeight, mirror, Direction.EAST);
            this.quads[5] = new ModelRenderer2.TexturedQuad(new ModelRenderer2.PositionTextureVertex[]{modelrenderer2$positiontexturevertex3, modelrenderer2$positiontexturevertex4, modelrenderer2$positiontexturevertex5, modelrenderer2$positiontexturevertex6}, f8, f11, f9, f12, texWidth, texHeight, mirror, Direction.SOUTH);
        }
    }

    @OnlyIn(Dist.CLIENT)
    static class PositionTextureVertex {
        public final Vector3f vector3D;
        public final float texturePositionX;
        public final float texturePositionY;

        public PositionTextureVertex(float x, float y, float z, float texU, float texV) {
            this(new Vector3f(x, y, z), texU, texV);
        }

        public ModelRenderer2.PositionTextureVertex setTextureUV(float texU, float texV) {
            return new ModelRenderer2.PositionTextureVertex(this.vector3D, texU, texV);
        }

        public PositionTextureVertex(Vector3f posIn, float texU, float texV) {
            this.vector3D = posIn;
            this.texturePositionX = texU;
            this.texturePositionY = texV;
        }
    }

    @OnlyIn(Dist.CLIENT)
    static class TexturedQuad {
        public final ModelRenderer2.PositionTextureVertex[] vertexPositions;
        public final Vector3f normal;

        public TexturedQuad(ModelRenderer2.PositionTextureVertex[] positionsIn, float u1, float v1, float u2, float v2, float texWidth, float texHeight, boolean mirrorIn, Direction directionIn) {
            this.vertexPositions = positionsIn;
            float f = 0.0F / texWidth;
            float f1 = 0.0F / texHeight;
            positionsIn[0] = positionsIn[0].setTextureUV(u2 / texWidth - f, v1 / texHeight + f1);
            positionsIn[1] = positionsIn[1].setTextureUV(u1 / texWidth + f, v1 / texHeight + f1);
            positionsIn[2] = positionsIn[2].setTextureUV(u1 / texWidth + f, v2 / texHeight - f1);
            positionsIn[3] = positionsIn[3].setTextureUV(u2 / texWidth - f, v2 / texHeight - f1);
            if (mirrorIn) {
                int i = positionsIn.length;

                for(int j = 0; j < i / 2; ++j) {
                    ModelRenderer2.PositionTextureVertex modelrenderer2$positiontexturevertex = positionsIn[j];
                    positionsIn[j] = positionsIn[i - 1 - j];
                    positionsIn[i - 1 - j] = modelrenderer2$positiontexturevertex;
                }
            }

            this.normal = directionIn.func_229386_k_();
            if (mirrorIn) {
                this.normal.func_229192_b_(-1.0F, 1.0F, 1.0F);
            }

        }
    }
}
