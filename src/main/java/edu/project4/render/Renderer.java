package edu.project4.render;

import edu.project4.FractalImage;
import edu.project4.transformation.Transformation;
import java.util.List;

@FunctionalInterface
public interface Renderer {
    FractalImage render(FractalFlameConfiguration configuration, List<Transformation> variations);
}
