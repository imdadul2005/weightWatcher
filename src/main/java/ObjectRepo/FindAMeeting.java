package ObjectRepo;

import Common.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAMeeting extends Base{

    public  FindAMeeting(){
        PageFactory.initElements(driver,this);
    }

    String leaderLocator = "schedule-detailed-day-meetings-item-leader";
    @FindBy(id = "meetingSearch") WebElement meetingSearch_Input;
    @FindBy(className = "spice-translated") WebElement search_Icon;
    @FindAll({@FindBy(className = "location__top")}) List<WebElement> locationList;
    @FindAll({@FindBy(className = "location__name")}) List<WebElement> locationNameList;
    @FindAll({@FindBy(className = "location__distance")}) List<WebElement> locationDistanceList;
    @FindBy(className = "hours-list--currentday") WebElement currntDay;
    @FindAll({@FindBy(className = "schedule-detailed-day")}) List<WebElement> scheduleDetail;



    @FindBy(className = "location__name") WebElement locationName;

    public WebElement getLocationName() {
        return locationName;
    }

    public List<WebElement> getScheduleDetail() {
        return scheduleDetail;
    }

    public void getLeaderNameByDay(String day){
        List<WebElement> leaderNameByDay = null;
        HashMap<String,Integer> map = new HashMap<>();

        for (WebElement element: getScheduleDetail()) {
            if(element.getText().contains(day)){
                leaderNameByDay = element.findElements(By.className(leaderLocator));
            }
        }

        if (leaderNameByDay!=null){
            for (WebElement childElement: leaderNameByDay) {
                String leaderName = childElement.getText();
                if(map.containsKey(leaderName)){
                    int count = map.get(leaderName);
                    map.put(leaderName,++count);
                }
                else
                    map.put(leaderName,1);
            }
        }
        System.out.println("___________________\n"+day);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "  " + entry.getValue().toString());
        }
    }

    public WebElement getMeetingSearch_Input() {
        return meetingSearch_Input;
    }

    public WebElement getSearch_Icon() {
        return search_Icon;
    }

    public void search(){
        getSearch_Icon().click();
    }

    public List<WebElement> getLocationList() {
        return locationList;
    }

    public List<WebElement> getLocationNameList() {
        return locationNameList;
    }

    public List<WebElement> getLocationDistanceList() {
        return locationDistanceList;
    }

    public void meetingSearch(String zipCode, Boolean sendEnter){
        if(sendEnter)
            getMeetingSearch_Input().sendKeys(zipCode+ Keys.ENTER);
        else
            getMeetingSearch_Input().sendKeys(zipCode);
    }

    public WebElement getCurrntDay() {
        return currntDay;
    }
}
