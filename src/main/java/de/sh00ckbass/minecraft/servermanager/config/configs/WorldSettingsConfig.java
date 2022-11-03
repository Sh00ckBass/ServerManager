package de.sh00ckbass.minecraft.servermanager.config.configs;

import lombok.Getter;
import lombok.Setter;

/*******************************************************
 * Copyright (C) Tobias Zechmann tobias@sh00ckbass.de
 *
 * This file is part of ServerManager and was created at the 03.11.2022
 *
 * ServerManager can not be copied and/or distributed without the express
 * permission of the owner.
 *
 */

@Getter
@Setter
public class WorldSettingsConfig {

    private boolean pvp = true;
    private boolean deathMessages = true;

}
