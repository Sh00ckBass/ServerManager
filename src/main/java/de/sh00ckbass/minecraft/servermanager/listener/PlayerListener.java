package de.sh00ckbass.minecraft.servermanager.listener;

import de.sh00ckbass.minecraft.servermanager.ServerManager;
import de.sh00ckbass.minecraft.servermanager.config.configs.WorldSettingsConfig;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

/*******************************************************
 * Copyright (C) Tobias Zechmann tobias@sh00ckbass.de
 *
 * This file is part of ServerManager and was created at the 04.11.2022
 *
 * ServerManager can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */
public class PlayerListener implements Listener {

    private final ServerManager plugin;
    private final WorldSettingsConfig worldConfig;

    public PlayerListener(final ServerManager plugin) {
        this.plugin = plugin;
        this.worldConfig = plugin.getConfigs().getWorldSettingsConfig();
    }

    @EventHandler
    public void onPlayerInteractPlayer(final EntityDamageByEntityEvent event) {
        final Entity damager = event.getDamager();
        final Entity defender = event.getEntity();
        if (!(damager instanceof Player) || !(defender instanceof Player)) {
            return;
        }
        final Player attacker = (Player) damager;

        if (this.worldConfig.isPvp()) {
            event.setCancelled(true);
            attacker.sendActionBar(Component.text("§4Pvp is disabled."));
        }
    }

    @EventHandler
    public void onPlayerDeathEvent(final PlayerDeathEvent event) {
        final Player player = event.getPlayer();
        if (this.worldConfig.isDeathMessages()) {
            final Location location = player.getLocation();
            final int x = location.getBlockX();
            final int y = location.getBlockY();
            final int z = location.getBlockZ();

            Component component = Component.text("§7You died at X §e" + x + "§7 Y §e" + y + "§7 Z §e" + z + "§7.");
            component = component.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD,
                    "x: " + x + " y: " + y + " z: " + z));
            component = component.hoverEvent(Component.text("§7Click to copy coordinates."));
            player.sendMessage(component);
        }
        if (!this.worldConfig.isGlobalDeathMessages()) {
            event.deathMessage(Component.empty());
        }
    }

}
