package net.escendia.gui.controll.commands;

import net.escendia.gui.controll.PacketService;
import net.escendia.gui.model.network.out.gui.Delete;
import net.escendia.gui.model.network.out.gui.PacketGUI;
import net.escendia.ioc.InversionOfControl;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class GUIResetCommand implements CommandExecutor {


    private final CommandSpec commandSpec;

    public GUIResetCommand(){
        commandSpec = CommandSpec.builder().description(Text.of("Reset the screen and closes the current gui.")).executor(this).build();
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if(src instanceof Player){
            PacketService service = InversionOfControl.get().build(PacketService.class);
            Player player = (Player) src;
            service.sendPacketToPlayer(player,new Delete());
            return CommandResult.success();
        }else{
            return null;
        }
    }

    public CommandSpec getCommandSpec() {
        return commandSpec;
    }
}
