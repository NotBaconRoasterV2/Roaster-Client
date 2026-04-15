package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.BlockItem;

public class FastPlaceModule extends Module {
    public FastPlaceModule() {
        super("FastPlace", "Removes the delay for placing blocks and using items", Category.PLAYER);
    }

    @Override
    public void onTick() {
        // Stop if the world/player isn't loaded
        if (nullCheck()) return;

        /* * Setting itemUseCooldown (or rightClickDelay) to 0 every tick 
         * allows for machine-gun speed placement/usage.
         */
        
        // Option A: Apply to EVERYTHING
        mc.itemUseCooldown = 0;

        /* * Option B: If your client base uses an accessor for the old 'rightClickDelay' name:
         * ((IMinecraft)mc).setRightClickDelay(0);
         */
    }

    @Override
    public void onDisable() {
        // Optional: Reset it to the default Minecraft delay when you turn the module off
        if (mc.player != null) {
            mc.itemUseCooldown = 4;
        }
    }
}
