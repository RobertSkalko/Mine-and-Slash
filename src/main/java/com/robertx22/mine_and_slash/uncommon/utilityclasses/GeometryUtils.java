package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraft.util.math.Vec3d;

public class GeometryUtils {

    public static Vec3d getRandomHorizontalPosInRadiusCircle(double x, double y, double z,
                                                             float radius) {

        double u = Math.random();
        double v = Math.random();
        double theta = 2 * Math.PI * u;
        double phi = Math.acos(2 * v - 1);
        double xpos = x + (radius * Math.sin(phi) * Math.cos(theta));
        double ypos = y;
        double zpos = z + (radius * Math.cos(phi));

        return new Vec3d(xpos, ypos, zpos);

    }

}
