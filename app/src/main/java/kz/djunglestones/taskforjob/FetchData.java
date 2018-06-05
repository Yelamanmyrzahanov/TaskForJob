package kz.djunglestones.taskforjob;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchData extends AsyncTask<Void,Void,Void>{

    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    private List<User> list_user = new ArrayList<>();

    @Override
    protected Void doInBackground(Void... voids) {

        try {

            URL url = new URL("http://www.mocky.io/v2/5a488f243000004c15c3c57e");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String employment = jsonObject.getString("employment");
                JSONObject employmentObject = new JSONObject(employment);
                User user = new User();

                user.setId(jsonObject.getInt("id"));
                user.setFirst_name(jsonObject.getString("first_name"));
                user.setLast_name(jsonObject.getString("last_name"));
                user.setEmail(jsonObject.getString("email"));
                user.setGender(jsonObject.getString("gender"));
                user.setIp_address(jsonObject.getString("ip_address"));
                user.setPhoto(jsonObject.getString("photo"));
                user.setName(employmentObject.getString("name"));
                user.setPosition(employmentObject.getString("position"));
                list_user.add(user);
//                singleParsed = "id: "+jsonObject.get("id")+"\n"+
//                        "first_name: "+jsonObject.get("first_name")+"\n"+
//                        "last_name: "+jsonObject.get("last_name")+"\n"+
//                        "email: "+jsonObject.get("email")+"\n"+
//                        "gender: "+jsonObject.get("gender")+"\n"+
//                        "ip_address: "+jsonObject.get("ip_address")+"\n"+
//                        "name: "+employmentObject.get("name")+"\n"+
//                        "position: "+employmentObject.get("position")+"\n"+
//                        "photo: "+jsonObject.get("photo")+"\n";


//                dataParsed = dataParsed+singleParsed+"\n";
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

//        MainActivity.data.setText(this.dataParsed);
    }
}
