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

    public void entrygame(String uuid) {
        r.entrygame(uuid);
    }

    public boolean prompt(String uuid) {
        return r.prompt(uuid);
    }

    public String getColor() {
        return r.color;
    }
}
