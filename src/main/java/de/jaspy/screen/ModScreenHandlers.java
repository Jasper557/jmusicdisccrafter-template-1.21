package de.jaspy.screen;

import de.jaspy.JMusicDiscCrafter;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static final ScreenHandlerType<MusicDiscCrafterScreenHandler> MUSIC_DISC_CRAFTER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(JMusicDiscCrafter.MOD_ID, "music_crafter"),
                    new ExtendedScreenHandlerType<>(MusicDiscCrafterScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandler(){
        JMusicDiscCrafter.LOGGER.info("Registering Screen Handlers for " + JMusicDiscCrafter.MOD_ID);
    }
}
