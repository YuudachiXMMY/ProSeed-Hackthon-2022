/*
 * Copyright (C) 2021 Hal Perkins.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Winter Quarter 2021 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package setup;

import java.util.Comparator;

/**
 * This is a simple object that has a volume.
 */
// You may not make Ball implement the Comparable interface.
public class Ball {

    /**
     * The volume of the Ball.
     */
    private double volume;

    /**
     * Constructor that creates a new ball object with the specified volume.
     *
     * @param volume Volume of the new object.
     */
    public Ball(double volume) {
        this.volume = volume;
    }

    /**
     * Returns the volume of the Ball.
     *
     * @return the volume of the Ball.
     */
    public double getVolume() {
        return volume;
    }

    /**
     * A static class which implements a Comparator
     */
    public static class myComparator implements Comparator<Ball> {
        /**
         * Compares its two balls for order. Returns a negative integer, zero, or
         * a positive integer as the first ball is less than, equal to, or
         * greater than the second.
         *
         * @param b1 the first ball to be compared.
         * @param b2 the second ball to be compared.
         * @return a negative integer, zero, or a positive integer as the first
         * ball is less than, equal to, or greater than the second.
         */
        public int compare(Ball b1, Ball b2) {
            return Double.compare(b1.getVolume(), b2.getVolume());
        }
    }

}