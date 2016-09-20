package util;

import org.tbot.methods.Mouse;

public class Utils {

    public static String getFormattedTime(final long timeMillis) {
        long millis = timeMillis;
        final long seconds2 = millis / 1000;
        final long hours = millis / (1000 * 60 * 60);
        millis -= hours * 1000 * 60 * 60;
        final long minutes = millis / (1000 * 60);
        millis -= minutes * 1000 * 60;
        final long seconds = millis / 1000;
        String hoursString = "";
        String minutesString = "";
        String secondsString = seconds + "";
        String type = "seconds";

        if (minutes > 0) {
            minutesString = minutes + ":";
            type = "minutes";
        } else if (hours > 0 && seconds2 > 0) {
            minutesString = "0:";
        }
        if (hours > 0) {
            hoursString = hours + ":";
            type = "hours";
        }
        if (minutes < 10 && !type.equals("seconds")) {
            minutesString = "0" + minutesString;
        }
        if (hours < 10 && type.equals("hours")) {
            hoursString = "0" + hoursString;
        }
        if (seconds < 10 && !type.equals("seconds")) {
            secondsString = "0" + secondsString;
        }
        if (timeMillis == 1000) {
            type = "second";
        } else if (timeMillis == 60000) {
            type = "minute";
        } else if (timeMillis == 3600000) {
            type = "hour";
        }
        return hoursString + minutesString + secondsString;
    }

    public static boolean isMouseClicked(int x1, int y1, int x2, int y2) {
        int px = Mouse.getX();
        int py = Mouse.getY();
        x2 = x1 + x2;
        y2 = y1 + y2;
        return (px > x1 && py > y1 && px < x2 && py < y2);
    }
}
