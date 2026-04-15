package me.alpha432.oyvey.features.modules.hud;

import me.alpha432.oyvey.event.impl.render.Render2DEvent;
import me.alpha432.oyvey.features.modules.client.HudModule;
import net.minecraft.ChatFormatting;

public class FpsHudModule extends HudModule {
    public FpsHudModule() {
        super("FPS", "Displays your current FPS", 40, 10);
    }

    @Override
    protected void render(Render2DEvent e) {
        super.render(e); // Needed for dragging/outline in HudEditor
        String fps = "FPS " + ChatFormatting.WHITE + mc.getFps();
        
        // Adjust bounds based on text width
        setWidth(mc.font.width(fps));
        
        e.getContext().drawString(mc.font, fps, (int) getX(), (int) getY(), -1);
    }
}
