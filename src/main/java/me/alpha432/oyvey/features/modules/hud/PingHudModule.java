package me.alpha432.oyvey.features.modules.hud;

import me.alpha432.oyvey.event.impl.render.Render2DEvent;
import me.alpha432.oyvey.features.modules.client.HudModule;
import net.minecraft.ChatFormatting;
import net.minecraft.client.multiplayer.PlayerInfo;

public class PingHudModule extends HudModule {
    public PingHudModule() {
        super("Ping", "Displays server latency", 40, 10);
    }

    @Override
    protected void render(Render2DEvent e) {
        super.render(e);
        int latency = 0;

        if (mc.getConnection() != null && mc.player != null) {
            PlayerInfo info = mc.getConnection().getPlayerInfo(mc.player.getUUID());
            if (info != null) latency = info.getLatency();
        }

        String ping = "Ping " + ChatFormatting.WHITE + latency + "ms";
        setWidth(mc.font.width(ping));
        
        e.getContext().drawString(mc.font, ping, (int) getX(), (int) getY(), -1);
    }
}
