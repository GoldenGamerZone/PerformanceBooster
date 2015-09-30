package de.goldengamerzone.performancebooster.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class TPS {
	private static Boolean startedScanning = false;
	private static Integer taskID;

	private static Long lastMillis;

	private static ArrayList<Long> delays = new ArrayList<Long>();

	public static void startScanning(Plugin plugin) {
		if (!startedScanning) {
			startedScanning = true;
			lastMillis = System.currentTimeMillis();
			taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
				public void run() {
					Long delay = System.currentTimeMillis() - lastMillis;
					if (delays.size() >= 20) {
						delays.remove(delays.get(0));
					}
					if (delay < 50L) {
						delay = 50L;
					}
					delays.add(delay);
					lastMillis = System.currentTimeMillis();
				}
			}, 1L, 1L);
		}
	}

	public static Boolean alreadyScanning() {
		return startedScanning;
	}

	public static void stopScanning() {
		if (startedScanning) {
			Bukkit.getScheduler().cancelTask(taskID);
			taskID = null;
			delays.clear();
			lastMillis = null;
		}
	}

	public static Double getTPS() {
		if (startedScanning) {
			if (delays.size() != 0) {
				if (delays.size() == 20) {
					Double toReturn = 0.0;
					for (Long delay : delays) {
						toReturn = toReturn + delay.doubleValue();
					}
					toReturn = (1000.0 / toReturn) * 20.0;
					return toReturn;
				} else {
					Double average = 0.0;
					Double dividend = 0.0;
					for (Long delay : delays) {
						dividend++;
						average = average + delay.doubleValue();
					}
					average = (average / dividend);
					Double toReturn = (average * 20.0);
					toReturn = (1000.0 / toReturn) * 20.0;
					return toReturn;
				}
			} else {
				return 20.0;
			}
		}
		return null;
	}
}
