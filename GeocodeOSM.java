import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class GeocodeOSM {
    public static double[] getCoordinates(String address) {
        double[] latLon = new double[2];
        try {
            String urlStr = "https://nominatim.openstreetmap.org/search?q=" +
                    address.replace(" ", "+") +
                    "&format=json&limit=1";
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Java App");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) sb.append(line);
            reader.close();

            JSONArray arr = new JSONArray(sb.toString());
            if (arr.length() > 0) {
                JSONObject obj = arr.getJSONObject(0);
                latLon[0] = obj.getDouble("lat");
                latLon[1] = obj.getDouble("lon");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return latLon;
    }
}
