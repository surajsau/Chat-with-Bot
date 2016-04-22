package in.surajsau.chatwithbot;

import android.support.annotation.IntDef;

/**
 * Created by suraj on 22/4/16.
 */
public class Message {

    public static final int TYPE_TIME_SEPARATOR = 2;
    public static final int TYPE_BUYER = 0;
    public static final int TYPE_SELLER = 1;
    public static final int TYPE_NONE = -1;

    @IntDef({TYPE_TIME_SEPARATOR, TYPE_BUYER, TYPE_SELLER, TYPE_NONE})
    public @interface UserType{};

    @Message.UserType private int userType;
    private String message;
    private long timeStamp;

    public Message(@UserType int userType, String message, long timeStamp) {
        this.userType = userType;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    @UserType public int getUserType() {
        return userType;
    }

    public void setUserType(@UserType int userType) {
        this.userType = userType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
