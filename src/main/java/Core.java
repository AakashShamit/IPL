import constants.Constants;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;

public class Core {

    protected JsonOperations jsonOperations;

    @BeforeClass
    public void initObject(){
        jsonOperations = JsonOperations.getInstance();

    }

    public void validatePlayerRole(){
        ArrayList<String> arr = jsonOperations.returnKey(jsonOperations.convertIntoJSONObject(Constants.RCB_JSON),"role");

    }

    public void getPlayerNames(){
        jsonOperations.returnValueFromInnerJsonArray(jsonOperations.convertIntoJSONObject(Constants.RCB_JSON),"player","name");
    }

    public void getTotalTeamPrice(){

    }

}
