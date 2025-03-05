package test;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class LoginPage {
    WebDriver driver;
    By Name = By.id("name-input");
    By Password = By.xpath("//form//input[@type=\"password\"]");
    By Drinks = By.name("fav_drink");
    By Color = By.cssSelector("input[type='radio']");
    By LoveAuto = By.xpath("//*[@id=\"automation\"]");
    By Autos = By.xpath("//*[@id=\"feedbackForm\"]/ul/li");
    By Email = By.id("email");
    By Message = By.cssSelector("textarea#message");
    By Button = By.xpath("//button[text()='Submit']");
    public enum Colors{
        Red (0),
        Blue (1),
        Yellow (2),
        Green (3),
        FFC0CB (4);
        private int color;
        Colors(int c){
            this.color = c;
        }
        public int getNum(){ return color;}
    }
    public enum DrinksEnum{
        Water(0),
        Milk(1),
        Coffee(2),
        Wine(3),
        CtrlAltDelight(4);
        private int num;
        DrinksEnum(int n){
            num = n;
        }
        public int GetNum() {return num;}
    }

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
    public void setName(String name){
        new Actions(driver).moveToElement(driver.findElement(Name)).click().sendKeys(name).perform();
    }
    public void setPassword(String password){
        new Actions(driver).moveToElement(driver.findElement(Password)).click().sendKeys(password).perform();
    }
    public void setDrinks(DrinksEnum drs[]){
        List<WebElement> boxes = driver.findElements(Drinks);
        for(int i = 0; i<drs.length;i++) {
            new Actions(driver).moveToElement(boxes.get(drs[i].GetNum())).click().perform();
        }
    }
    public void setDrink(DrinksEnum drs){
        new Actions(driver).moveToElement(driver.findElements(Drinks).get(drs.GetNum())).click().perform();
    }
    public void setColor(Colors c){
        new Actions(driver).moveToElement(driver.findElements(Color).get(c.getNum())).click().perform();
    }
    public void setLoveAuto(int num){
        new Actions(driver).moveToElement(driver.findElement(LoveAuto)).perform();
        (new Select(driver.findElement(LoveAuto))).selectByIndex(num);
    }
    public void setEmail(String s){
        new Actions(driver).moveToElement(driver.findElement(Email)).click().sendKeys(s).perform();
    }
    public void setMessage(String s){
        new Actions(driver).moveToElement(driver.findElement(Message)).click().sendKeys(s).perform();
    }
    public String[] getAutos(){
        List<WebElement> tools = driver.findElements(Autos);
        String[] string_tools = new String[tools.size()];
        for(int i = 0; i < string_tools.length; i++){
            string_tools[i] = tools.get(i).getText();
        }
        return string_tools;
    }
    public String getLongestAuto(){
        String[] autos = getAutos();
        String longest = autos[0];
        for(int i = 1; i<autos.length; i++){
            if(autos[i].length()>longest.length())
                longest = autos[i];
        }
        return longest;
    }
    public void buttonClick(){
        new Actions(driver).moveToElement(driver.findElement(Button)).click().perform();
    }
    public Alert getAlert(){
        try{
            return driver.switchTo().alert();
        } catch (NoAlertPresentException e){
            return null;
        }
    }
    public void closeAlert(){
        Alert a = getAlert();
        if(a!=null){
            a.accept();
        }
    }
}
