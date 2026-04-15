package me.alpha432.oyvey.features.modules.combat;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.Mth;

public class KillAuraModule extends Module {
    public KillAuraModule() {
        super("KillAura", "Automatically attacks nearby entities", Category.COMBAT);
    }

    private final float range = 3.85f;
    private int hitDelay = 0;

    @Override
    public void onTick() {
        if (nullCheck()) return;

        LivingEntity target = getTarget();
        if (target == null) return;

        faceEntity(target);

        if (hitDelay > 0) {
            hitDelay--;
        } else {
            attackEntity(target);
            hitDelay = 10; 
        }
    }

    private void attackEntity(Entity entity) {
        mc.player.setSprinting(false);
        mc.gameMode.attack(mc.player, entity);
        mc.player.swing(InteractionHand.MAIN_HAND);
    }

    private LivingEntity getTarget() {
        LivingEntity closestEntity = null;
        double closestDistance = range * range;

        // Use entitiesForRendering() as it is public in ClientLevel
        for (Entity entity : mc.level.entitiesForRendering()) {
            if (!(entity instanceof LivingEntity living)) continue;
            
            // Changed .canSee() to .hasLineOfSight() to match Mojang mappings
            if (living == mc.player || !living.isAlive() || !mc.player.hasLineOfSight(living)) continue;

            double dist = mc.player.distanceToSqr(living);

            if (dist < closestDistance) {
                closestDistance = dist;
                closestEntity = living;
            }
        }
        return closestEntity;
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
