package ro.ase.stampcollector;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Network extends AsyncTask<URL, Integer, InputStream>{

    InputStream mInputStream = null;
    public static String mBuffer = new String();




    @Override
    protected InputStream doInBackground(URL... urls) {

        HttpURLConnection connection = null;

        try{
            connection = (HttpURLConnection)urls[0].openConnection();
            connection.setRequestMethod("GET");

            mInputStream = connection.getInputStream();

            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(mInputStream));

            String newLine = "";

            while((newLine = bufferedReader.readLine()) != null){
                mBuffer += newLine;
            }



        }catch(Exception ex){
            Log.e("doInBackground", ex.getMessage());
        }
        finally{
            if(connection != null)
            {
                connection.disconnect();
            }
        }

        return  mInputStream;
    }

    public String GetUsers(){

        try{
            URL url = new URL("https://api.myjson.com/bins/qz756");

            execute(url);


            String result = null;

            if(mInputStream != null){
                result = mBuffer;
            }
            else{
                Thread.sleep(5000);
                result = mBuffer;
            }
            return result;
        }catch(Exception ex){
            Log.e("GetUsers", ex.getMessage());
            return null;
        }
    }

    public static ArrayList<Collector> returnUsers(){
        Network n = new Network();
        String stringOfUsers = n.GetUsers();
        ArrayList<Collector> collectors = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(stringOfUsers);
            JSONArray usersArray = jsonObject.getJSONArray("users");
            for(int i = 0; i < usersArray.length(); i++){
                JSONObject JSONuser = usersArray.getJSONObject(i);
                Collector collector = new Collector();
                collector.setId(Integer.parseInt(JSONuser.getString("id")));
                collector.setName(JSONuser.getString("name"));
                collector.setEmail(JSONuser.getString("email"));

                JSONObject JSONadress = new JSONObject(JSONuser.getString("address"));
                collector.setCity(JSONadress.getString("city"));

                JSONObject JSONCompany = new JSONObject(JSONuser.getString("company"));
                collector.setCompanyName(JSONCompany.getString("name"));

                collector.setPhone(JSONuser.getString("phone"));
                collector.setWebsite(JSONuser.getString("website"));

                collectors.add(collector);

            }


        }catch(Exception ex){
            ex.printStackTrace();
        }
        return collectors;
    }

}