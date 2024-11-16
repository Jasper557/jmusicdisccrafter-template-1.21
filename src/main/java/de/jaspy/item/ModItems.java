package de.jaspy.item;

import de.jaspy.JMusicDiscCrafter;
import de.jaspy.sound.ModSounds;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item CUSTOM_MUSIC_DISC = registerItem("custom_music_disc", 
        new CustomMusicDisc(15, ModSounds.CUSTOM_MUSIC_DISC, 
            new Item.Settings().maxCount(1).fireproof(), 200));

    private static Item registerItem(String name, Item item) {
        // Register the custom music disc item
        return Registry.register(Registries.ITEM, Identifier.of(JMusicDiscCrafter.MOD_ID, name), item);
    }

    public static void registerModItems() {
        JMusicDiscCrafter.LOGGER.info("Registering Mod Items for " + JMusicDiscCrafter.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            //add Items
        });
    }
}
