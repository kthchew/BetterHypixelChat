package io.github.kthchew.betterhypixelchat;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "betterhypixelchat", name = "Better Hypixel Chat", version = "1.0", clientSideOnly = true)
public class BetterHypixelChat {

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ServerConnectionListener());
    }
}
