package net.kunmc.lab.boobsmod;

import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.kunmc.lab.boobsmod.constants.EnumAnimation;
import net.kunmc.lab.boobsmod.constants.EnumParts;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ForgeRegistries;

public class ModelDate
        extends ModelDataShared implements ICapabilityProvider {
    public static ExecutorService saveExecutor = Executors.newFixedThreadPool(1);

    @CapabilityInject(ModelDate.class)
    public static Capability<ModelDate> MODELDATe_CAPABILITY = null;

    private LazyOptional<ModelDate> instance = LazyOptional.of(() -> this);

    public boolean resourceInit = false;

    public boolean resourceLoaded = false;

    public boolean webapiActive = false;
    public boolean webapiInit = false;
    public Object textureObject = null;

    public ItemStack backItem = ItemStack.EMPTY;

    public int inLove = 0;
    public int animationTime = -1;

    public EnumAnimation animation = EnumAnimation.NONE;
    public EnumAnimation prevAnimation = EnumAnimation.NONE;
    public int animationStart = 0;

    public float sleepRotation;
    public short soundType = 0;
    public double prevPosX;
    public double prevPosY;
    public double prevPosZ;
    public PlayerEntity player = null;

    public long lastEdited = System.currentTimeMillis();


    public UUID analyticsUUID = UUID.randomUUID();


    public synchronized CompoundNBT writeToNBT() {
        CompoundNBT compound = super.writeToNBT();
        compound.putShort("SoundType", this.soundType);

        compound.putInt("Animation", this.animation.ordinal());

        compound.putLong("LastEdited", this.lastEdited);

        return compound;
    }

    public synchronized void readFromNBT(CompoundNBT compound) {
        String prevUrl = this.url;
        super.readFromNBT(compound);
        this.soundType = compound.getShort("SoundType");

        this.lastEdited = compound.getLong("LastEdited");

        if (this.player != null)
        {
            if (!hasEntity()) {
                this.player.getPersistentData().remove("MPMModel");
            } else {
                this.player.getPersistentData().putString("MPMModel", getEntityName().toString());
            }  }
        setAnimation(compound.getInt("Animation"));

        if (!prevUrl.equals(this.url)) {
            this.resourceInit = false;
            this.resourceLoaded = false;
        }
    }

    @Override
    public ModelPartData getPartData(EnumParts claws) {
        return null;
    }



    public void setAnimation(int i) {
        if (i < (EnumAnimation.values()).length) {
            this.animation = EnumAnimation.values()[i];
        } else {
            this.animation = EnumAnimation.NONE;
        }  setAnimation(this.animation);
    }

    public void setAnimation(EnumAnimation ani) {
        this.animationTime = -1;
        this.animation = ani;
        this.lastEdited = System.currentTimeMillis();

        if (this.animation == EnumAnimation.WAVING) {
            this.animationTime = 80;
        }
        if (this.animation == EnumAnimation.YES || this.animation == EnumAnimation.NO) {
            this.animationTime = 60;
        }
        if (this.player == null || ani == EnumAnimation.NONE) {
            this.animationStart = -1;
        } else {
            this.animationStart = this.player.ticksExisted;
        }
    }
    public LivingEntity getEntity(PlayerEntity player) {
        if (!hasEntity())
            return null;
        if (this.entity == null) {
            try {
                this.entity = (LivingEntity)((EntityType)ForgeRegistries.ENTITIES.getValue(getEntityName())).create(player.world);

                if (PixelmonHelper.isPixelmon((Entity)this.entity) && player.world.isRemote && !this.extra.contains("Name")) {
                    this.extra.putString("Name", "Abra");
                }

                this.entity.readAdditional(this.extra);
                this.entity.setInvulnerable(true);
                this.entity.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(player.getMaxHealth());

                this.entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, player.getHeldItemMainhand());
                this.entity.setItemStackToSlot(EquipmentSlotType.OFFHAND, player.getHeldItemOffhand());
                this.entity.setItemStackToSlot(EquipmentSlotType.HEAD, player.inventory.armorItemInSlot(3));
                this.entity.setItemStackToSlot(EquipmentSlotType.CHEST, player.inventory.armorItemInSlot(2));
                this.entity.setItemStackToSlot(EquipmentSlotType.LEGS, player.inventory.armorItemInSlot(1));
                this.entity.setItemStackToSlot(EquipmentSlotType.FEET, player.inventory.armorItemInSlot(0));
            } catch (Exception exception) {}
        }

        return this.entity;
    }

    public ModelDate copy() {
        ModelDate data = new ModelDate();
        data.readFromNBT(writeToNBT());
        data.resourceLoaded = this.resourceLoaded;
        data.player = this.player;
        return data;
    }

    public boolean isSleeping() {
        return (this.animation == EnumAnimation.SLEEPING);
    }

    public boolean animationEquals(EnumAnimation animation2) {
        return (animation2 == this.animation);
    }

    public float getOffsetCamera(PlayerEntity player) {
        if (!Boobsmod.EnablePOV)
            return 0.0F;
        float offset = -offsetY();
        if (this.animation == EnumAnimation.SITTING) {
            offset += 0.5F - getLegsY();
        }
        if (isSleeping())
            offset = 1.18F;
        if (this.animation == EnumAnimation.CRAWLING)
            offset = 0.8F;
        if (offset < -0.2F && isBlocked(player))
            offset = -0.2F;
        return offset;
    }

    private boolean isBlocked(PlayerEntity player) {
        return !player.world.isAirBlock((new BlockPos((Entity)player)).up(2));
    }


    private static ModelDate backup = new ModelDate();
    public static ModelDate get(PlayerEntity player) {
        ModelDate data = (ModelDate)player.getCapability(MODELDATe_CAPABILITY, null).orElse(backup);
        if (data.player == null) {
            data.player = player;
            CompoundNBT compound = loadPlayerData(player.getUniqueID());
            if (compound != null) {
                data.readFromNBT(compound);
            }
        }
        return data;
    }

    private static CompoundNBT loadPlayerData(UUID id) {
        String filename = id.toString();
        if (filename.isEmpty())
            filename = "noplayername";
        filename = filename + ".dat";
        try {
            File file = new File(Boobsmod.dir, filename);
            if (!file.exists()) {
                return null;
            }
            return CompressedStreamTools.readCompressed(new FileInputStream(file));
        } catch (Exception e) {

            try {
                File file = new File(Boobsmod.dir, filename + "_old");
                if (!file.exists()) {
                    return null;
                }
                return CompressedStreamTools.readCompressed(new FileInputStream(file));
            }
            catch (Exception exception) {

                return null;
            }
        }
    }
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction facing) {
        if (capability == MODELDATe_CAPABILITY)
            return this.instance.cast();
        return LazyOptional.empty();
    }
}
