package com.challenge.util;

public class CoordinateUtil {

    /**
     * Calculate distance between 2 points in latitude and longitude
     * lat1, lon1 Start point lat2, lon2
     *
     * @returns Distance in Miles or KM
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(degTorad(lat1)) * Math.sin(degTorad(lat2)) + Math.cos(degTorad(lat1)) * Math.cos(degTorad(lat2)) * Math.cos(degTorad(theta));
        dist = Math.acos(dist);
        dist = radTodeg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        }
        return (dist);
    }

    /**
     * Convert degree to Radio
     */
    private static double degTorad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /**
     * Convert radians to decimal degrees
     */
    private static double radTodeg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public static String formatDistanceToStringKm(double distance){
        return String.format("%.2f", distance) + "Km";
    }
}
