package Server;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URI;

public class Remote {
    private URL serverAddr;
    private long sessionID;
    private CloseableHttpClient httpclient;

    public Remote(String url) {
        try {
            this.serverAddr = new URL(url);
        }
        catch( MalformedURLException e ) {
            System.out.println(e);
        }

        this.httpclient = HttpClients.createDefault();
    }

    private CloseableHttpResponse GET(URI uri) throws IOException {
        HttpGet httpget = new HttpGet(uri);
        System.out.println(httpget.getURI());

        CloseableHttpResponse response =  httpclient.execute(httpget);

        return response;
    }

    private class LoginResponse {
        public String uuid;
        public String id;
    }

    public String login() {
        String result = "";
        try {
            URI uri = null;
            try {
                uri = new URIBuilder()
                        .setScheme("http")
                        .setHost(this.serverAddr.getHost().toString())
                        .setPath("/login")
                        .build();
            }
            catch( Exception e ) {
                System.out.println(e.toString());
            }

            CloseableHttpResponse response = GET(uri);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            System.out.println(responseString);
            LoginResponse received = (new Gson()).fromJson(responseString, LoginResponse.class);
            result = received.uuid;
            response.close();
        }
        catch( IOException e ) {
            System.out.println(e.toString());
        }
        return result;
    }

    public void close() {
        try {
            this.httpclient.close();
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    public void entrygame(String uuid) {
        try {
            URI uri = null;
            try {
                uri = new URIBuilder()
                        .setScheme("http")
                        .setHost(this.serverAddr.getHost().toString())
                        .setPath("/entergame")
                        .addParameter("uuid", uuid)
                        .build();
            }
            catch( Exception e ) {
                System.out.println(e.toString());
            }
            CloseableHttpResponse response = GET(uri);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            System.out.println(responseString);
            response.close();
        }
        catch( IOException e ) {
            System.out.println(e.toString());
        }
    }

    private class Prompt

    public boolean prompt(String uuid) {
        boolean result = false;
        try {
            URI uri = null;
            try {
                uri = new URIBuilder()
                        .setScheme("http")
                        .setHost(this.serverAddr.getHost().toString())
                        .setPath("/prompt")
                        .addParameter("uuid", uuid)
                        .build();
            }
            catch( Exception e ) {
                System.out.println(e.toString());
            }
            CloseableHttpResponse response = GET(uri);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            if(! responseString.equals("WAIT")) {
                result = true;
            }
            System.out.println(responseString);
            response.close();
        }
        catch( IOException e ) {
            System.out.println(e.toString());
        }
        return result;
    }
}
