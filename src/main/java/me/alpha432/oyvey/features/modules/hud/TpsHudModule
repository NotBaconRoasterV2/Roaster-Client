package me.alpha432.oyvey.features.modules.hud;

import me.alpha432.oyvey.event.impl.render.Render2DEvent;
import me.alpha432.oyvey.features.modules.client.HudModule;
import net.minecraft.ChatFormatting;

public class TpsHudModule extends HudModule {
    public TpsHudModule() {
        super("TPS", "Displays server ticks per second", 40, 10);
    }

    @Override
    protected void render(Render2DEvent e) {
        super.render(e);
        
        // Note: You need a TPS Manager to calculate this value accurately.
        // For now, we'll display a placeholder or 20.0
        float tpsValue = 20.0f; 
        
        String tps = "TPS " + ChatFormatting.WHITE + String.format("%.2f", tpsValue);
        setWidth(mc.font.width(tps));

        e.getContext().drawString(mc.font, tps, (int) getX(), (int) getY(), -1);
    }
}
