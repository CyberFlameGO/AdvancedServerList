/*
 * MIT License
 *
 * Copyright (c) 2022-2023 Andre_601
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package ch.andre601.advancedserverlist.bukkit.commands;

import ch.andre601.advancedserverlist.core.interfaces.commands.CmdSender;
import ch.andre601.advancedserverlist.core.interfaces.core.PluginCore;
import ch.andre601.advancedserverlist.paper.commands.PaperCmdSender;
import ch.andre601.advancedserverlist.spigot.commands.SpigotCmdSender;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class CmdAdvancedServerList extends Command implements CommandExecutor{
    
    private final PluginCore<?> plugin;
    private final BukkitAudiences audiences;
    
    public CmdAdvancedServerList(PluginCore<?> plugin){
        this(plugin, null);
    }
    
    public CmdAdvancedServerList(PluginCore<?> plugin, BukkitAudiences audiences){
        super(
            "advancedserverlist",
            "Main command of the plugin",
            "/asl [reload|help]",
            Collections.singletonList("asl")
        );
        
        this.plugin = plugin;
        this.audiences = audiences;
    }
    
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args){
        plugin.getCore().getCommandHandler().handle(getSender(sender), args);
        return true;
    }
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args){
        plugin.getCore().getCommandHandler().handle(getSender(sender), args);
        return true;
    }
    
    private CmdSender getSender(CommandSender sender){
        return audiences == null ? new PaperCmdSender(sender) : new SpigotCmdSender(sender, audiences);
    }
}
