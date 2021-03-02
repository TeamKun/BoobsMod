package net.kunmc.lab.boobsmod;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.kunmc.lab.boobsmod.mbe60_network_messages.AirstrikeMessageToServer;
import net.kunmc.lab.boobsmod.mbe60_network_messages.StartupCommon;
import net.kunmc.lab.boobsmod.mbe60_network_messages.TargetEffectMessageToClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.MPMRenderEntityUtil;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.serializers.StringArgumentSerializer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.PacketDistributor;
import org.apache.logging.log4j.LogManager;
import net.minecraft.command.Commands;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.minecraft.command.arguments.EntityArgument;

public class Cmd {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        //dispatcher.register(Commands.literal("").then(Commands.argument("player", players()).executes(Cmd::run)));
        dispatcher.register(Commands.literal("boobsmod")
                                .then(Commands.argument("player", EntityArgument.players())
                                        .then(Commands.argument("number", IntegerArgumentType.integer())
                                                    .executes(Cmd::run))));
    }

    private static int run(CommandContext<CommandSource> source) throws CommandSyntaxException {
        source.getSource().sendFeedback(new TranslationTextComponent("command.dirtmod"), true);
        //LogManager.getLogger().info(source.getInput());
        List<ServerPlayerEntity> player = (List<ServerPlayerEntity>) EntityArgument.getPlayers(source,"player");
        int bast = IntegerArgumentType.getInteger(source,"number");
        //LogManager.getLogger().info(bast);
        //LogManager.getLogger().info(player);
        Minecraft mc = Minecraft.getInstance();
        //LogManager.getLogger().info(Minecraft.getInstance().player.getName());
        EntityRendererManager manager = mc.getRenderManager();
        Map<String, PlayerRenderer> map = manager.getSkinMap();
        ElementalFire fireInterface = player.get(0).getCapability(CapabilityElementalFire.CAPABILITY_ELEMENTAL_FIRE).orElse(null);

        //LogManager.getLogger().info(fireInterface.getChargeLevel());
        fireInterface.setCharge(bast);
        //LogManager.getLogger().info(fireInterface.getChargeLevel());

        //LogManager.getLogger().info("Cmd:"+Boobsmod.noob);
        Boobsmod.noob = 910;
        TargetEffectMessageToClient msg = new TargetEffectMessageToClient(player.get(0).entityUniqueID,bast,0);
        StartupCommon.simpleChannel.send(PacketDistributor.ALL.noArg(),msg);
/*
        for (String type : map.keySet()) {
            PlayerRenderer render = map.get(type);
            ModelRenderer cynthia = new ModelRenderer((net.minecraft.client.renderer.model.Model)render.getEntityModel());
            cynthia.func_78784_a(20, 20);
            cynthia.func_228300_a_(-3.0F, 1.0F, -3.0F, 6.0F, 1.0F, 1.0F);
            cynthia.func_78784_a(19, 21);
            cynthia.func_228300_a_(-4.0F, 2.0F, -3.0F, 8.0F, 3.0F, 1.0F);
            /*
            PlayerModel model = render.getEntityModel();
            model.bipedBody.func_78784_a(20, 20);
            model.bipedBody.func_228300_a_(-3.0F, 1.0F, -3.0F, 6.0F, 1.0F, 1.0F);
            model.bipedBody.func_78784_a(19, 21);
            model.bipedBody.func_228300_a_(-4.0F, 2.0F, -3.0F, 8.0F, 3.0F, 1.0F);
            boolean hasMPMLayers = false;
            addLayers(render);


        }

        */
        return 1;

    }
    private static int check(CommandContext<CommandSource> source) throws CommandSyntaxException {
        source.getSource().sendFeedback(new TranslationTextComponent("command.dirtmod"), true);
        LogManager.getLogger().info(source.getInput());
        ServerPlayerEntity player =EntityArgument.getPlayer(source,"player");
        int bast = IntegerArgumentType.getInteger(source,"number");
        LogManager.getLogger().info(bast);
        LogManager.getLogger().info(player);
        Minecraft mc = Minecraft.getInstance();
        LogManager.getLogger().info(Minecraft.getInstance().player.getName());
        EntityRendererManager manager = mc.getRenderManager();
        Map<String, PlayerRenderer> map = manager.getSkinMap();
        ElementalFire fireInterface = ((ServerPlayerEntity)player).getCapability(CapabilityElementalFire.CAPABILITY_ELEMENTAL_FIRE).orElse(null);
        LogManager.getLogger().info(fireInterface.getChargeLevel());
/*
        for (String type : map.keySet()) {
            PlayerRenderer render = map.get(type);
            ModelRenderer cynthia = new ModelRenderer((net.minecraft.client.renderer.model.Model)render.getEntityModel());
            cynthia.func_78784_a(20, 20);
            cynthia.func_228300_a_(-3.0F, 1.0F, -3.0F, 6.0F, 1.0F, 1.0F);
            cynthia.func_78784_a(19, 21);
            cynthia.func_228300_a_(-4.0F, 2.0F, -3.0F, 8.0F, 3.0F, 1.0F);
            /*
            PlayerModel model = render.getEntityModel();
            model.bipedBody.func_78784_a(20, 20);
            model.bipedBody.func_228300_a_(-3.0F, 1.0F, -3.0F, 6.0F, 1.0F, 1.0F);
            model.bipedBody.func_78784_a(19, 21);
            model.bipedBody.func_228300_a_(-4.0F, 2.0F, -3.0F, 8.0F, 3.0F, 1.0F);
            boolean hasMPMLayers = false;
            addLayers(render);


        }

        */
        return 1;

    }
    private static void addLayers(PlayerRenderer playerRender) {
        List<LayerRenderer> list = MPMRenderEntityUtil.getLayers((LivingRenderer)playerRender);
        list.removeIf(layer -> layer instanceof net.minecraft.client.renderer.entity.layers.CapeLayer);

    }
}
