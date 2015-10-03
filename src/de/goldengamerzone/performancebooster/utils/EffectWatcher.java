package de.goldengamerzone.performancebooster.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.Plugin;

public class EffectWatcher implements Listener {
	
	private static Plugin plugin;
	private static ArrayList<Location> locs = new ArrayList<Location>();
	
	public static void setPlugin(Plugin pl) {
		plugin = pl;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onEffect(EntityExplodeEvent e) {
		if (!e.isCancelled()) {
			Boolean cancel = false;
			for (Location loc : locs) {
				if (loc.distance(e.getLocation()) > 10) {
					cancel = true;
				}
			}
			if (cancel) {
				e.setCancelled(true);
				for (Block b : e.blockList()) {
					b.breakNaturally();
				}
			} else {
				final FinalObject<Location> finalLoc = new FinalObject<Location>(e
						.getLocation());
				locs.add(finalLoc.get());
				Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
					public void run() {
						locs.remove(finalLoc.get());
					}
				}, 20L);
			}
		}
	}
}
