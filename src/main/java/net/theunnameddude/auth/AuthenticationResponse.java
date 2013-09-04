package net.theunnameddude.auth;

import org.json.JSONException;
import org.json.JSONObject;

public class AuthenticationResponse {

    String accessToken;
    String clientToken;
    String username;

    public AuthenticationResponse(JSONObject obj, String username) {
        this.username = username;
        System.out.println( obj );
        try {
            this.accessToken = obj.getString( "accessToken" );
            this.clientToken = obj.getString( "clientToken" );
        } catch ( JSONException e ) {
            e.printStackTrace();
        }
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getClientToken() {
        return clientToken;
    }

    public String getUsername() {
        return username;
    }
}
