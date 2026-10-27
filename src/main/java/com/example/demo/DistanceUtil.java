package com.example.demo;

public class DistanceUtil {

    // Calculates distance in kilometres between two GPS coordinates
    public static double dist(double lat1, double lon1, double lat2, double lon2) {
        double a1 = Math.toRadians(lat1);
        double a2 = Math.toRadians(lat2);
        double dlat = Math.toRadians(lat2 - lat1);
        double dlon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2)
                + Math.cos(a1) * Math.cos(a2)
                * Math.sin(dlon / 2) * Math.sin(dlon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return 6371 * c;
    }
}
