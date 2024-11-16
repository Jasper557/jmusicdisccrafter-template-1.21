package de.jaspy;

import de.jaspy.block.ModBlocks;
import de.jaspy.block.entity.ModBlockEntities;
import de.jaspy.item.ModItemGroups;
import de.jaspy.item.ModItems;
import de.jaspy.screen.ModScreenHandlers;
import de.jaspy.sound.ModSounds;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JMusicDiscCrafter implements ModInitializer {
	public static final String MOD_ID = "jmusicdisccrafter";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandler();
		ModSounds.registerSounds();
	}
}