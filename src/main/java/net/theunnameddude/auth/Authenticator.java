package net.theunnameddude.auth;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class Authenticator {
    private final String authServer = "https://authserver.mojang.com/authenticate";
    private String password;
    private String username;

    public Authenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthenticationResponse sendRequest() {
        JSONObject request = new JSONObject();
        try {
            request.put( "username", username );
            request.put( "password", password );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        byte[] params = request.toString().getBytes( Charset.forName( "UTF-8" ) );
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL( authServer ).openConnection();

            urlConnection.setConnectTimeout( 5000 );
            urlConnection.setReadTimeout( 5000 );
            urlConnection.setRequestMethod( "POST" );

            urlConnection.setRequestProperty("Content-Type", "application/json" + "; charset=utf-8");

            urlConnection.setRequestProperty("Content-Length", "" + params.length);
            urlConnection.setRequestProperty("Content-Language", "en-US");

            urlConnection.setUseCaches(false);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            DataOutputStream writer = new DataOutputStream(urlConnection.getOutputStream());
            writer.write(params);
            writer.flush();
            writer.close();

            BufferedReader reader = new BufferedReader( new InputStreamReader( urlConnection.getInputStream() ) );
            String line;
            while ( (line = reader.readLine()) != null ) {
                if ( !line.trim().isEmpty() ) {
                    break;
                }
            }

            JSONObject jsonObject = new JSONObject( line );
            return new AuthenticationResponse( jsonObject, username );

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
