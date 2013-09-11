package net.theunnameddude.mcclient.api.auth;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

public class AuthenticationResponse {

    String accessToken;
    String clientToken;
    String username;
    String profileId;

    boolean mojangAccount = true;

    String sessionId;

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

    public AuthenticationResponse(String username, String sessionId) {
        this.username = username;
        this.sessionId = sessionId;
        mojangAccount = false;
    }

    public AuthenticationResponse(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getClientToken() {
        return clientToken;
    }

    public String getProfileId() {
        return profileId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getUsername() {
        return username;
    }

    public boolean isMojangAccount() {
        return mojangAccount;
    }

    public String getConnectId() {
        StringBuilder builder = new StringBuilder();
        if ( isMojangAccount() ) {
            builder.append( "token" )
                    .append( ':' ).append( getAccessToken() )
                    .append( ':' ).append( getProfileId() );
        } else {
            builder.append( new Date().getTime() / 1000 )
                    .append( ':' ).append( "deprecated" )
                    .append( ':' ).append( username )
                    .append( ':' ).append( getSessionId() )
                    .append( ':' ).append( UUID.randomUUID() );

        }
        return builder.toString();
    }
}
