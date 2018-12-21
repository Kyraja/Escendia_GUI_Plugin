package net.escendia.gui.controll.commands;

import net.escendia.gui.controll.PacketService;
import net.escendia.gui.model.network.out.PacketConnection;
import net.escendia.ioc.InversionOfControl;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class GUIReloadCommand implements CommandExecutor {

    private final CommandSpec commandSpec;

    public GUIReloadCommand(){
        commandSpec = CommandSpec.builder().description(Text.of("Reload GUI screen.")).executor(this).build();
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if(src instanceof Player)InversionOfControl.get().build(PacketService.class).sendPacketToPlayer(((Player)src).getPlayer().get().getUniqueId(), new PacketConnection.Init());
        return null;
    }

    public CommandSpec getCommandSpec() {
        return commandSpec;
    }
}
