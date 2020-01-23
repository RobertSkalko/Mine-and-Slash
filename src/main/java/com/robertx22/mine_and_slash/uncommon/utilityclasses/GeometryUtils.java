package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraft.util.math.Vec3d;

public class GeometryUtils {

    public static Vec3d getRandomHorizontalPosInRadiusCircle(Vec3d pos, float radius) {
        return getRandomHorizontalPosInRadiusCircle(pos.x, pos.y, pos.z, radius);
    }

    public static Vec3d getRandomHorizontalPosInRadiusCircle(double x, double y, double z, float radius) {

        double u = Math.random();
        double v = Math.random();
        double theta = 2 * Math.PI * u;
        double phi = Math.acos(2 * v - 1);
        double xpos = x + (radius * Math.sin(phi) * Math.cos(theta));
        double ypos = y;
        double zpos = z + (radius * Math.cos(phi));

        return new Vec3d(xpos, ypos, zpos);

    }

    // todo unsure if works
    public static Vec3d getRandomPosInRadiusCircle(double x, double y, double z, float radius) {

        double u = Math.random();
        double v = Math.random();
        double theta = 2 * Math.PI * u;
        double phi = Math.acos(2 * v - 1);
        double xpos = x + (radius * Math.sin(phi) * Math.cos(theta));
        double ypos = y + (radius * Math.cos(phi));
        double zpos = z + (radius * Math.cos(phi));

        return new Vec3d(xpos, ypos, zpos);

    }

}
