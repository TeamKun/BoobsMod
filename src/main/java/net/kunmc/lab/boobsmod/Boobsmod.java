package net.kunmc.lab.boobsmod;

import net.kunmc.lab.boobsmod.mbe60_network_messages.StartupCommon;
import net.kunmc.lab.boobsmod.mbe60_network_messages.TargetEffectMessageToClient;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.PacketDistributor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.io.File;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("boobsmod")
public class Boobsmod {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static int noob = 10;
    public static HashMap PlayersBasts = new HashMap<UUID,float[]>();
    public String MOD_ID = "boobsmod";
    public static Boobsmod instace;
    public static RenderPlayer2 render;
    public static boolean EnablePOV = true;
    public static File dir;
    public static IEventBus MOD_EVENT_BUS;
    public Boobsmod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        MOD_EVENT_BUS.register(StartupCommon.class);

    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        noob = 900;
        CapabilityElementalFire.register();
        CapabilityElementalAir.register();
        CapabilityManager.INSTANCE.register(ModelDate.class, new Capability.IStorage<ModelDate>()
        {
            @Nullable
            public INBT writeNBT(Capability<ModelDate> capability, ModelDate instance, Direction side) {
                return null;
            }



            public void readNBT(Capability<ModelDate> capability, ModelDate instance, Direction side, INBT nbt) {}
        },  ModelDate::new);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("boobsmod", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
        Cmd.register(event.getCommandDispatcher());
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
    private static final ResourceLocation key = new ResourceLocation("boobsmod", "modeldata");
    @SubscribeEvent
    public void attach(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof PlayerEntity) {
            event.addCapability(key, new ModelDate());
            event.addCapability(new ResourceLocation("boobsmod:mbe65_capability_provider_entities"), new CapabilityProviderEntities());
        }
    }
    @SubscribeEvent
    public void PlayerTick(TickEvent.PlayerTickEvent event){
        if(event.side.isServer())return;
        PlayerEntity player = event.player;

        float[] basts = ((float[]) PlayersBasts.get(player.getUniqueID()));
        if(basts[1]==0) {
            if (!player.onGround)basts[1]=1;
        }else{
            basts[1]=0;
        }
        if(basts[1]==0 && basts[2]==0)return;
        basts[2]+=0.06f;
        if(basts[2]>5)basts[2]=0;
        PlayersBasts.put(player.getUniqueID(),basts);
    }
    @SubscribeEvent
    public void PlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerEntity p = event.getPlayer();
        ElementalFire fireInterface = p.getCapability(CapabilityElementalFire.CAPABILITY_ELEMENTAL_FIRE).orElse(null);
        TargetEffectMessageToClient msg = new TargetEffectMessageToClient(event.getPlayer().entityUniqueID, fireInterface.getChargeLevel(), 0);
        StartupCommon.simpleChannel.send(PacketDistributor.ALL.noArg(),msg);
        p.getServer().getPlayerList().getPlayers().stream()
                .filter(i -> !p.getUniqueID().equals(i.getUniqueID()))
                .forEach(i -> StartupCommon.simpleChannel.send(
                        PacketDistributor.PLAYER.with((Supplier<ServerPlayerEntity>) i)
                        ,new TargetEffectMessageToClient(i.entityUniqueID
                                , i.getCapability(CapabilityElementalFire.CAPABILITY_ELEMENTAL_FIRE)
                                    .orElse(null)
                                    .getChargeLevel()
                                ,0)));


    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void renderEvent(RenderPlayerEvent.Pre e) {
        if (!(e.getEntity() instanceof PlayerEntity))
            return;
        e.getRenderer().getEntityModel().bipedHead.showModel = !e.getPlayer().isCrouching();
        e.getRenderer().getEntityModel().bipedLeftArm.showModel = !e.getPlayer().isCrouching();
        e.getRenderer().getEntityModel().bipedRightArm.showModel = !e.getPlayer().isCrouching();
        if(!PlayersBasts.containsKey(e.getPlayer().getUniqueID()))return;
        float[] basts = ((float[]) PlayersBasts.get(e.getPlayer().getUniqueID())).clone();
        PlayerModel2 model = new PlayerModel2<>(0.0F, false,(int)basts[0]);
                //if(e.getPlayer().isCrouching()){e.getRenderer().getEntityModel().bipedBody.showModel = false;e.getRenderer().getEntityModel().bipedHead.showModel = false;}else {e.getRenderer().getEntityModel().bipedBody.showModel = true;e.getRenderer().getEntityModel().bipedHead.showModel = true;}
        render = new RenderPlayer2(e.getRenderer(),model);
        //model.boobsR.showModel =true;
        //model.boobsL.showModel =true;
        double ax = -Math.atan((OppaiBound(basts[2]))/4f);
        if(basts[0]>1) {
            render.models.boobsR.rotateAngleX += ax;
            render.models.boobsL.rotateAngleX += ax;
        }
        //e.getRenderer().getEntityModel().bipedBody.func_78792_a(model.boobsR);
        //e.getRenderer().getEntityModel().bipedBody.func_78792_a(model.boobsL);
        render.func_225623_a_((AbstractClientPlayerEntity)e.getEntity(),1,e.getPartialRenderTick(),e.getMatrixStack(),e.getBuffers(),e.getLight());
        //e.getEntity().sendMessage(new TextComponentString("Name:" + e.getEntity().getName()));
        //IKonoFat fat = e.getEntityPlayer().getCapability(KonoFatProvider.Fat_CAP, null);
        //e.getEntity().sendMessage(new TextComponentString("Co:" + String.valueOf(fatClient)));
        //e.getRenderer().getMainModel().bipedRightArm.isHidden = true;


    }
    public static float OppaiBound(float t){
        return (float) (Math.sin(t*6.283184f)/Math.exp(0.5f*t));
    }
    public static float easeOutElastic(float t) {
        return easeOutElastic(t,0.0f,1.0f,1.0f);
    }
    public static float easeOutElastic(float t, float b, float c) {
        return easeOutElastic(t,b,c,1.0f);
    }
    public static float easeOutElastic(float t, float b, float c, float d) {
        float s = 1.70158f;
        float p = 0;
        float a = c;
        d=1.0f;
        if (t == 0)
            return b;
        if ((t /= d) == 1)
            return b + c;
        p = d * 0.3f;
        if (a < Math.abs(c)) {
            a = c;
            s = p / 4f;
        } else
            s = (float) (p / (2f * Math.PI) * Math.asin(c / a));
        return (float) (a * Math.pow(2, -10f * t) * Math.sin((t * d - s) * (2f * Math.PI) / p) + c + b);
    }
}
