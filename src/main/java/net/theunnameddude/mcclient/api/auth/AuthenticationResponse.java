package net.theunnameddude.mcclient.api.auth;

import org.json.JSONException;
import org.json.JSONObject;

public class AuthenticationResponse {

    String accessToken;
    String clientToken;
    String username;
    String profileId;

    public AuthenticationResponse(JSONObject obj) {
        try {
            this.accessToken = obj.getString( "accessToken" );
            this.clientToken = obj.getString( "clientToken" );
            JSONObject selectedProfile = obj.getJSONObject( "selectedProfile" );
            this.profileId = selectedProfile.getString( "id" );
            this.username = selectedProfile.getString( "name" );
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

    public String getProfileId() {
        return profileId;
    }
}
