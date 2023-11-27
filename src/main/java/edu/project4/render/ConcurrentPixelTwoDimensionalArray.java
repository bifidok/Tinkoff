package edu.project4.render;

import edu.project4.Pixel;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Two-dimensional array of objects
 * <p>
 * <strong>Warning:</strong> The number of columns must be the same
 */
public class ConcurrentPixelTwoDimensionalArray {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Pixel[][] array;

    public ConcurrentPixelTwoDimensionalArray(int rows, int columns) {
        array = new Pixel[rows][columns];
    }

    public ConcurrentPixelTwoDimensionalArray(Pixel[][] array) {
        this.array = array;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].length != array[i + 1].length) {
                throw new IllegalArgumentException("The number of columns must be the same");
            }
        }
    }

    public Pixel get(int row, int col) {
        Pixel value;
        synchronized (this) {
            value = array[row][col];
        }
        return value;
    }

    public void set(int row, int col, Pixel value) {
        synchronized (this) {
            array[row][col] = value;
        }
    }

    public Pixel[][] toArray() {
        if (array.length == 0) {
            return new Pixel[0][0];
        }
        Pixel[][] newArray = new Pixel[array.length][array[0].length];
        synchronized (this) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    newArray[i][j] = array[i][j];
                }
            }
        }
        return newArray;
    }
}
