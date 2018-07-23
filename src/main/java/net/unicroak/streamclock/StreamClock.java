package net.unicroak.streamclock;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class StreamClock extends JavaPlugin implements Listener {

    private static final String STOPPED_WORLD_KEY = "stopped-world";

    private List<String> stoppedWorldNameList;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        stoppedWorldNameList = getConfig().getStringList(STOPPED_WORLD_KEY);

        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        Block block = event.getBlock();
        if (block == null) return;

        if (stoppedWorldNameList.contains(block.getWorld().getName()) && event.getBlock().isLiquid()) {
            event.setCancelled(true);
        }
    }

}
