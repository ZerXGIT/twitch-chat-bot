package eu.kenexar.core;

import eu.kenexar.commands.CommandManager;
import eu.kenexar.commands.runnable.CreateClipCommand;
import eu.kenexar.commands.runnable.GameChangeCommand;
import eu.kenexar.commands.runnable.TitleChangeCommand;
import eu.kenexar.core.console.ConsoleManager;
import eu.kenexar.core.console.commands.ExitCommand;
import eu.kenexar.userhandler.UserHandler;
import eu.kenexar.userhandler.UserObject;
import io.github.cdimascio.dotenv.Dotenv;


public class Main {

    public static void main(String[] args) {
        ConsoleManager.addListener(new ExitCommand());

        CommandManager.addListener(new CreateClipCommand());
        CommandManager.addListener(new GameChangeCommand());
        CommandManager.addListener(new TitleChangeCommand());

        // Rewrite user management
        var userObject = new UserObject();
        Dotenv dotenv = Dotenv.load();

        userObject.addUser("zerxdelive", dotenv.get("ZERX_TOKEN"));

        var clientHandler = new UserHandler();
        clientHandler.init();


        //System.out.println(MySQL.get("SELECT * FROM `kenexar-rp`.addon_account;").getMetaData().getColumnLabel(1));


    }

    /**
     * Ablage AMK
     * public void restartApplication(){
     *         try {
     *             final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
     *             final File currentJar = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
     *
     *             if (!currentJar.getName().endsWith(".jar"))
     *                 return;
     *
     *             final ArrayList<String> command = new ArrayList<String>();
     *             command.add(javaBin);
     *             command.add("-jar");
     *             command.add(currentJar.getPath());
     *
     *             final ProcessBuilder builder = new ProcessBuilder(command);
     *             builder.start();
     *             System.exit(0);
     *         } catch (IOException | URISyntaxException e) {
     *             e.printStackTrace();
     *         }
     *     }
     *
     */


}

