package com.company;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Driver;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class UnitTestApp {
    Driver driver;


    //Create String of then random characters
    public static String randomandomStrings() {

        String randomString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            int index = (int) (randomString.length() * Math.random());
            sb.append(randomString.charAt(index));
        }
        return sb.toString();
    }

    //Create Thread sleep of 't' miliseconds
    public void setWait(long t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void test() throws InterruptedException, ParseException {
        System.setProperty("webdriver.chrome.driver", "D:\\DF\\Selenium\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.facebook.com");
        driver.manage().window().maximize();
        setWait(1000);

        //Date time for Canada
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        isoFormat.setTimeZone(TimeZone.getTimeZone("GMT-5"));
        Date date = isoFormat.parse("2010-05-23T09:01:02");
        long timeMilli = date.getTime();

        String dateString = Long.toString(timeMilli);


        String random = randomandomStrings();
        String isEmpty;

        //Checking presence of input field on page
        boolean exist = driver.findElement(By.xpath("//input[@name='email']")).isDisplayed();
        Assert.assertTrue("Input field is not displayed on this page!", exist);

        //Checks if the input field is enabeled
        boolean enabled = driver.findElement(By.xpath("//input[@name='email']")).isEnabled();
        Assert.assertTrue("Input field is not displayed on this page!", enabled);

        //checks if the input field has empty
        isEmpty = driver.findElement(By.xpath("//input[@name='email']")).getAttribute("value");
        Assert.assertTrue("Input field is not empty!", isEmpty.equals(""));

        //Input random String of ten characters
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(random);

        setWait(1000);

        //Checks if the text is entered
        String inputText = driver.findElement(By.xpath("//input[@name='email']")).getAttribute("value");
        Assert.assertTrue("Input field text is not as expected!", inputText.equals(random));

        setWait(1000);

        //Delete entered text and checks if the input field has empty
        driver.findElement(By.xpath("//input[@name='email']")).clear();
        String clear = driver.findElement(By.xpath("//input[@name='email']")).getAttribute("value");
        Assert.assertTrue("Input field is not empty!", clear.equals(""));

        setWait(1000);

        //Input current time in Chicago
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(dateString);

        //Delete entered text and checks if the input field has empty
        driver.findElement(By.xpath("//input[@name='email']")).clear();
        String clear1 = driver.findElement(By.xpath("//input[@name='email']")).getAttribute("value");
        Assert.assertTrue("Input field is not empty!", clear.equals(""));


        setWait(1000);

        //Checks if the text is entered
        isEmpty = driver.findElement(By.xpath("//input[@name='email']")).getAttribute("value");
        Assert.assertTrue("Input field is not empty!", isEmpty.equals(""));

        driver.close();


    }

}


