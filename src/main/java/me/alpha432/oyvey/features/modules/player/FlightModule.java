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

        mc.player.getAbilities().flying = true;
        // setFlySpeed is a field called flyingSpeed in these mappings
        mc.player.getAbilities().flyingSpeed = flySpeed / 10f;

        double verticalSpeed = 0;
        if (mc.options.keyJump.isDown()) {
            verticalSpeed = flySpeed;
        } else if (mc.options.keyShift.isDown()) {
            verticalSpeed = -flySpeed;
        }

        if (verticalSpeed != 0) {
            Vec3 vel = mc.player.getDeltaMovement();
            mc.player.setDeltaMovement(vel.x, verticalSpeed, vel.z);
        }
    }

    @Override
    public void onDisable() {
        if (mc.player != null) {
            if (!mc.player.isCreative()) {
                mc.player.getAbilities().flying = false;
            }
            mc.player.getAbilities().flyingSpeed = 0.05f; 
        }
    }
}
