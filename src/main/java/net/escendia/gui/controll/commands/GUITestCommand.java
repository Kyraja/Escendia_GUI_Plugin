package net.escendia.gui.controll.commands;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import net.escendia.gui.controll.FileService;
import net.escendia.gui.controll.PacketService;
import net.escendia.gui.model.components.Element;
import net.escendia.gui.model.components.impl.Progressbar;
import net.escendia.gui.model.form.Form;
import net.escendia.gui.model.form.style.FormStyle;
import net.escendia.gui.model.form.style.impl.RectangleStandard;
import net.escendia.gui.model.network.in.PacketGUI;
import net.escendia.gui.model.network.out.PacketElement;
import net.escendia.ioc.InversionOfControl;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;

import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class GUITestCommand implements CommandExecutor {

    private CommandSpec commandSpec;

    public GUITestCommand(){}

    public void setCommandSpec(CommandSpec commandSpec) {
        this.commandSpec = commandSpec;
    }



    public CommandSpec getCommandSpec() {
        return commandSpec;
    }

    public static class GUITestFileCommand extends GUITestCommand{

        public GUITestFileCommand(){
            super();
            setCommandSpec(CommandSpec.builder().description(org.spongepowered.api.text.Text.of("Test GUI File.")).executor(this).build());
        }

        @Override
        public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
            if(src instanceof Player){


                try {
                    InputStream stream = InversionOfControl.get().build(FileService.class).getFile("testgui.json");
                    String json = CharStreams.toString(new InputStreamReader(stream, Charsets.UTF_8));
                    PacketService packetService = InversionOfControl.get().build(PacketService.class);
                    Player player = (Player)src;
//                Element element = new Element().fromJson(json);
//                Progressbar progressbar = new Progressbar();
//                Form form = element.getForm();
//                form.getFormStyle().setWidth(50);
//                form.getFormStyle().setWidth(10);
//                form.getFormStyle().setFontColor(255,255,0,255);
//                progressbar.setBarPercentage(50);
//                progressbar.setForm(form);
//
//                element.addChildrenElement(progressbar);



                    packetService.sendPacketToPlayer(player, new PacketGUI.Delete());
                    packetService.sendPacketToPlayer(player, new PacketGUI.Create());
                    packetService.sendPacketToPlayer(player, new PacketElement.Added(new Element().fromJson(json)));

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return CommandResult.success();
            }

            return null;
        }

    }

    public static class GUITestProgressbarCommand extends GUITestCommand{

        public GUITestProgressbarCommand(){
            super();
            setCommandSpec(CommandSpec.builder().description(org.spongepowered.api.text.Text.of("Test Progreessbars for GUI screen.")).executor(this).build());
        }

        @Override
        public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

            PacketService packetService = InversionOfControl.get().build(PacketService.class);
            Player player = (Player)src;

            FormStyle standard = new FormStyle();
            standard.setWidth(100);
            standard.setHeight(100);
            standard.setFontColor(0,255,0,255);

            RectangleStandard progressForm = new RectangleStandard(standard);

            FormStyle progressFormStyle = new FormStyle();
            progressFormStyle.setWidth(50);
            progressFormStyle.setHeight(50);
            progressFormStyle.setFontColor(255,255,255,255);

            RectangleStandard standardForm = new RectangleStandard(standard);

            Progressbar.Vertical vertical = new Progressbar.Vertical();
            vertical.setForm(standardForm);
            vertical.setBarPercentage(50);
            vertical.setProgressForm(progressForm);


            packetService.sendPacketToPlayer(player, new PacketGUI.Delete());
            packetService.sendPacketToPlayer(player, new PacketGUI.Create());
            packetService.sendPacketToPlayer(player, new PacketElement.Added(vertical));


            return CommandResult.success();
        }
    }
}
