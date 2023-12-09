package edu.project4.processor;

import edu.project4.FractalImage;

@FunctionalInterface
public interface ImageProcessor {
    void process(FractalImage image);
}
