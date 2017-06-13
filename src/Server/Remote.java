package Server;

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

    private CloseableHttpResponse GET(String ... options) throws IOException {
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

        HttpGet httpget = new HttpGet(uri);
        System.out.println(httpget.getURI());

        CloseableHttpResponse response =  httpclient.execute(httpget);

        return response;
    }

    public String login() {
        String result = "";
        try {
            CloseableHttpResponse response = GET("login");
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            System.out.println(responseString);
            response.close();
        }
        catch( IOException e ) {
            System.out.println(e.toString());
        }
        return result;
    }

    public void run() throws IOException {
        //System.out.println(Request.Get("realgo.herokuapp.com").execute().returnContent());
        //Request.Post("http://targethost/login")
        //        .bodyForm(Form.form().add("username",  "vip").add("password",  "secret").build())
        //        .execute().returnContent();
    }

    public void close() {

    }
}
