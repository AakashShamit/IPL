import constants.Constants;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.ArrayList;


@Log4j2
public class RCBTests extends Core{
    @Test
    public void test1(){

        validateTeamSize(Constants.RCB_JSON);    //Validates the team size

        getTotalTeamPrice(Constants.RCB_JSON);   //Get Total team price

        log.info("Team Players Names are - "+getPlayerNames(Constants.RCB_JSON));  //Prints all team players names

        validateRoleAvailability(Constants.RCB_JSON,Constants.JSON_WICKETKEEPER);     //Tests that team is having atleast 1 wicketkeeper

        validateForiegnPLayerPolicy(Constants.RCB_JSON);  //Tests that team is having exactly 4 foriegn players

    }






}
