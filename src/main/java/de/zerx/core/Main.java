package de.zerx.core;

import de.zerx.core.console.ConsoleManager;
import de.zerx.core.console.commands.ExitCommand;
import de.zerx.core.mysql.MySQL;
import de.zerx.userhandler.UserHandler;
import de.zerx.userhandler.UserObject;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception {
  /*    var consoleManager = new ConsoleManager();
        consoleManager.addListener(new ExitCommand());


        // Rewrite user managment
        var userObject = new UserObject();
        Dotenv dotenv = Dotenv.load();

        userObject.addUser("zerxdelive", dotenv.get("ZERX_TOKEN"));

        var clientHandler = new UserHandler();
        clientHandler.init();
*/

        ResultSet rs = MySQL.getConnection().prepareStatement("SELECT * FROM `kenexar-rp`.addon_account;").executeQuery();

        System.out.println(rs.getMetaData().getColumnLabel(4));
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

