package me.chaoticva.chatping.sound;


import me.chaoticva.chatping.ChatPing;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent BLOP = registerSoundEvent("blop");
    public static final SoundEvent GUM = registerSoundEvent("gum");
    public static final SoundEvent PING = registerSoundEvent("ping");
    public static final SoundEvent PLING = registerSoundEvent("pling");
    public static final SoundEvent POP = registerSoundEvent("pop");
    public static final SoundEvent TING = registerSoundEvent("ting");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier identifier = Identifier.of(ChatPing.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void registerSounds() {

    }
}
