package me.alpha432.oyvey.features.modules.hud;

import me.alpha432.oyvey.event.impl.render.Render2DEvent;
import me.alpha432.oyvey.features.modules.client.HudModule;
import me.alpha432.oyvey.features.settings.Setting;
import me.alpha432.oyvey.util.BuildConfig;
import me.alpha432.oyvey.util.TextUtil;

public class WatermarkHudModule extends HudModule {
    public Setting<String> text = str("Text", BuildConfig.NAME);
    public Setting<Boolean> fullVersion = new Setting<>("FullVersion", false);

    public WatermarkHudModule() {
        super("Watermark", "Display watermark", 100, 10);
        if (BuildConfig.USING_GIT) {
            register(fullVersion);
        }
    }

    @Override
    protected void render(Render2DEvent e) {
        super.render(e);
        
        // Define the formatted string with color tags
        String watermarkString = "{purple} Roasters {reset} | {red} RELEASE";
        
        // Process the string through TextUtil to apply the colors
        String renderedText = TextUtil.text(watermarkString).getString();

        e.getContext().drawString(mc.font,
                renderedText,
                (int) getX(), (int) getY(), -1);

        // Set width and height based on the rendered text
        setWidth(mc.font.width(renderedText));
        setHeight(mc.font.lineHeight);
    }
}
