package edu.project4.transformation;

import edu.project4.Point;

public class LinearTransformation implements Transformation{
    @Override
    public Point apply(Point point) {
        return point;
    }
}
