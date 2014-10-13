package com.ars.common.util;

import java.io.IOException;
import java.util.Scanner;

public class IOUtils
{
    public static String inputString()
    {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
    
    public static void print(String str)
    {
        System.out.print(str);
    }
    
    public static void println(String str)
    {
        System.out.println(str);
    }
    
    public static void inputTip()
    {
        print("Input:");
    }
    
    public static void printControlMenu()
    {
        println("");
        println("P.Previous Menu");
        println("Q.Quit");
        println("****************************************");
    }
    
    public static void printTitle(String title)
    {
        println("****************************************");
        println(title);
        println("****************************************");
        
    }
    
    public static void readAnyKey()
    {
        try
        {
            System.in.read();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
