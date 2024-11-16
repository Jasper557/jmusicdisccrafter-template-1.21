package de.jaspy.block.entity;

import de.jaspy.JMusicDiscCrafter;
import de.jaspy.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<MusicDiscCrafterEntity> MUSIC_DISC_CRAFTER_ENTITY_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(JMusicDiscCrafter.MOD_ID, "music_disc_crafter_be"),
                    FabricBlockEntityTypeBuilder.create(MusicDiscCrafterEntity::new,
                            ModBlocks.MUSIC_DISC_CRAFTER).build());
    public static void registerBlockEntities(){
        JMusicDiscCrafter.LOGGER.info("Registering Block Entities for " + JMusicDiscCrafter.MOD_ID);
    }
}
