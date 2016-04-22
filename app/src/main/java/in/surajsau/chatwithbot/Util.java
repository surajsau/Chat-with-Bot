package in.surajsau.chatwithbot;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by suraj on 22/4/16.
 */
public class Util {

    public static final long ONE_MINUTE = 60000;

    public static String getCurrentTimeString(long timeStamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        return dateFormat.format(calendar.getTime());
    }

    public static boolean isDifferenceMore(long time1, long time2, long differenceRequired) {
        if(time1 > time2) {
            return (time1 - time2) >= differenceRequired;
        }
        return (time2 - time1) >= differenceRequired;
    }

}
