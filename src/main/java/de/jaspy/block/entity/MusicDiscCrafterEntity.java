package de.jaspy.block.entity;

import de.jaspy.item.ModItems;
import de.jaspy.networking.BlockPosPayload;
import de.jaspy.screen.MusicDiscCrafterScreenHandler;
import de.jaspy.sound.YouTubeMP3;
import java.io.File;
import java.io.IOException;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ws.schild.jave.EncoderException;

public class MusicDiscCrafterEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    private static final Integer INPUT_SLOT = 0;
    private static final Integer OUTPUT_SLOT = 1;

    public MusicDiscCrafterEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MUSIC_DISC_CRAFTER_ENTITY_BLOCK_ENTITY, pos, state);
    }

    @Override
    public Object getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Music Disc Crafter");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, this.inventory, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, this.inventory, registryLookup);
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        System.out.println(syncId);
        return new MusicDiscCrafterScreenHandler(syncId, playerInventory, this, null);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient){
            return;
        }

    }

    public void markUpdated() {
        System.out.println("mark updated");
        this.markDirty();
        this.world.updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), Block.NOTIFY_ALL);
    }


    private void craftItem() {
        if (!isOutputSlotEmpty()) return;
        ItemStack result = createCustomMusicDisc();

        this.setStack(OUTPUT_SLOT, result);
        this.markUpdated();
    }

    public void createCustomDisc(String title, String youtubeLink) {
        try {
            String safeName = title.replaceAll("[^a-zA-Z0-9]", "_").toLowerCase();
            File modsDir = new File("mods");
            File musicDir = new File(modsDir, "custom_music");
            musicDir.mkdirs();

            String outputPath = new File(musicDir, safeName + ".ogg").getAbsolutePath();

            new Thread(() -> {
                try {
                    YouTubeMP3.downloadMP3(youtubeLink, outputPath);
                } catch (EncoderException | IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }).start();

            craftItem();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ItemStack createCustomMusicDisc() {
        ItemStack disc = new ItemStack(ModItems.CUSTOM_MUSIC_DISC);
        return disc;
    }

    private boolean isOutputSlotEmpty() {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }
}
