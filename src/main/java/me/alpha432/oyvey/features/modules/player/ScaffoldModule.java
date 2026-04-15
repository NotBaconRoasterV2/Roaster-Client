package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.BlockHitResult;

public class ScaffoldModule extends Module {
    public ScaffoldModule() {
        super("Scaffold", "Automatically places blocks under you", Category.PLAYER);
    }

    @Override
    public void onTick() {
        if (nullCheck()) return;

        // In your client, use blockPosition() instead of getBlockPos()
        BlockPos belowPos = mc.player.blockPosition().below();

        // Check if block is air/replaceable
        if (mc.level.getBlockState(belowPos).getMaterial().isReplaceable()) {
            
            int slot = findBlockSlot();
            if (slot == -1) return;

            int oldSlot = mc.player.getInventory().selected;
            mc.player.getInventory().selected = slot;

            placeBlock(belowPos);

            mc.player.getInventory().selected = oldSlot;
        }
    }

    private void placeBlock(BlockPos pos) {
        Vec3 vec = new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
        
        // Using standard multi-version friendly placement
        mc.gameMode.useItemOn(mc.player, InteractionHand.MAIN_HAND, 
            new BlockHitResult(vec, Direction.UP, pos, false));
        
        mc.player.swing(InteractionHand.MAIN_HAND);
    }

    private int findBlockSlot() {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = mc.player.getInventory().getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof BlockItem) {
                return i;
            }
        }
        return -1;
    }
}
