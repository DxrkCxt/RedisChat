package dev.unnm3d.kalyachat.commands;

import dev.unnm3d.kalyachat.KalyaChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

public class PlayerListManager implements TabCompleter {
    private final BukkitTask task;
    private static Set<String> playerList;

    public PlayerListManager() {
        KalyaChat kc=KalyaChat.getInstance();
        this.task=new BukkitRunnable(){

            @Override
            public void run() {
                try {
                    playerList = kc.getRedisDataManager().getPlayerList();
                }catch (Exception ignored){
                }
            }
        }.runTaskTimerAsynchronously(kc, 0, 20);
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length==0)return null;
        if(command.getName().equalsIgnoreCase("msg")&&args.length!=1){
            return null;
        }
        return playerList.stream().filter(s -> s.startsWith(args[args.length-1])).toList();
    }

    public static Set<String> getPlayerList(){
        return playerList;
    }
}
