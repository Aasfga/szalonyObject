package Server;

import Go.State;
import sun.net.httpserver.*;

public class Client {
    private Remote r;

    public Client() {
        this.r = new Remote("http://realgo.herokuapp.com");//"http://localhost:6060");
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

    public Remote.StateResponse resolveState() {
        return r.resolveState();
    }

    public void sendMove(State.Move s) {
        r.sendMove(s);
    }
}
