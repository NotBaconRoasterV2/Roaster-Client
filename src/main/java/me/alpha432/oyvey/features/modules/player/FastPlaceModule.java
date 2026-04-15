package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.world.item.Items;

public class FastPlaceModule extends Module {
    public FastPlaceModule() {
        super("FastPlace", "Removes the delay for placing blocks and using items", Category.PLAYER);
    }

    @Override
    public void onTick() {
        if (nullCheck()) return;

        // Using your client's likely mapping for the right click delay
        // If this still fails, check the 'Minecraft' class for a similar field name
        mc.rightClickDelay = 0;
    }

    @Override
    public void onDisable() {
        if (mc.player != null) {
            mc.rightClickDelay = 4;
        }
    }
}
