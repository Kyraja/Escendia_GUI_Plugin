package net.escendia.gui;


import net.escendia.gui.controll.FileService;
import net.escendia.gui.controll.GUIService;
import net.escendia.gui.controll.PacketService;
import net.escendia.gui.controll.PlayerService;
import net.escendia.gui.controll.commands.GUIReloadCommand;
import net.escendia.gui.controll.commands.GUIResetCommand;
import net.escendia.gui.controll.commands.GUITestCommand;
import net.escendia.gui.model.logger.EscendiaLogger;
import net.escendia.gui.model.logger.Level;
import net.escendia.ioc.Inject;
import net.escendia.ioc.InversionOfControl;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import java.nio.file.Path;

@Plugin(
        id = "escendiagui",
        name = "EscendiaGUIPlugin",
        description = "Escendia GUI Plugin",
        authors = {
                "FPetersen"
        }
)
public class EscendiaGUIPlugin {

        @Inject
        private EscendiaLogger logger;

        @com.google.inject.Inject
        @DefaultConfig(sharedRoot = true)
        private Path defaultConfig;

        @Listener
        public void preinit(GamePreInitializationEvent e) {
                this.logger = new EscendiaLogger("Escendia GUI Plugin", Level.TRACE);

                registerIOC();
                registerCommand();
        }

        private void registerIOC() {
                InversionOfControl.get().registerInterfaceImplementation(EscendiaLogger.class, logger);
                InversionOfControl.get().registerDependency(this);

                InversionOfControl.get().registerInterfaceImplementation(FileService.class, new FileService());
                InversionOfControl.get().registerInterfaceImplementation(PlayerService.class, new PlayerService());
                InversionOfControl.get().registerInterfaceImplementation(PacketService.class, new PacketService());
                InversionOfControl.get().registerInterfaceImplementation(GUIService.class, new GUIService());


                Sponge.getEventManager().registerListeners(this, InversionOfControl.get().build(PlayerService.class));
        }

        private void registerCommand() {
                CommandSpec commandSpec = CommandSpec.builder().description(Text.of("Escendia GUI Command"))
                        .child(new GUIReloadCommand().getCommandSpec(), "reload", "r")
                        .child(new GUIResetCommand().getCommandSpec(), "reset")
                        .child(new GUITestCommand.GUITestFileCommand().getCommandSpec(), "tfile", "tf")
                        .child(new GUITestCommand.GUITestProgressbarCommand().getCommandSpec(), "tprogress", "tp")
                        .build();
                Sponge.getGame().getCommandManager().register(this, commandSpec, "gui");
        }

        @Listener
        public void gameStart(GameStartingServerEvent event){
                logger.info("GameStartingServerEvent() Escendia - GUI - Plugin loading...");
        }

        @Listener
        public void gameStart(GameStartedServerEvent event){
                logger.info("GameStartedServerEvent() Escendia - GUI - Plugin loading...");
        }

        @Listener
        public void postInit(GamePostInitializationEvent e){
               logger.info("GamePostInitializationEvent() Escendia - GUI - Plugin loading...");
               logger.info("GamePostInitializationEvent() Escendia - GUI - Plugin loaded...");
        }

        /**
         * Default config
         * @return
         */
        public Path getDefaultConfig() {
                return defaultConfig;
        }

        /**
         * returnts this plugin id
         * @return
         */
        public String getPluginID() {
                return "escendiagui";
        }
}
