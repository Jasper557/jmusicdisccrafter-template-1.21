package de.jaspy.sound;

import de.jaspy.JMusicDiscCrafter;
import net.minecraft.registry.*;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent CUSTOM_MUSIC_DISC = registerSoundEvent("custom_music_disc");

    private static SoundEvent registerSoundEvent(String name){
        Identifier id = Identifier.of(JMusicDiscCrafter.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds(){
        JMusicDiscCrafter.LOGGER.info("Registering Mod Sounds for " + JMusicDiscCrafter.MOD_ID);
    }
}
