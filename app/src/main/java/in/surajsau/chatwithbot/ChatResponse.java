package in.surajsau.chatwithbot;

import java.io.Serializable;

/**
 * Created by suraj on 22/4/16.
 */
public class ChatResponse implements Serializable {

    private String convo_id;
    private String usersay;
    private String botsay;

    public String getConvo_id() {
        return convo_id;
    }

    public void setConvo_id(String convo_id) {
        this.convo_id = convo_id;
    }

    public String getUsersay() {
        return usersay;
    }

    public void setUsersay(String usersay) {
        this.usersay = usersay;
    }

    public String getBotsay() {
        return botsay;
    }

    public void setBotsay(String botsay) {
        this.botsay = botsay;
    }
}
