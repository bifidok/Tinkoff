package edu.project4.render;

public record FractalFlameConfiguration(int width,
                                        int height,
                                        int samples,
                                        int iterPerSample,
                                        int symmetry) {
}
