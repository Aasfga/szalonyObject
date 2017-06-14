package Server;

import Go.Logic.Game;
import Go.State;
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
    private String uuid;
    private int gameid;
    protected String color;

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
        //System.out.println(httpget.getURI());

        CloseableHttpResponse response =  httpclient.execute(httpget);

        return response;
    }

    private class LoginResponse {
        public String name;
    }

    public String login() {
        String result = "";
        try {
            URI uri = null;
            try {
                uri = new URIBuilder()
                        .setScheme("http")
                        .setHost(this.serverAddr.getHost().toString() + ":" + this.serverAddr.getPort())
                        .setPath("/login")
                        .build();
            }
            catch( Exception e ) {
                System.out.println(e.toString());
            }

            CloseableHttpResponse response = GET(uri);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            //System.out.println(responseString);
            LoginResponse received = (new Gson()).fromJson(responseString, LoginResponse.class);
            result = received.name;
            this.uuid = received.name;
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
                        .setHost(this.serverAddr.getHost().toString() + ":" + this.serverAddr.getPort())
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

    private class PromptResponse {
        public int gameid;
        public String color;
    }

    public boolean prompt(String uuid) {
        boolean result = false;
        try {
            URI uri = null;
            try {
                uri = new URIBuilder()
                        .setScheme("http")
                        .setHost(this.serverAddr.getHost().toString() + ":" + this.serverAddr.getPort())
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
                PromptResponse received = (new Gson()).fromJson(responseString, PromptResponse.class);
                this.gameid = received.gameid;
                this.color = received.color;
                System.out.println(this.color);
            }
            System.out.println(responseString);
            response.close();
        }
        catch( IOException e ) {
            System.out.println(e.toString());
        }
        return result;
    }

    public class StateResponse {
        public int [][] board;
        public int scorewhite;
        public int scoreblack;
        public String player;
    }

    public StateResponse resolveState() {
        boolean result = false;
        try {
            URI uri = null;
            try {
                uri = new URIBuilder()
                        .setScheme("http")
                        .setHost(this.serverAddr.getHost().toString() + ":" + this.serverAddr.getPort())
                        .setPath("/state")
                        .addParameter("gameid", String.valueOf(gameid))
                        .build();
            }
            catch( Exception e ) {
                System.out.println(e.toString());
            }
            CloseableHttpResponse response = GET(uri);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");

            StateResponse received = (new Gson()).fromJson(responseString, StateResponse.class);

            //System.out.println(responseString);
            response.close();

            return received;
        }
        catch( IOException e ) {
            System.out.println(e.toString());
        }
        return null;
    }

    public void sendMove(State.Move s) {
        boolean result = false;
        try {
            URI uri = null;
            try {
                uri = new URIBuilder()
                        .setScheme("http")
                        .setHost(this.serverAddr.getHost().toString() + ":" + this.serverAddr.getPort())
                        .setPath("/move")
                        .addParameter("gameid", String.valueOf(gameid))
                        .addParameter("uuid", uuid)
                        .addParameter("x", String.valueOf(s.x))
                        .addParameter("y", String.valueOf(s.y))
                        .build();
            }
            catch( Exception e ) {
                System.out.println(e.toString());
            }
            CloseableHttpResponse response = GET(uri);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");

            //System.out.println(responseString);
            response.close();
        }
        catch( IOException e ) {
            System.out.println(e.toString());
        }
    }

}
