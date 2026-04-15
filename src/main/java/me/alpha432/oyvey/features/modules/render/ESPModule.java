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
        super("ESP", "Highlights players through walls", Category.RENDER);
    }

    @Subscribe
    public void onRender3D(Render3DEvent event) {
        if (nullCheck()) return;

        for (Entity entity : mc.level.entitiesForRendering()) {
            if (entity == mc.player || !entity.isAlive()) continue;

            if (entity instanceof Player) {
                // event.getMatrices() is the common name in OyVey ports
                RenderUtil.drawBox(
                    event.getMatrices(), 
                    entity.getBoundingBox(), 
                    new Color(255, 0, 0, 100), 
                    true
                );
            }
        }
    }
}
