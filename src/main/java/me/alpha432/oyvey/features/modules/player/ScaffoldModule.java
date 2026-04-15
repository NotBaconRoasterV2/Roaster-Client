package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;

public class ScaffoldModule extends Module {
    public ScaffoldModule() {
        super("Scaffold", "Automatically places blocks under you", Category.PLAYER);
    }

    @Override
    public void onTick() {
        if (nullCheck()) return;

        // 1. Get the position directly under the player
        BlockPos belowPos = mc.player.getBlockPos().down();

        // 2. Only try to place if it's currently air (or replaceable)
        if (mc.world.getBlockState(belowPos).getMaterial().isReplaceable()) {
            
            // 3. Find a block in the hotbar
            int slot = findBlockSlot();
            if (slot == -1) return; // No blocks found

            int oldSlot = mc.player.getInventory().selectedSlot;
            mc.player.getInventory().selectedSlot = slot;

            // 4. Place the block
            placeBlock(belowPos);

            // 5. Switch back to previous tool
            mc.player.getInventory().selectedSlot = oldSlot;
        }
    }

    private void placeBlock(BlockPos pos) {
        // Simple placement logic: targets the top of the block below the target air block
        Vec3d vec = new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
        
        // Use the interaction manager to simulate a right-click on the block position
        mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, 
            new net.minecraft.util.hit.BlockHitResult(vec, Direction.UP, pos, false));
        
        mc.player.swingHand(Hand.MAIN_HAND);
    }

    private int findBlockSlot() {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.player.getInventory().getStack(i);
            if (!stack.isEmpty() && stack.getItem() instanceof BlockItem) {
                return i;
            }
        }
        return -1;
    }
}
