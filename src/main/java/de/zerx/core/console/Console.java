package de.zerx.core.console;

import de.zerx.constants.Banner;

import java.util.Scanner;

/**
 * Created by Till O. aka. ZerX
 * <p>
 * Project name: twitch-chat-bot<p>
 * This file was created at 22.12.2021 21:54
 */
public class Console extends Thread {

    private static final Scanner scanner = new Scanner(System.in);

    public Console() {
        start();
        setName("Console");
    }

    public void run() {
        System.out.println(Banner.bannerString);
        runEventLoop();
    }

    private void runEventLoop() {
        while (scanner.hasNext()) {
            ConsoleManager.triggerEvents(scanner.nextLine());
        }
    }
}