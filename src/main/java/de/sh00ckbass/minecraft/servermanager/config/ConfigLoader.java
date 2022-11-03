package de.sh00ckbass.minecraft.servermanager.config;

import de.sh00ckbass.minecraft.servermanager.ServerManager;
import de.sh00ckbass.minecraft.servermanager.config.configs.WorldSettingsConfig;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/*******************************************************
 * Copyright (C) Tobias Zechmann tobias@sh00ckbass.de
 *
 * This file is part of ServerManager and was created at the 03.11.2022
 *
 * ServerManager can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class ConfigLoader {

    private final ServerManager plugin;
    private final Configs configs;
    private final File configFolder;

    public ConfigLoader(final ServerManager plugin) {
        this.plugin = plugin;
        this.configs = plugin.getConfigs();
        this.configFolder = new File(this.plugin.getDataFolder(), "configs");
        this.saveDefaultConfigs();
    }

    public void loadConfigs() {
        this.loadWorldSettingsConfig();
    }

    public void saveConfigs() {
        try {
            this.saveWorldSettingsConfig();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveDefaultConfigs() {
        final File worldSettingsConfig = new File(this.configFolder, "world-settings.yml");
        if (!this.configFolder.exists()) {
            this.configFolder.mkdir();
        }
        if (!worldSettingsConfig.exists()) {
            this.plugin.saveResource("configs/world-settings.yml", false);
        }
    }

    private void loadWorldSettingsConfig() {
        final File worldSettingsConfig = new File(this.configFolder, "world-settings.yml");
        final YamlConfiguration config = YamlConfiguration.loadConfiguration(worldSettingsConfig);
        final WorldSettingsConfig settingsConfig = this.configs.getWorldSettingsConfig();

        final boolean pvp = config.getBoolean("pvp");
        final boolean deathMessages = config.getBoolean("death-messages");

        settingsConfig.setPvp(pvp);
        settingsConfig.setDeathMessages(deathMessages);
    }

    private void saveWorldSettingsConfig() throws IOException {
        final File worldSettingsConfig = new File(this.configFolder, "world-settings.yml");
        final YamlConfiguration config = YamlConfiguration.loadConfiguration(worldSettingsConfig);
        final WorldSettingsConfig settingsConfig = this.configs.getWorldSettingsConfig();

        final boolean pvp = settingsConfig.isPvp();
        final boolean deathMessages = settingsConfig.isDeathMessages();

        config.set("pvp", pvp);
        config.set("death-messages", deathMessages);

        config.save(worldSettingsConfig);
    }

}
