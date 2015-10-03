package de.goldengamerzone.performancebooster;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import de.goldengamerzone.performancebooster.utils.EffectWatcher;
import de.goldengamerzone.performancebooster.utils.TPS;

public class MainClass extends JavaPlugin implements Listener {
	public void onEnable() {
		TPS.startScanning(this);
		EffectWatcher.setPlugin(this);
	}
	
	public Plugin getPlugin() {
		return this;
	}
}
