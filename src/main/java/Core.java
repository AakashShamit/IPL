import constants.Constants;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import java.util.*;

@Log4j2
public class Core {

    protected JsonOperations jsonOperations;

    //This beforeclass method is to initialize objects
    @BeforeClass
    public void initObject(){
        jsonOperations = JsonOperations.getInstance();
    }


    //This is a getmethod which returns an ArrayList object having Player Roles in it
    public ArrayList<String> getPlayerRole(String jsonPath){
        return jsonOperations.returnKey(jsonOperations.convertIntoJSONObject(jsonPath),Constants.JSON_ROLE);
    }

    //This is a getmethod which returns ArrayList object having Player names in it
    public ArrayList<String> getPlayerNames(String jsonPath){
        return jsonOperations.returnValueFromInnerJsonArray(jsonOperations.convertIntoJSONObject(jsonPath),Constants.JSON_PLAYER,Constants.JSON_NAME);
    }

    //This is a getmethod which returns an ArrayList object having Player Country in it
    public ArrayList<String> getPlayerCountry(String jsonPath){
        return jsonOperations.returnKey(jsonOperations.convertIntoJSONObject(jsonPath),Constants.JSON_COUNTRY);
    }


    //This is a getmethod which prints total team price
    public void getTotalTeamPrice(String jsonPath){
        float totalPrice=0;
        ArrayList<String> arrayList = jsonOperations.returnKey(jsonOperations.convertIntoJSONObject(jsonPath),Constants.JSON_PRICE);
        for(String price : arrayList){
            totalPrice= Float.parseFloat(price)+totalPrice;
        }
        log.info("Total Price for Team is - "+totalPrice+" Crore INR");
    }


    //This method validates that team size is 11 or not
    public void validateTeamSize(String jsonPath){
        Assert.assertTrue(getPlayerNames(jsonPath).size() == Constants.CRICKET_TEAM_SIZE,"Json is not proper as - Number of Team Players is not equals to 11");
        log.info("Team size in provided Json is correct(11)");
    }

    //This method validates that provided role is available in team or not
    public void validateRoleAvailability(String jsonPath, String role){
        ArrayList<String> arrayList = getPlayerRole(jsonPath);
        Assert.assertTrue(arrayList.contains(role),"Team doesn't have a "+role);
        log.info("Team is having atleast 1 "+role);
    }

    //This method check that there are exactly 4 foreign players in team or not
    public void validateForiegnPLayerPolicy(String jsonPath){
        ArrayList<String> arrayList =  getPlayerCountry(jsonPath);
        int count = 0;
        for(String country: arrayList){
            if(country.equals(Constants.JSON_INDIA)){
                count++;
            }
        }
        Assert.assertTrue(count==7,"There are "+(11-count)+" foriegn Players in the team - Not a valid team");
        log.info("Team is having exactly 4 foriegn players");
    }

}
