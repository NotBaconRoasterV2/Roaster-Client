package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.Mth;
import java.util.Comparator;

public class KillAuraModule extends Module {
    public KillAuraModule() {
        // No keybind in super, matching your Module constructor
        super("KillAura", "Automatically attacks nearby entities", Category.COMBAT);
    }

    private final float range = 3.85f;
    private int hitDelay = 0;

    @Override
    public void onTick() {
        if (nullCheck()) return;

        // 1. Find the best target (closest living entity)
        LivingEntity target = getTarget();
        if (target == null) return;

        // 2. Rotate to face the target
        faceEntity(target);

        // 3. Handle attack timing (approx 6.1 APS logic)
        if (hitDelay > 0) {
            hitDelay--;
        } else {
            attackEntity(target);
            hitDelay = 10; // Adjust this number to change attack speed
        }
    }

    private void attackEntity(Entity entity) {
        // Reset sprinting for extra knockback/damage logic
        mc.player.setSprinting(false);
        
        // Use the modern interaction manager
        mc.gameMode.attack(mc.player, entity);
        mc.player.swing(InteractionHand.MAIN_HAND);
    }

    private LivingEntity getTarget() {
        return mc.level.getEntitiesByClass(LivingEntity.class, 
                mc.player.getBoundingBox().inflate(range), 
                entity -> entity != mc.player && entity.isAlive() && mc.player.canSee(entity))
                .stream()
                .min(Comparator.comparingDouble(e -> mc.player.distanceToSqr(e)))
                .orElse(null);
    }

    private void faceEntity(Entity entity) {
        double diffX = entity.getX() - mc.player.getX();
        double diffZ = entity.getZ() - mc.player.getZ();
        double diffY = (entity.getY() + entity.getEyeHeight()) - (mc.player.getY() + mc.player.getEyeHeight());

        double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);

        float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
        float pitch = (float) -(Math.atan2(diffY, diffXZ) * 180.0D / Math.PI);

        mc.player.setYRot(mc.player.getYRot() + Mth.wrapDegrees(yaw - mc.player.getYRot()));
        mc.player.setXRot(mc.player.getXRot() + Mth.wrapDegrees(pitch - mc.player.getXRot()));
    }
}
