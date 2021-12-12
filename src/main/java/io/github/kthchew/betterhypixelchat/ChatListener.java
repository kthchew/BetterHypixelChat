package io.github.kthchew.betterhypixelchat;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatListener {
    @SubscribeEvent
    public void onChatReceive(ClientChatReceivedEvent event) {
        String message = event.message.getFormattedText();

        // Fix non chat
        // Example: 'Player123: hello'
        if (message.matches("^§7([^\\s]+): .*")) {
            message = message.replaceFirst(":", ":§r");
            event.setCanceled(true);
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
            return;
        }

        // Fix PM chat
        // Example: 'From [OWNER] hypixel: hello'
        if (message.matches("^§dFrom .*§7: .*")) {
            message = message.replaceFirst(":", ":§r");
            event.setCanceled(true);
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
        }
    }
}
