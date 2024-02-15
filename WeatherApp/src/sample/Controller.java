package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField city;

    @FXML
    private Text currentTemp;

    @FXML
    private Text felt_temp;

    @FXML
    private Button getWeather;

    @FXML
    private Text pressure;

    @FXML
    private Text wind;

    @FXML
    void initialize() {
        getWeather.setOnAction(
                event -> {
                    String usercity = city.getText().trim();
                    if(!usercity.isEmpty()){
                        String output = getContent("http://api.weatherapi.com/v1/current.json?key=805f609064bc43ec8d2105338241502%20&q="+ usercity +"&aqi=no");

                            JSONObject object = new JSONObject(output);
                        currentTemp.setText(String.valueOf(object.getJSONObject("current").getDouble("temp_c")));
                        felt_temp.setText(String.valueOf(object.getJSONObject("current").getDouble("feelslike_c")));
                        wind.setText(String.valueOf(object.getJSONObject("current").getDouble("wind_kph")));
                        pressure.setText(String.valueOf(object.getJSONObject("current").getDouble("pressure_mb")));
                        }
                    }
        );
    }
    private static String getContent(String urlAdress){
        StringBuffer content = new StringBuffer();

        try{
            URL url = new URL(urlAdress);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Такой город не найден!");
        }
        return content.toString();
    }

}
