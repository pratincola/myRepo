import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by prateek on 11/22/14.
 */
public class TestTempConversion {
    private static WebElement user = null;
    private static long end;

    public static boolean testLogin(WebDriver driver) {

        String [] users = {"andy", "bob", "charley"};
        String [] passwords = {"apple", "bathtub", "china"};

        for (int i=0; i <users.length; i++){
            user = driver.findElement(By.name("userId"));
            user.clear();
            user.sendKeys(users[i]);


            user = driver.findElement(By.name("userPassword"));
            user.clear();
            user.sendKeys(passwords[i]);

            user.sendKeys(Keys.ENTER);
            // Wait
            end = System.currentTimeMillis() + 5000;
            while (System.currentTimeMillis() < end) {}

            if (driver.getTitle().equals("Bad Login")) {
                System.out.println("Login attempt failed");
                return false;
            }
            //Go back to test different user
            driver.navigate().back();
        }

        return true;
    }


    public static boolean testCaseSensitivity(WebDriver driver){

        String [] users = {"ANDY", "BoB", "chArley"};
        String [] passwords = {"apple", "bathtub", "china"};
        String [] badPasswords = {"Apple", "bathtUb", "chIna"};

        for (int i=0; i <users.length; i++){
            user = driver.findElement(By.name("userId"));
            user.clear();
            user.sendKeys(users[i]);


            user = driver.findElement(By.name("userPassword"));
            user.clear();
            user.sendKeys(passwords[i]);

            user.sendKeys(Keys.ENTER);
            // Wait
            timeout(5000);


            if (driver.getTitle().equals("Bad Login")) {
                System.out.println("Login attempt failed");
                return false;
            }
            //Go back to test different user
            driver.navigate().back();
        }

        for (int i=0; i <users.length; i++){
            user = driver.findElement(By.name("userId"));
            user.clear();
            user.sendKeys(users[i]);


            user = driver.findElement(By.name("userPassword"));
            user.clear();
            user.sendKeys(badPasswords[i]);

            user.sendKeys(Keys.ENTER);
            // Wait
            timeout(5000);

            if (!driver.getTitle().equals("Bad Login")) {
                System.out.println("Bad Login");
                return false;
            }
            //Go back to test different user
            driver.navigate().back();
        }

        return true;

    }

    public static boolean testTemperaturePrecision(WebDriver driver) {
        String[] validInputs = {"0", "-1", "0.00"};
        String[] invalidInputs = {" ", "3.w", "9.73E2", "9i0"};

        user = driver.findElement(By.name("userId"));
        user.clear();
        user.sendKeys("andy");

        user = driver.findElement(By.name("userPassword"));
        user.clear();
        user.sendKeys("apple");
        user.sendKeys(Keys.ENTER);

        for (int i=0; i<validInputs.length; i++) {
            user = driver.findElement(By.name("farenheitTemperature"));
            user.clear();
            user.sendKeys(validInputs[i]);
            user.sendKeys(Keys.ENTER);

            timeout(5000);
            if (driver.getTitle().equals("Bad Temperature")) {
                System.out.println("Bad Temperature");
                return false;
            }
            driver.navigate().back();
        }

        for (int i=0; i<invalidInputs.length; i++) {
            user = driver.findElement(By.name("farenheitTemperature"));
            user.clear();
            user.sendKeys(invalidInputs[i]);
            user.sendKeys(Keys.ENTER);

            timeout(5000);
            if (!driver.getTitle().equals("Bad Temperature")) {
                System.out.println("Bad Temperature");
                return false;
            }
            driver.navigate().back();
        }

        return true;
    }

    public static void timeout(int t){
        end = System.currentTimeMillis() + t;
        while (System.currentTimeMillis() < end) {}

    }

    public static void main(String[] args) throws Exception {

        WebDriver driver = new FirefoxDriver();

        // Go to the Temperature Conversion Webpage
        driver.get("http://apt-public.appspot.com/testing-lab-login.html");
        timeout(10000);
        testLogin(driver);
        timeout(10000);
        testCaseSensitivity(driver);
        timeout(10000);
        testTemperaturePrecision(driver);
        driver.quit();
     }

}
