package net.kunmc.lab.boobsmod;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityElementalFire {
    @CapabilityInject(ElementalFire.class)
    public static Capability<ElementalFire> CAPABILITY_ELEMENTAL_FIRE = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(
                ElementalFire.class,
                new ElementalFire.ElementalFireNBTStorage(),
                ElementalFire::createADefaultInstance);
    }
}
