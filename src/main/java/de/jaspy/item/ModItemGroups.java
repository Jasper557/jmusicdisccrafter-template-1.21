package de.jaspy.item;

import de.jaspy.JMusicDiscCrafter;
import de.jaspy.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup MUSIC_DISC_CRAFTER_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(JMusicDiscCrafter.MOD_ID, "music_disc_crafter_blocks"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.MUSIC_DISC_CRAFTER))
                    .displayName(Text.translatable("itemgroup.jmusicdisccrafter.music_disc_crafter"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.MUSIC_DISC_CRAFTER);
                        entries.add(ModItems.CUSTOM_MUSIC_DISC);
                    }).build());


    public static void registerItemGroups() {
        JMusicDiscCrafter.LOGGER.info("Registering Item Groups for " + JMusicDiscCrafter.MOD_ID);
    }

}
