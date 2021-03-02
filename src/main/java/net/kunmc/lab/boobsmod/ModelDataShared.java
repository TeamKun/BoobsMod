package net.kunmc.lab.boobsmod;

import java.util.HashMap;

import net.kunmc.lab.boobsmod.constants.EnumAnimation;
import net.kunmc.lab.boobsmod.constants.EnumParts;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;

public abstract class ModelDataShared
{
    public ModelPartConfig arm1 = new ModelPartConfig();
    public ModelPartConfig arm2 = new ModelPartConfig();
    public ModelPartConfig body = new ModelPartConfig();
    public ModelPartConfig leg1 = new ModelPartConfig();
    public ModelPartConfig leg2 = new ModelPartConfig();
    public ModelPartConfig head = new ModelPartConfig();

    protected ModelPartData legParts = new ModelPartData("legs");

    protected ResourceLocation entityName = null;

    protected LivingEntity entity;
    public CompoundNBT extra = new CompoundNBT();

    protected HashMap<EnumParts, ModelPartData> parts = new HashMap<>();

    public int wingMode = 0;

    public String url = "";
    public String displayName = "";
    public String displayFormat = "";

    public CompoundNBT writeToNBT() {
        CompoundNBT compound = new CompoundNBT();

        if (this.entityName != null) {
            compound.putString("EntityName", this.entityName.toString());
        }
        compound.put("ArmsConfig", (INBT)this.arm1.writeToNBT());
        compound.put("Arms2Config", (INBT)this.arm2.writeToNBT());
        compound.put("BodyConfig", (INBT)this.body.writeToNBT());
        compound.put("LegsConfig", (INBT)this.leg1.writeToNBT());
        compound.put("Legs2Config", (INBT)this.leg2.writeToNBT());
        compound.put("HeadConfig", (INBT)this.head.writeToNBT());

        compound.put("LegParts", (INBT)this.legParts.writeToNBT());

        compound.put("ExtraData", (INBT)this.extra);
        compound.putInt("WingMode", this.wingMode);

        compound.putString("CustomSkinUrl", this.url);
        compound.putString("DisplayName", this.displayName);
        compound.putString("DisplayDisplayFormat", this.displayFormat);

        ListNBT list = new ListNBT();
        for (EnumParts e : this.parts.keySet()) {
            CompoundNBT item = ((ModelPartData)this.parts.get(e)).writeToNBT();
            item.putString("PartName", e.name);
            list.add(item);
        }
        compound.put("Parts", (INBT)list);

        return compound;
    }

    public void readFromNBT(CompoundNBT compound) {
        setEntity(compound.getString("EntityName"));

        this.arm1.readFromNBT(compound.getCompound("ArmsConfig"));
        this.arm2.readFromNBT(compound.getCompound("Arms2Config"));
        this.body.readFromNBT(compound.getCompound("BodyConfig"));
        this.leg1.readFromNBT(compound.getCompound("LegsConfig"));
        this.leg2.readFromNBT(compound.getCompound("Legs2Config"));
        this.head.readFromNBT(compound.getCompound("HeadConfig"));

        this.legParts.readFromNBT(compound.getCompound("LegParts"));

        this.extra = compound.getCompound("ExtraData");
        this.wingMode = compound.getInt("WingMode");

        this.url = compound.getString("CustomSkinUrl");
        this.displayName = compound.getString("DisplayName");
        this.displayFormat = compound.getString("DisplayDisplayFormat");

        HashMap<EnumParts, ModelPartData> parts = new HashMap<>();
        ListNBT list = compound.getList("Parts", 10);
        for (int i = 0; i < list.size(); i++) {
            CompoundNBT item = list.getCompound(i);
            String name = item.getString("PartName");
            ModelPartData part = new ModelPartData(name);
            part.readFromNBT(item);
            EnumParts e = EnumParts.FromName(name);
            if (e != null)
                parts.put(e, part);
        }
        this.parts = parts;
        updateTransate();
    }

    private void updateTransate() {
        for (EnumParts part : EnumParts.values()) {
            ModelPartConfig config = getPartConfig(part);
            config.setTranslate(0.0F, getBodyY(), 0.0F);
        }
    }

    public void setEntity(String name) {
        if (name == null || name.isEmpty()) {
            this.entityName = null;
        } else {
            this.entityName = new ResourceLocation(name);
        }  this.entity = null;
        this.extra = new CompoundNBT();
    }

    public ResourceLocation getEntityName() {
        return this.entityName;
    }

    public boolean hasEntity() {
        return (this.entityName != null);
    }

    public float offsetY() {
        if (this.entity == null)
            return -getBodyY();
        return this.entity.getHeight() - 1.8F;
    }

    public void clearEntity() {
        this.entity = null;
    }

    public ModelPartData getPartData(EnumParts type) {
        return this.parts.get(type);
    }

    public ModelPartConfig getPartConfig(EnumParts type) {
        if (type == EnumParts.Boobs){
            return this.body;
        }
        return this.head;
    }

    public void removePart(EnumParts type) {
        this.parts.remove(type);
    }

    public ModelPartData getOrCreatePart(EnumParts type) {
        if (type == null)
            return null;
        ModelPartData part = getPartData(type);
        if (part == null)
            this.parts.put(type, part = new ModelPartData(type.name));
        return part;
    }





    public float getBodyY() {
        return (1.0F - this.body.scaleY) * 0.75F + getLegsY();
    }

    public float getLegsY() {
        ModelPartConfig legs = this.leg1;
        if (this.leg2.notShared && this.leg2.scaleY > this.leg1.scaleY) {
            legs = this.leg2;
        }

        return (1.0F - legs.scaleY) * 0.75F;
    }


    public abstract boolean animationEquals(EnumAnimation crawling);
}
