package io.github.kthchew.betterhypixelchat;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class ServerConnectionListener {
    private ChatListener chatListener = new ChatListener();
    private boolean listeningForChat = false;

    @SubscribeEvent
    public void onClientConnectToServer(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        if (listeningForChat) {
            return;
        }

        // Only listen for chat messages if connected to Hypixel. The mod should not do anything otherwise.
        // Check when connecting to the server instead of checking on every chat message.
        if ((!event.isLocal) && (Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("hypixel.net"))) {
            MinecraftForge.EVENT_BUS.register(chatListener);
            listeningForChat = true;
        }
    }

    @SubscribeEvent
    public void onClientDisconnectFromServer(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        if (!listeningForChat) {
            return;
        }

        MinecraftForge.EVENT_BUS.unregister(chatListener);
        listeningForChat = false;
    }
}
