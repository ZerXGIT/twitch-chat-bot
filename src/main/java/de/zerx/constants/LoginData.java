package de.zerx.constants;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * Created by Till O. aka. ZerX
 * <p>
 * Project name: twitch-chat-bot<p>
 * This file was created at 22.12.2021 21:48
 */
public interface LoginData {

    Dotenv dotenv = Dotenv.load();

    String CLIENT_ID = dotenv.get("CLIENT_ID");
    String CLIENT_SECRET = dotenv.get("CLIENT_SECRET");
    OAuth2Credential CLIENT_CREDENTIAL = new OAuth2Credential("twitch", dotenv.get("CLIENT_ACCESSTOKEN"));

}
