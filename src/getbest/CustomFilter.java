package getbest;

import org.tbot.methods.Players;
import org.tbot.util.Filter;
import org.tbot.wrappers.GroundItem;

public class CustomFilter implements Filter {

    private int maxDistanceAway;

    public CustomFilter(int range) {
        this.maxDistanceAway = range;
    }

    // Only accept grounditems that are within specified range
    // You could change this to whatever you'd like, for example: only items
    // whose names contain the word 'dragon'.
    @Override
    public boolean accept(Object v) {
        GroundItem groundItem = (GroundItem) v;
        return groundItem.distance(Players.getLocal().getLocation()) <= this.maxDistanceAway;
    }

}
