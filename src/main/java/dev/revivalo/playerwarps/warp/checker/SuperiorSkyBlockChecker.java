package dev.revivalo.playerwarps.warp.checker;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import dev.revivalo.playerwarps.configuration.file.Lang;
import org.bukkit.entity.Player;

public class SuperiorSkyBlockChecker implements Checker {
    @Override
    public boolean validate(Player player) {
        Island island = SuperiorSkyblockAPI.getIslandAt(player.getLocation());
        if (island == null) {
            return true;
        }

        boolean allow;
        allow = island.getIslandMembers().stream().anyMatch(sp -> sp.getUniqueId().equals(player.getUniqueId()))
                || island.getOwner().getUniqueId().equals(player.getUniqueId());

        if (!allow) {
            player.sendMessage(Lang.TRIED_TO_CREATE_WARP_IN_FOREIGN_ISLAND.asColoredString());
            return false;
        }

        return true;
    }
}
