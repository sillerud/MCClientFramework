package net.theunnameddude.mcclient.api.auth;

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
    private final static String authServer = "https://authserver.mojang.com/authenticate";


    public static AuthenticationResponse sendRequest(String username, String password) {
        JSONObject request = new JSONObject();
        try {
            request.put( "agent", new JSONObject()
                    .put( "name", "Minecraft")
                    .put( "version", 1)
            );
            request.put( "username", username );
            request.put( "password", password );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            HttpURLConnection urlConnection = createConnection( authServer, request );

            BufferedReader reader = new BufferedReader( new InputStreamReader( urlConnection.getInputStream() ) );
            String line;
            while ( (line = reader.readLine()) != null ) {
                if ( !line.trim().isEmpty() ) {
                    break;
                }
            }

            JSONObject jsonObject = new JSONObject( line );
            return new AuthenticationResponse( jsonObject );

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static AuthenticationResponse update(String accessToken, String clientToken) {
        JSONObject request = new JSONObject();
        try {
            request.put( "accessToken", accessToken );
            request.put( "clientToken", clientToken );
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection urlConnection = createConnection( authServer, request );
            BufferedReader reader = new BufferedReader( new InputStreamReader( urlConnection.getInputStream() ) );
            String line;
            while ( (line = reader.readLine()) != null ) {
                if ( !line.trim().isEmpty() ) {
                    break;
                }
            }

            JSONObject jsonObject = new JSONObject( line );
            return new AuthenticationResponse( jsonObject );

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static HttpURLConnection createConnection( String URL, JSONObject request ) throws IOException {
        byte[] params = request.toString().getBytes( Charset.forName( "UTF-8" ) );
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
        writer.close();;
        return urlConnection;
    }

}
