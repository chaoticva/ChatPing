package me.chaoticva.chatping.config;

import me.chaoticva.chatping.ChatPing;
import me.chaoticva.chatping.sound.Sound;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import java.util.ArrayList;
import java.util.List;

@Config(name = ChatPing.MOD_ID)
public class ModConfig implements ConfigData {
    public List<String> names = new ArrayList<>();

    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public Sound sound = Sound.PING;

    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int volume = 50;

    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int pitch = 100;
}
