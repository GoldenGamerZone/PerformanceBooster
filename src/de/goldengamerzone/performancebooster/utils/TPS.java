package de.goldengamerzone.performancebooster.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class TPS {
	private static Boolean startedScanning = false;
	private static Integer taskID;

	public static void startScanning(Plugin plugin) {
		if (!startedScanning) {
			startedScanning = true;
			taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
				public void run() {
					// TODO TPS berechnen
				}
			}, 1L, 1L);
		}
	}

	public static Boolean alreadyScanning() {
		return startedScanning;
	}

	public static void stopScanning() {
		Bukkit.getScheduler().cancelTask(taskID);
		taskID = null;
	}

	public static Integer getTPS() {
		// TODO TPS returnen
		return 20;
	}
}
