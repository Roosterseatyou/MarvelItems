package xyz.roosterseatyou.marvelitems.utils;

import xyz.roosterseatyou.marvelitems.MarvelItems;

public class MathUtils {
    public static int getRandom(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    public static int getRandom(int max) {
        return (int) (Math.random() * max);
    }

    public static double getRandomDouble(double min, double max) {
        return Math.random() * (max - min) + min;
    }

    public static boolean rngHelper(double chance) {
        double rand = Math.random();
        MarvelItems.logger().info("RNG: " + rand);
        return rand <= chance;
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static boolean isInRange(int number, int min, int max) {
        return number >= min && number <= max;
    }
}
