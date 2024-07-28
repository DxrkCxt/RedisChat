package dev.unnm3d.redischat.chat.filters.incoming;

import dev.unnm3d.redischat.Permissions;
import dev.unnm3d.redischat.RedisChat;
import dev.unnm3d.redischat.chat.filters.AbstractFilter;
import dev.unnm3d.redischat.chat.filters.FilterResult;
import dev.unnm3d.redischat.chat.objects.ChatMessage;
import dev.unnm3d.redischat.settings.FiltersConfig;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class PermissionFilter extends AbstractFilter<FiltersConfig.FilterSettings> {

    public PermissionFilter(FiltersConfig.FilterSettings filterSettings) {
        super("permission", Direction.INCOMING, filterSettings);
    }

    @Override
    public FilterResult applyWithPrevious(CommandSender receiver, @NotNull ChatMessage chatMessage, ChatMessage... previousMessages) {
        for (String permission : chatMessage.getReceiver().getPermissions()) {
            RedisChat.getInstance().getLogger().info(receiver.getName()+" has permission "+permission+" 1? "+receiver.hasPermission(permission));
            if (!receiver.hasPermission(permission)) {
                return new FilterResult(chatMessage, true);
            }
        }

        //Default read permission check
        final String permission = Permissions.CHANNEL_PREFIX.getPermission() + chatMessage.getReceiver().getName();
        RedisChat.getInstance().getLogger().info(receiver.getName()+" has permission "+permission+" 2? "+receiver.hasPermission(permission));
        RedisChat.getInstance().getLogger().info(receiver.getName()+" has permission "+permission+".read? "+receiver.hasPermission(permission+".read"));
        if (!(receiver.hasPermission(permission) || receiver.hasPermission(permission + ".read"))) {
            return new FilterResult(chatMessage, true);
        }

        return new FilterResult(chatMessage, false);
    }
}
