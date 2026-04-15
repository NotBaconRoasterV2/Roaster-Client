package me.alpha432.oyvey.features.modules.render;

import me.alpha432.oyvey.event.impl.render.Render3DEvent;
import me.alpha432.oyvey.event.system.Subscribe;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.util.render.RenderUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import java.awt.Color;

public class ESPModule extends Module {
    public ESPModule() {
        super("ESP", "Highlights entities through walls", Category.RENDER);
    }

    @Subscribe
    public void onRender3D(Render3DEvent event) {
        if (nullCheck()) return;

        for (Entity entity : mc.level.entitiesForRendering()) {
            if (entity == mc.player || !entity.isAlive()) continue;

            // Only highlight players for now, you can add mobs later
            if (entity instanceof Player) {
                renderESP(event, entity, Color.RED);
            }
        }
    }

    private void renderESP(Render3DEvent event, Entity entity, Color color) {
        // We use the bounding box to draw the ESP
        RenderUtil.drawBox(
            event.getContext(),
            entity.getBoundingBox(),
            color,
            0.4f // Transparency
        );
    }
}
