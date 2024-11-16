package de.jaspy;

import de.jaspy.screen.ModScreenHandlers;
import de.jaspy.screen.MusicDiscCrafterScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class JMusicDiscCrafterClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.MUSIC_DISC_CRAFTER_SCREEN_HANDLER, MusicDiscCrafterScreen::new);
    }
}
