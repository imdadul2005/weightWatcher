package ObjectRepo;

import Common.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Base{

    public  HomePage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "find-a-meeting") WebElement findMeeting_Link;

    public void clickFindMeeting(){
        findMeeting_Link.click();
    }

    public WebElement getFindMeeting_Link() {
        return findMeeting_Link;
    }
}

