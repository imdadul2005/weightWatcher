package Common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public static WebDriver driver = null;


    public static String getProperty(String key) throws IOException {
        Properties p = prop();
        return p.getProperty(key);
    }

    public static Properties prop() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\Resource\\Config.properties");
        prop.load( fis);
        return prop;
    }


    public void startUp(String url)throws IOException {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(35, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
    }
    public WebDriver getLocalDriver(String OS, String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            if(OS.equalsIgnoreCase("OS X")){
                System.setProperty("webdriver.chrome.driver", "../WWTest/driver/chromedriver");
            }else if(OS.equalsIgnoreCase("Windows")){
                System.setProperty("webdriver.chrome.driver", "../WWTest/driver/chromedriver.exe");
            }
            driver = new ChromeDriver();
        }else if(browserName.equalsIgnoreCase("firefox")){
            if(OS.equalsIgnoreCase("OS X")){
                System.setProperty("webdriver.gecko.driver", "../WWTest/driver/geckodriver");
            }else if(OS.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.gecko.driver", "../WWTest/driver/geckodriver.exe");
            }
            driver = new FirefoxDriver();

        } else if(browserName.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", "../WWTest/driver/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        return driver;

    }

    public static void cleanUp(){
        driver.close();
    }

    public String showTitle(){
        return driver.getTitle();
    }

    public String getTextFromList(List<WebElement> list, int index){
        return list.get(index).getText();
    }

    public void clickListByIndex(List <WebElement> list, int index){
        list.get(index).click();
    }

    public void verify(String actual, String expected){

        if(!actual.equalsIgnoreCase(expected)){
            System.out.println("Expected :" + expected );
            System.out.println("Actual   :"+actual);
        } else
        {
            System.out.println("Verified acutal & expected result matched : "+ actual);
        }

    }

}