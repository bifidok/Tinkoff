package edu.project4.render;

import edu.project4.transformation.DiskTransformation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RendererTest {
    @Test
    @DisplayName("Однопоточный рендер")
    public void render_shouldComplyConfigurationResolution_whenSingleThread(){
        FractalFlameConfiguration configuration =
            new FractalFlameConfiguration(10,10,10,10,1);
        Renderer renderer = new SingleThreadRenderer();

        var image = renderer.render(configuration, List.of(new DiskTransformation()));

        assertThat(image.height()).isEqualTo(configuration.height());
        assertThat(image.width()).isEqualTo(configuration.width());
        assertThat(image.pixels().length).isEqualTo(configuration.width());
        assertThat(image.pixels()[0].length).isEqualTo(configuration.height());
    }

    @Test
    @DisplayName("Многопоточный рендер")
    public void render_shouldComplyConfigurationResolution_whenMultiThread(){
        FractalFlameConfiguration configuration =
            new FractalFlameConfiguration(10,10,10,10,1);
        Renderer renderer = new MultiThreadRenderer(2);

        var image = renderer.render(configuration, List.of(new DiskTransformation()));

        assertThat(image.height()).isEqualTo(configuration.height());
        assertThat(image.width()).isEqualTo(configuration.width());
        assertThat(image.pixels().length).isEqualTo(configuration.width());
        assertThat(image.pixels()[0].length).isEqualTo(configuration.height());
    }
}
