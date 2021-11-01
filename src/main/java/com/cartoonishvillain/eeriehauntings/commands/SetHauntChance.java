package com.cartoonishvillain.eeriehauntings.commands;

import com.cartoonishvillain.eeriehauntings.components.PlayerComponent;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.GameProfileArgument;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;

import static com.cartoonishvillain.eeriehauntings.components.ComponentStarter.PLAYERCOMPONENTINSTANCE;

public class SetHauntChance {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("eeriehauntings").then(Commands.literal("sethauntchance")
                .requires(cs -> {return cs.hasPermission(2);})
                .then(Commands.argument("target", GameProfileArgument.gameProfile()).then(Commands.argument("chance", FloatArgumentType.floatArg(0, 100)).executes(context -> {
                    return setHauntChance(context.getSource(), GameProfileArgument.getGameProfiles(context, "target"), FloatArgumentType.getFloat(context, "chance"));
                }))))

        );
    }


    private static int setHauntChance(CommandSourceStack source, Collection<GameProfile> profiles, float chance){
        for(GameProfile gameProfile : profiles){
            ServerPlayer serverPlayerEntity = source.getServer().getPlayerList().getPlayer(gameProfile.getId());
            PlayerComponent h = PLAYERCOMPONENTINSTANCE.get(serverPlayerEntity);
                h.setHauntChance(chance);
        }
        source.sendSuccess(new TranslatableComponent("hauntchance.eeriehauntings.success", chance), false);
        return 0;
    }
}
