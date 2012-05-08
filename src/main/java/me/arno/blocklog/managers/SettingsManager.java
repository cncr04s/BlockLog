package me.arno.blocklog.managers;

import java.io.File;

import me.arno.blocklog.BlockLog;
import me.arno.blocklog.Config;
import me.arno.blocklog.logs.LogType;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class SettingsManager {
	public boolean isLoggingEnabled(World world, LogType type) {
		if(type == LogType.EXPLOSION_CREEPER || type == LogType.EXPLOSION_GHAST || type == LogType.EXPLOSION_TNT)
			type = LogType.EXPLOSION;
		
		Config config = new Config("world" + File.separator + world.getName() + ".yml");
		
		return config.getConfig().getBoolean(type.name(), false);
	}
	
	public FileConfiguration getConfig() {
		return BlockLog.plugin.getConfig();
	}
	
	public int getBlockSaveDelay() {
		return getConfig().getInt("blocklog.delay");
	}
	
	public boolean isAutoSaveEnabled() {
		return getConfig().getBoolean("blocklog.autosave.enabled");
	}
	
	public int getAutoSaveBlocks() {
		return getConfig().getInt("blocklog.autosave.blocks");
	}
	
	public boolean isReportsEnabled() {
		return getConfig().getBoolean("blocklog.reports");
	}
	
	public boolean isUpdatesEnabled() {
		return getConfig().getBoolean("blocklog.updates");
	}
	
	public boolean isMetricsEnabled() {
		return getConfig().getBoolean("blocklog.metrics");
	}
	
	public String getDateFormat() {
		return getConfig().getString("blocklog.dateformat");
	}
	
	public Material getWand() {
		return Material.getMaterial(getConfig().getInt("blocklog.wand"));
	}
	
	public int getMaxResults() {
		return getConfig().getInt("blocklog.results");
	}
}