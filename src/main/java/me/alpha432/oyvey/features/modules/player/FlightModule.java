package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.world.phys.Vec3;

public class FlightModule extends Module {
    public FlightModule() {
        super("Flight", "Allows you to fly and move vertically", Category.PLAYER);
    }

    private final float flySpeed = 0.5f;

    @Override
    public void onTick() {
        if (nullCheck()) return;

        // Enable flying ability
        mc.player.getAbilities().flying = true;
        mc.player.getAbilities().setFlySpeed(flySpeed / 10f);

        // Vertical movement logic
        double verticalSpeed = 0;
        if (mc.options.keyJump.isDown()) {
            verticalSpeed = flySpeed;
        } else if (mc.options.keyShift.isDown()) {
            verticalSpeed = -flySpeed;
        }

        // Apply vertical velocity so you can actually move up and down
        if (verticalSpeed != 0) {
            Vec3 vel = mc.player.getDeltaMovement();
            mc.player.setDeltaMovement(vel.x, verticalSpeed, vel.z);
        }
    }

    @Override
    public void onDisable() {
        if (mc.player != null) {
            // Reset flying state
            if (!mc.player.isCreative()) {
                mc.player.getAbilities().flying = false;
            }
            // Stop movement so you don't drift after disabling
            mc.player.getAbilities().setFlySpeed(0.05f); 
        }
    }
}
