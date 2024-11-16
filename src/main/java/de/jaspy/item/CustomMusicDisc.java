package de.jaspy.item;

import net.minecraft.block.entity.JukeboxBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.sound.SoundEvent;

public class CustomMusicDisc extends Item {
    private final int comparatorOutput;
    private final SoundEvent sound;

    public CustomMusicDisc(int comparatorOutput, SoundEvent sound, Settings settings, int lengthInSeconds) {
        super(settings);
        this.comparatorOutput = comparatorOutput;
        this.sound = sound;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        System.out.println("Used");
        if(context.getWorld().getBlockEntity(context.getBlockPos()) instanceof JukeboxBlockEntity jukebox) {
            System.out.println("instanceof Juke");
            jukebox.setDisc(context.getStack().copy());
            jukebox.reloadDisc();
        }
        return super.useOnBlock(context);
    }
}
