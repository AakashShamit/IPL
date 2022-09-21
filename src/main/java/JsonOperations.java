import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

@Log4j2
public class JsonOperations{

    private volatile static JsonOperations obj;
    ArrayList<String> values = new ArrayList<>();

    public static JsonOperations getInstance() {
        if (obj == null) {
            // To make thread safe
            synchronized (JsonOperations.class) {
                // check again as multiple threads
                // can reach above step
                if (obj == null)
                    obj = new JsonOperations();
            }
        }
        return obj;
    }


    public void cleanDS()
    {
        values.clear();
    }


    public void getValueFromInnerJsonArray(JSONObject json, String arrayNodeKey, String key){
        boolean isNodePresent = json.has(arrayNodeKey);
        boolean isKeyPresent = json.has(key);

        if(isNodePresent){
            try {
                if(json.get(arrayNodeKey) instanceof JSONObject){
                    getKey((JSONObject)json.get(arrayNodeKey),key);
                } else if (json.get(arrayNodeKey) instanceof JSONArray) {

                    JSONArray jsonArray = json.getJSONArray(arrayNodeKey);
                    for(int i=0;i<jsonArray.length();i++){
                        String jsonArrayString = jsonArray.get(i).toString();
                        JSONObject innerjson = new JSONObject(jsonArrayString);
                        if(isKeyPresent==false){
                            getKey(innerjson,key);
                        }
                        else{
                            addToList(innerjson,key);
                        }
                    }
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void getKey(JSONObject json, String key){
        boolean isPresent = json.has(key);
        Iterator<?> keys;
        String nextkeys;


        if(!isPresent){
            keys=json.keys();
            while(keys.hasNext()){
                nextkeys=(String)keys.next();
                try{
                    if(json.get(nextkeys) instanceof JSONObject){
                        if( isPresent == false){
                            getKey(json.getJSONObject(nextkeys),key);
                        }
                    }
                    else if(json.get(nextkeys) instanceof JSONArray){
                        JSONArray jsonArray = json.getJSONArray(nextkeys);
                        for(int i=0;i<jsonArray.length();i++){
                            String jsonArrayString = jsonArray.get(i).toString();
                            JSONObject innerjson = new JSONObject(jsonArrayString);
                            if(isPresent==false){
                                getKey(innerjson,key);
                            }
                        }
                    }
                }
                catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else{
            addToList(json, key);
        }
    }

    public void addToList(JSONObject json, String key){
        values.add((String)json.get(key));
    }


    public JSONObject convertIntoJSONObject(String jsonFilePath){
        InputStream is = JsonOperations.class.getResourceAsStream(jsonFilePath);
        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + jsonFilePath);
        }
        JSONTokener tokener = new JSONTokener(is);
        JSONObject object = new JSONObject(tokener);
        return object;
    }


    public ArrayList<String> returnKey(JSONObject json, String key){
        cleanDS();
        getKey(json,key);
        return values;
    }


    public ArrayList<String> returnValueFromInnerJsonArray(JSONObject json,String arrayNodeKey, String key){
        cleanDS();
        getValueFromInnerJsonArray(json,arrayNodeKey,key);
        return values;
    }



}
