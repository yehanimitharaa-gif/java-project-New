import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class OSRMDistance {
    public static double getDrivingDistance(double lat1, double lon1, double lat2, double lon2) {
        double distanceKm = 0;
        try {
            String urlStr = "http://router.project-osrm.org/route/v1/driving/" +
                    lon1 + "," + lat1 + ";" + lon2 + "," + lat2 +
                    "?overview=false";
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) sb.append(line);
            reader.close();

            JSONObject json = new JSONObject(sb.toString());
            double distanceMeters = json.getJSONArray("routes")
                    .getJSONObject(0)
                    .getDouble("distance");
            distanceKm = distanceMeters / 1000; // km වලට පරිවර්තනය
        } catch (Exception e) {
            e.printStackTrace();
        }
        return distanceKm;
    }
}
