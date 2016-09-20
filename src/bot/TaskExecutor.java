package bot;

import getbest.CustomEvaluator;
import getbest.CustomFilter;
import getbest.ItemPriceGrabber;
import org.tbot.internal.handlers.LogHandler;
import org.tbot.methods.GroundItems;
import org.tbot.methods.Time;
import org.tbot.wrappers.GroundItem;

public class TaskExecutor {

    public final static ItemPriceGrabber priceGrabber = new ItemPriceGrabber();

    public static void init() {
        priceGrabber.grabAllPrices();
    }

    public static int STOP_SCRIPT() {
        LogHandler.log("Stopping script.");
        return -1;
    }

    // Look for best item within a range of 20
    public static void GET_BEST() {
        GroundItem[] items = GroundItems.getLoaded();
        if (items.length > 0) {
            LogHandler.log("Finding best item");
            // Choose range here (goes into filter)
            GroundItem bestGroundItem = GroundItems.getBest(new CustomEvaluator(), new CustomFilter(20));
            if (bestGroundItem == null) {
                LogHandler.log("Not in range of any item. Change range in script.");
            } else {
                LogHandler.log("Found best item: " + bestGroundItem.getName() + " with price: " + priceGrabber.grabPrice(bestGroundItem.getID()));
            }
        }
        // Wait 2.5 seconds before looking for new best item
        Time.sleep(2500);
    }

}
