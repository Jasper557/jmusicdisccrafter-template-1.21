package de.jaspy.screen;

import de.jaspy.block.entity.MusicDiscCrafterEntity;
import de.jaspy.item.ModItems;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class MusicDiscCrafterScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    public final MusicDiscCrafterEntity blockentity;
    private String title = "", link = "";

    public MusicDiscCrafterScreenHandler(int syncId, PlayerInventory inventory, BlockPos blockPos){
        this(syncId, inventory, inventory.player.getWorld().getBlockEntity(blockPos),
                new ArrayPropertyDelegate(2));
    }

    public MusicDiscCrafterScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, ArrayPropertyDelegate propertyDelegate) {
        super(ModScreenHandlers.MUSIC_DISC_CRAFTER_SCREEN_HANDLER, syncId);
        checkSize(((Inventory) blockEntity), 2);
        this.inventory = ((Inventory) blockEntity);
        inventory.onOpen(playerInventory.player);
        this.blockentity = ((MusicDiscCrafterEntity) blockEntity);

        this.addSlot(new Slot(inventory, 0, 8, 52));
        this.addSlot(new Slot(inventory, 1, 152, 52));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    private void addPlayerInventory(PlayerInventory playerInventory){
        for (int i = 0; i < 3; i++) {
            for (int l = 0; l < 9; l++) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory){
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    public void createDisc(String title, String link) {
        if (this.blockentity != null) {
            this.blockentity.createCustomDisc(title, link);
        }
    }
}
