package io.github.kthchew.betterhypixelchat;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatListener {
    @SubscribeEvent
    public void onChatReceive(ClientChatReceivedEvent event) {
        String message = event.message.getFormattedText();

        // Fix non chat
        // Example: '§r§7Player123§7§r§7: hello§r'
        // But in SkyBlock, chat does not start with a RESET formatter: '§7Player123§7§r§7: hello§r'
        String nonRegex = "^" + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + ".*" + EnumChatFormatting.GRAY + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + ": .*" + EnumChatFormatting.RESET;
        String skyBlockNonRegex = "^" + EnumChatFormatting.GRAY + ".*" + EnumChatFormatting.GRAY + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + ": .*" + EnumChatFormatting.RESET;

        if (message.matches(nonRegex) || message.matches(skyBlockNonRegex)) {
            message = message.replaceFirst(":", ":" + EnumChatFormatting.RESET);
            event.setCanceled(true);
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
            return;
        }

        // Fix PM chat
        // Example: 'From [OWNER] hypixel: hello'
        String pmRegex = EnumChatFormatting.LIGHT_PURPLE + "From .*" + EnumChatFormatting.GRAY + ": .*";
        if (message.matches(pmRegex)) {
            message = message.replaceFirst(":", ":§r");
            event.setCanceled(true);
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
        }
    }
}
