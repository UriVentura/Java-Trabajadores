package helps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class help {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int putInt(String text) {
        while (true) {
            try {
                System.out.print(text);
                int i = Integer.parseInt(br.readLine());
                return i;
            } catch (IOException e) {
                System.out.println("Error: " + e);
            } catch (NumberFormatException e) {
                System.out.println("Error: Introduce un valor entero");
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }

    public static int putInt(String text, int min, int max) {
        while (true) {
            int i = putInt(text);
            if (i >= min && i <= max) {
                return i;
            } else {
                System.out.println("Introduce un valor entre " + min + " y " + max);
            }
        }
    }

    public static void siguiente(String text) {
        System.out.println(text);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                br.readLine();
            } catch (Exception e) {
        }
    }
    public static void siguiente() {
        siguiente("Presiona ENTER para continuar.");
    }

    public static String entryStr(String text) {
        while (true) {
            try {
                System.out.print(text);
                String stringText = br.readLine();
                return stringText;
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }
}

