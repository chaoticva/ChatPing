package me.chaoticva.chatping.mixin;

import me.chaoticva.chatping.ChatPing;
import me.chaoticva.chatping.config.ModConfig;
import me.chaoticva.chatping.sound.ModSounds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.s2c.play.ChatMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(ClientPlayNetworkHandler.class)
public class OnChatMixin {
    @Inject(at = @At("HEAD"), method = "onChatMessage")
    private void onChatMessage(ChatMessageS2CPacket packet, CallbackInfo ci) {
        handleMessage(packet.body().content());
    }

    @Inject(at = @At("HEAD"), method = "onGameMessage")
    private void onGameMessage(GameMessageS2CPacket packet, CallbackInfo ci) {
        handleMessage(packet.content().getString());
    }

    @Unique
    private void handleMessage(String message) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        ModConfig config = ChatPing.config;
        AtomicBoolean containsName = new AtomicBoolean(false);

        for (String name : config.names) {
            if (config.ignoreCapitalization) {
                if (!message.toLowerCase().contains(name.toLowerCase())) continue;
            } else {
                if (!message.contains(name)) continue;
            }

            containsName.set(true);
            break;
        }

        if (player == null) return;
        if (!containsName.get()) return;

        float volume = config.volume / 100f;
        float pitch = config.pitch / 100f;

        switch (config.sound) {
            case BLOP -> player.playSound(ModSounds.BLOP, volume, pitch);
            case GUM -> player.playSound(ModSounds.GUM, volume, pitch);
            case PING -> player.playSound(ModSounds.PING, volume, pitch);
            case PLING -> player.playSound(ModSounds.PLING, volume, pitch);
            case POP -> player.playSound(ModSounds.POP, volume, pitch);
            case TING -> player.playSound(ModSounds.TING, volume, pitch);
        }
    }
}
