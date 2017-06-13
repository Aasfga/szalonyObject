package Server;

import sun.net.httpserver.*;

public class Client {
    private Remote r;

    public Client() {
        this.r = new Remote("http://realgo.herokuapp.com");
    }

    public String login() {
        return r.login();
    }
}
