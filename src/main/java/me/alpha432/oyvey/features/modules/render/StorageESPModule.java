package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.event.impl.render.Render3DEvent;
import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.util.render.RenderUtil;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.phys.AABB;
import java.awt.Color;

public class StorageESPModule extends Module {
    public StorageESPModule() {
        super("StorageESP", "Highlights chests and other containers", Category.RENDER);
    }

    @Subscribe
    public void onRender3D(Render3DEvent event) {
        if (nullCheck()) return;

        // Iterate through all loaded block entities (Chests, Hoppers, etc.)
        for (BlockEntity blockEntity : mc.level.blockEntityList) {
            Color color = getStorageColor(blockEntity);
            
            if (color != null) {
                AABB box = new AABB(blockEntity.getBlockPos());
                RenderUtil.drawBox(event.getContext(), box, color, 0.3f);
            }
        }
    }

    private Color getStorageColor(BlockEntity blockEntity) {
        if (blockEntity instanceof ChestBlockEntity) return Color.ORANGE;
        if (blockEntity instanceof ShulkerBoxBlockEntity) return Color.MAGENTA;
        if (blockEntity instanceof EnderChestBlockEntity) return Color.BLUE;
        if (blockEntity instanceof HopperBlockEntity) return Color.GRAY;
        return null;
    }
}
