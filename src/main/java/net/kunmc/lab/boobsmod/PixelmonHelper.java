package net.kunmc.lab.boobsmod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.ModList;
import org.apache.logging.log4j.LogManager;

public class PixelmonHelper
{
    public static boolean Enabled = false;
    private static Method getPixelmonModel = null;

    private static Class modelSetupClass;
    private static Method modelSetupMethod;

    public static void load() {
        Enabled = ModList.get().isLoaded("pixelmon");
        if (!Enabled) {
            return;
        }
        try {
            Class<?> c = Class.forName("com.pixelmonmod.pixelmon.entities.pixelmon.Entity2Client");
            getPixelmonModel = c.getMethod("getModel", new Class[0]);

            modelSetupClass = Class.forName("com.pixelmonmod.pixelmon.client.models.PixelmonModelSmd");
            modelSetupMethod = modelSetupClass.getMethod("setupForRender", new Class[] { c });
        }
        catch (Exception e) {
            Enabled = false;
        }
    }


    public static List<String> getPixelmonList() {
        List<String> list = new ArrayList<>();
        if (!Enabled)
            return list;
        try {
            Class<?> c = Class.forName("com.pixelmonmod.pixelmon.enums.EnumPokemonModel");
            Object[] array = c.getEnumConstants();
            for (Object ob : array) {
                list.add(ob.toString());
            }
        } catch (Exception e) {
            LogManager.getLogger().error("getPixelmonList", e);
        }
        return list;
    }

    public static boolean isPixelmon(Entity entity) {
        if (!Enabled)
            return false;
        return entity.getType().getTranslationKey().toLowerCase().contains("pixelmon");
    }


    public static Object getModel(LivingEntity entity) {
        try {
            return getPixelmonModel.invoke(entity, new Object[0]);
        } catch (Exception e) {
            LogManager.getLogger().error("getModel", e);

            return null;
        }
    }
    public static void setupModel(LivingEntity entity, Object model) {
        try {
            if (modelSetupClass.isAssignableFrom(model.getClass())) {
                modelSetupMethod.invoke(model, new Object[] { entity });
            }
        } catch (Exception e) {
            LogManager.getLogger().error("setupModel", e);
        }
    }

    public static String getName(LivingEntity entity) {
        if (!Enabled || !isPixelmon((Entity)entity))
            return "";
        try {
            Method m = entity.getClass().getMethod("getName", new Class[0]);
            return m.invoke(entity, new Object[0]).toString();
        } catch (Exception e) {
            LogManager.getLogger().error("getName", e);

            return "";
        }
    }

    public static void debug(LivingEntity entity) {
        if (!Enabled || !isPixelmon((Entity)entity))
            return;
        try {
            Method m = entity.getClass().getMethod("getModel", new Class[0]);
            (Minecraft.getInstance()).player.sendMessage((ITextComponent)new StringTextComponent((String)m.invoke(entity, new Object[0])));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}