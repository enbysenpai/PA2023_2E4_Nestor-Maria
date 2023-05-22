package org.example;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.net.URL;

public class Main
{
    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException
    {
        MyClassLoader classLoader=new MyClassLoader();

        if(args.length!=2)
        {
            System.out.println("Please provide the path to the .class file and its name");
            return;
        }

        File path=new File(args[0]);
        URL url=path.toURI().toURL();
        classLoader.addURL(url);

        String className=args[1];

        System.out.println("Methods:");
        for(Method method:Class.forName(className).getDeclaredMethods())
        {
            System.out.println(method.getReturnType()+" "+method.getName()+"(");
            for(var param:method.getParameterTypes())
            {
                System.out.println(param.getName()+" ");
            }
            System.out.println(")");
        }

        System.out.println("\nTest methods:");
        for(Method method:Class.forName(className).getMethods())
        {
            if(method.isAnnotationPresent(Test.class))
            {
                try
                {
                    method.invoke(null);
                }
                catch (Throwable e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}