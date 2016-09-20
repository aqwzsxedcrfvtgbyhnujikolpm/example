package getbest;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.JOptionPane;
import org.tbot.internal.handlers.LogHandler;

public class ItemPriceGrabber {

    private HashMap<Integer, Integer> itemPrices = new HashMap<>();

    public void grabAllPrices() {
        LogHandler.log("Grabbing all prices for items");
        try {
            String sURL = "https://rsbuddy.com/exchange/summary.json";
            URL url = new URL(sURL);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            if (root != null && !root.toString().isEmpty() && !"null".equals(root.toString())) {
                JsonObject rootobj = root.getAsJsonObject();
                Set<Entry<String, JsonElement>> listItems = rootobj.entrySet();
                for (Entry<String, JsonElement> entry : listItems) {
                    this.itemPrices.put(Integer.parseInt(entry.getKey()), entry.getValue().getAsJsonObject().get("overall_average").getAsInt());
                }
            }
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(null, "Error during grabbing prices. " + ex.getMessage());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error during grabbing prices. " + ex.getMessage());
        }
    }

    public int grabPrice(int itemId) {
        if (itemPrices.containsKey(itemId)) {
            return itemPrices.get(itemId);
        }
        return 0;
    }
    
}
