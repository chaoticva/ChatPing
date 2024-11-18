package me.chaoticva.chatping;

import me.chaoticva.chatping.config.ModConfig;
import me.chaoticva.chatping.sound.ModSounds;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;

public class ChatPing implements ModInitializer {
    public static final String MOD_ID = "chatping";
    public static ModConfig config;

    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        ModSounds.registerSounds();
    }
}
