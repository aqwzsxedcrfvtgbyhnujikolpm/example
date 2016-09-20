package getbest;

import bot.TaskExecutor;
import org.tbot.methods.Players;
import org.tbot.util.Evaluator;
import org.tbot.wrappers.GroundItem;

public class CustomEvaluator implements Evaluator {

    // This is my implementation, would be better to use weights for distance I think
    // You'll have to figure out how to score the items yourself
    //
    // Less points = good item
    // Alot of points = bad item
    @Override
    public double evaluate(Object v) {
        double points = 0;
        GroundItem groundItem = (GroundItem) v;
        points = points + calculatePointsForDistance(groundItem);
        points = points + calculatePointsForPrice(groundItem);
        return points;
    }

    // High distance is alot of points because is bad
    private double calculatePointsForDistance(GroundItem groundItem) {
        return (double) groundItem.distance(Players.getLocal().getLocation());
    }

    // High price is negative points because it is good
    private double calculatePointsForPrice(GroundItem groundItem) {
        return (double) TaskExecutor.priceGrabber.grabPrice(groundItem.getID()) * -1;
    }

}
