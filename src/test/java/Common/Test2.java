package Common;

import ObjectRepo.FindAMeeting;
import ObjectRepo.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;


/**
 * @author Imdadul Hoq
 */

public class Test2 extends Base {

    WebDriver driver;
    final String DAYSARRAY[] = {"SUN","MON","TUE", "WED","THU","FRI", "SAT"};

    @BeforeMethod
    public void startUp() throws IOException {
        //String os = "Windows";
       // String browserName = "chrome";
        driver = getLocalDriver(getProperty("os"),getProperty("browserName"));
        driver.manage().deleteAllCookies();

    }

    /*
    1. Navigate to https://www.weightwatchers.com/us/
    2. Verify loaded page title matches “WW (Weight Watchers): Weight Loss & Wellness Help”
    3. On the right corner of the page, click on “Find a Studio”
    4. Verify loaded page title contains “Find WW Studios & Meetings Near You | WW USA”
    5. In the search field, search for meetings for zip code: 10011
    6. Print the title of the first result and the distance (located on the right of location title/name)
    7. Click on the first search result and then, verify displayed location name/title matches with the name of the first searched result that was clicked.
    8. From this location page, print TODAY’s hours of operation (located towards the bottom of the page)
    9. Create a method to print the number of meeting the each person(under the scheduled time) has a particular day of the week

    */
    // Please use ID or class as selectors.

    @Test
    public void scheduleTimeTest() throws InterruptedException, IOException {

        // Step 1
        startUp(getProperty("url"));
        HomePage hp = new HomePage();

        String HomePageExpectTitle=getProperty("HomePageExpectTitle");
        String FindAMeetingExpectTitle=getProperty("FindAMeetingExpectTitle");

        // Step 2
        Assert.assertEquals(showTitle(),HomePageExpectTitle);
        verify(showTitle(),HomePageExpectTitle);
        // Step 3
        hp.clickFindMeeting();

        // Step 4
        FindAMeeting fm = new FindAMeeting();
        if(!showTitle().contains(FindAMeetingExpectTitle))
            System.out.println("Title didn't match");
        else
            System.out.println("Verified title contains "+ FindAMeetingExpectTitle);

        // Step 5
        fm.meetingSearch(getProperty("zipCode"),true);

        // Step 6
        int locationNumber =Integer.parseInt(getProperty("selectionNumber"));
        String firstLocation = getTextFromList(fm.getLocationNameList(),locationNumber -1);
        String firstLocationDistance = getTextFromList(fm.getLocationDistanceList(),locationNumber -1);
        System.out.println("Selected location name: " + firstLocation + "\nSelected location distance : "+firstLocationDistance);
        String currentTitle =showTitle();

        // Step 7
        clickListByIndex(fm.getLocationDistanceList(),locationNumber -1);
        String locationNameAfterClick = fm.getLocationName().getText();
        verify(firstLocation,locationNameAfterClick);

        // Step 8
        String dayAndHours = fm.getCurrntDay().getText();
        System.out.println("_______________\nToday's Day and Hours : \n"+dayAndHours + "\n");

        // Step 9
        for (String day: DAYSARRAY) {
            fm.getLeaderNameByDay(day);
        }
    }

    @AfterMethod
    public void closeAll(){
        cleanUp();
    }
}
