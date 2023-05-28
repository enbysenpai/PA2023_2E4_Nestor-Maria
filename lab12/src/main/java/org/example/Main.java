package org.example;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Main
{
    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;
    public static List<String> toAnalyse=new ArrayList<>();

    public static void main(String[] args)
            throws MalformedURLException
    {
        Scanner scanner=new Scanner(System.in);
        String path=scanner.nextLine();

        File file=new File(path);
        if(file.isDirectory())
        {
            analyseDirectory(file);
        }
        else if(file.isFile() && file.getName().endsWith(".jar"))
        {
            analyseJar(file);
        }
        else if(file.isFile() && file.getName().endsWith(".class"))
        {
            String basePath= file.getPath().substring(0,file.getPath().lastIndexOf(file.getName())-1);
            String className=getClassName(file.getAbsolutePath(),basePath);
            analyseClass(className);
        }
    }

    private static void analyseDirectory(File directory)
    {
        File[] files=directory.listFiles();

        if(files != null)
        {
            for(File file:files)
            {
                if (file.isFile() && file.getName().endsWith(".class"))
                {
                    String className=getClassName(file.getAbsolutePath(),directory.getAbsolutePath());

                    analyseClass(className);
                }
                else if(file.isFile() && file.getName().endsWith(".jar"))
                {
                    analyseJar(file);
                }
                else if(file.isDirectory())
                {
                    analyseDirectory(file);
                }
            }
        }
    }

    private static String getClassName(String path,String basePath)
    {
        if(path.endsWith(".class"))
        {
            path=path.substring(0,path.length()-".class".length());
        }

        basePath=basePath.substring(0,basePath.lastIndexOf(File.separator));
        basePath=basePath.substring(0,basePath.lastIndexOf(File.separator));

        if(path.startsWith(basePath))
        {
            path=path.substring(basePath.length()+1);
        }

        path=path.replace(File.separator,".");

        return path;
    }

    private static void analyseClass(String className)
    {

        try {
            System.out.println("Class: " + className);
            System.out.println("Methods: ");
            for (Method method : Class.forName(className).getDeclaredMethods()) {
                System.out.println(method.getReturnType() + " " + method.getName() + "(");
                for (var param : method.getParameterTypes()) {
                    System.out.println(param.getName() + " ");
                }
                System.out.println(")");
            }
            System.out.println("\nTest methods:");
            for(Method method:Class.forName(className).getMethods())
            {
                if(method.isAnnotationPresent(Test.class))
                {
                    totalTests++;
                    System.out.println("Running test: " + method.getName());
                    try {
                        Object instance = null;
                        if (!Modifier.isStatic(method.getModifiers())) {
                            instance = Class.forName(className).newInstance();
                        }

                        // Generate mock values for int or String arguments
                        Object[] args = new Object[method.getParameterCount()];
                        for (int i = 0; i < args.length; i++) {
                            Class<?> paramType = method.getParameterTypes()[i];
                            if (paramType == int.class) {
                                args[i] = 0; // Set a mock int value
                            } else if (paramType == String.class) {
                                args[i] = "mock"; // Set a mock String value
                            }
                        }

                        method.invoke(instance, args);
                        passedTests++;
                        System.out.println("Test passed: " + method.getName());
                    } catch (Exception e) {
                        failedTests++;
                        System.err.println("Test failed: " + method.getName());
                        e.printStackTrace();
                    }
                }
            }

            System.out.println("Statistics:\ntotal test: "+totalTests);
            System.out.println("passed tests: "+passedTests);
            System.out.println("failed tests: "+failedTests);
            System.out.println();
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("Couldn't load: "+className);
        }
    }

    private static void analyseJar(File file)
    {
        try(JarFile jarFile=new JarFile(file))
        {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    String className = entry.getName().replace("/", ".").replaceAll(".class", "");
                    analyseClass(className);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}