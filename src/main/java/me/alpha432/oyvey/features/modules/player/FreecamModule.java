package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.world.phys.Vec3;

public class FreecamModule extends Module {
    private Vec3 originalPos;
    private float originalYaw, originalPitch;

    public FreecamModule() {
        super("Freecam", "Move your camera outside your body", Category.PLAYER);
    }

    @Override
    public void onEnable() {
        if (nullCheck()) {
            this.disable();
            return;
        }

        // Save the location of your real body
        originalPos = mc.player.position();
        originalYaw = mc.player.getYaw();
        originalPitch = mc.player.getPitch();

        // Enable client-side noclip
        mc.player.noPhysics = true;
    }

    @Override
    public void onTick() {
        if (nullCheck()) return;

        // Keep the player in a flying state
        mc.player.getAbilities().flying = true;
        mc.player.getAbilities().setFlySpeed(0.1f);
        
        // Ensure we can pass through blocks every tick
        mc.player.noPhysics = true;
        mc.player.setOnGround(false);
    }

    @Override
    public void onDisable() {
        if (mc.player != null) {
            // Restore your body to its original position
            if (originalPos != null) {
                mc.player.setPos(originalPos.x, originalPos.y, originalPos.z);
                mc.player.setYaw(originalYaw);
                mc.player.setPitch(originalPitch);
            }

            // Reset flight state
            if (!mc.player.isCreative()) {
                mc.player.getAbilities().flying = false;
            }

            mc.player.noPhysics = false;
            mc.player.setDeltaMovement(Vec3.ZERO);
        }
    }
}
