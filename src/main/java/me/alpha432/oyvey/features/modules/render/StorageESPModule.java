package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.event.impl.render.Render3DEvent;
import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.util.render.RenderUtil;
import me.alpha432.oyvey.mixin.world.IClientLevel; // Ensure this import is correct
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.phys.AABB;
import java.awt.Color;
import java.util.List;

public class StorageESPModule extends Module {
    public StorageESPModule() {
        super("StorageESP", "Highlights chests and containers", Category.RENDER);
    }

    @Subscribe
    public void onRender3D(Render3DEvent event) {
        if (nullCheck()) return;

        // Using the Mixin to get the hidden list
        List<BlockEntity> blockEntities = ((IClientLevel) mc.level).getBlockEntities();

        for (BlockEntity blockEntity : blockEntities) {
            Color color = getStorageColor(blockEntity);
            
            if (color != null) {
                AABB box = new AABB(blockEntity.getBlockPos());
                
                RenderUtil.drawBox(
                    event.getMatrices(), 
                    box, 
                    color, 
                    true
                );
            }
        }
    }

    private Color getStorageColor(BlockEntity blockEntity) {
        if (blockEntity instanceof ChestBlockEntity) return new Color(255, 165, 0, 100);
        if (blockEntity instanceof ShulkerBoxBlockEntity) return new Color(255, 0, 255, 100);
        if (blockEntity instanceof EnderChestBlockEntity) return new Color(0, 0, 255, 100);
        if (blockEntity instanceof HopperBlockEntity) return new Color(128, 128, 128, 100);
        return null;
    }
}
