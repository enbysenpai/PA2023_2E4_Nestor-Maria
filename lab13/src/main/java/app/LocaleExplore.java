package app;

import comm.DisplayLocales;
import comm.Info;
import comm.SetLocale;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    public static void main(String[] args) {

        DisplayLocales displayLocales = new DisplayLocales();
        SetLocale setLocale = new SetLocale();
        Info info = new Info();

        Scanner scanner = new Scanner(System.in);

        String command;
        String[] input;

        String currentLocale = "United States";

        ResourceBundle resources = ResourceBundle.getBundle("Messages");


        while (true) {
            System.out.println(resources.getString("prompt"));
            command = scanner.nextLine();
            input = command.split(" ");

            switch (input[0]) {
                case "info":
                    System.out.println(MessageFormat.format(resources.getString("info"), currentLocale));
                    info.getInfo(Locale.getDefault());
                    break;
                case "display":
                    System.out.println(resources.getString("locales"));
                    displayLocales.display();
                    break;
                case "set":
                    Locale locale = new Locale(input[1], input[2]);

                    setLocale.set(locale);

                    resources = ResourceBundle.getBundle("Messages", locale);

                    currentLocale = locale.getDisplayCountry();

                    System.out.println(MessageFormat.format(resources.getString("locale.set"), currentLocale));
                    break;
                case "exit":
                    return;
                default:
                    System.out.println(resources.getString("invalid"));
            }
        }
    }
}