package de.sh00ckbass.minecraft.servermanager.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Conditions;
import co.aikar.commands.annotation.Default;
import de.sh00ckbass.minecraft.servermanager.ServerManager;
import de.sh00ckbass.minecraft.servermanager.config.configs.WorldSettingsConfig;
import org.bukkit.command.CommandSender;

/*******************************************************
 * Copyright (C) Tobias Zechmann tobias@sh00ckbass.de
 *
 * This file is part of ServerManager and was created at the 04.11.2022
 *
 * ServerManager can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */

@CommandAlias("world")
@CommandPermission("op")
@Conditions("rights")
public class WorldSettingsCommand extends BaseCommand {

    private final ServerManager plugin;
    private final WorldSettingsConfig config;

    public WorldSettingsCommand(final ServerManager plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfigs().getWorldSettingsConfig();
    }

    @Default
    public void listSettings(final CommandSender sender) {
        sender.sendMessage("§7----§eWorld-Settings§7----");
        sender.sendMessage("§ePvP§7: §e" + this.getToggle(this.config.isPvp()));
        sender.sendMessage("§eDeath-Messages§7: §e" + this.getToggle(this.config.isDeathMessages()));
        sender.sendMessage("§eGlobal-Death-Messages§7: §e" + this.getToggle(this.config.isGlobalDeathMessages()));
        sender.sendMessage("§7----§eWorld-Settings§7----");
    }

    @CommandAlias("pvp")
    public void togglePvp(final CommandSender sender) {
        this.config.setPvp(!this.config.isPvp());
        sender.sendMessage("§7Turned PvP §e" + this.getToggle(this.config.isPvp()) + "§7.");
    }

    @CommandAlias("deathmessages")
    public void toggleDeathMessages(final CommandSender sender) {
        this.config.setDeathMessages(!this.config.isDeathMessages());
        sender.sendMessage("§7Turned Death-Messages §e" + this.getToggle(this.config.isDeathMessages()) + "§7.");
    }

    @CommandAlias("globaldeathmessages")
    public void toggleGlobalDeathMessages(final CommandSender sender) {
        this.config.setGlobalDeathMessages(!this.config.isGlobalDeathMessages());
        sender.sendMessage("§7Turned Global-Death-Messages §e" +
                this.getToggle(this.config.isGlobalDeathMessages()) + "§7.");
    }

    private String getToggle(final boolean condition) {
        if (condition) {
            return "on";
        }
        return "off";
    }

}
