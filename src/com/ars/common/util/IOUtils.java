package com.ars.common.util;

import java.io.Closeable;
import java.io.IOException;
import java.util.Scanner;

public class IOUtils
{
    public static String inputString()
    {
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(System.in);
            return scanner.next();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            closeStream(scanner);
        }
        return StringUtils.EMPTY;
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
    
    public static void closeStream(Closeable... closeables)
    {
        for (Closeable closeable : closeables)
        {
            if (null != closeable)
            {
                try
                {
                    closeable.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
