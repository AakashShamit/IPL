import org.json.simple.JSONObject;

public class JsonOperations {

    private volatile static JsonOperations obj;

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

    public void getValueFromInnerJsonArray(String arrayNodeKey){


    }

}
