package org.rekrutacjahorus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rekrutacjahorus.exception.ColorMismatchException;
import org.rekrutacjahorus.exception.MaterialMismatchException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompositeBlockTest {

    private final String colorRed = "red";
    private final String colorBlue = "blue";
    private final String materialClay = "clay";
    private final String materialConcrete = "concrete";

    @Test
    @DisplayName("Create a composite block with a single block")
    void createCompositeBlock() {

        Block block = new BuildingBlock(colorRed, materialClay);
        CompositeBlock compositeBlock = new CompositeBuildingBlock(block);

        assertEquals(colorRed, compositeBlock.getColor());
        assertEquals(materialClay, compositeBlock.getMaterial());
        assertEquals(1, compositeBlock.getBlocks().size());
    }

    @Test
    @DisplayName("Create a composite block with multiple blocks")
    void createCompositeBlockWithMultipleBlocks() throws MaterialMismatchException, ColorMismatchException {

        List<Block> blocks = List.of(
                new BuildingBlock(colorRed, materialClay),
                new BuildingBlock(colorRed, materialClay),
                new BuildingBlock(colorRed, materialClay));

        CompositeBlock compositeBlock = new CompositeBuildingBlock(blocks);

        assertEquals(colorRed, compositeBlock.getColor());
        assertEquals(materialClay, compositeBlock.getMaterial());
        assertEquals(3, compositeBlock.getBlocks().size());
    }

    @Test
    @DisplayName("Create a composite block with multiple blocks with mismatching colors")
    void createCompositeBlockWithMultipleBlocksMismatchingColors() {

        List<Block> blocks = List.of(new BuildingBlock(colorRed, materialClay),
                new BuildingBlock(colorRed, materialClay),
                new BuildingBlock(colorBlue, materialClay));

        assertThrows(ColorMismatchException.class, () -> {
            CompositeBlock compositeBlock = new CompositeBuildingBlock(blocks);
        });
    }

    @Test
    @DisplayName("Create a composite block with multiple blocks with mismatching materials")
    void createCompositeBlockWithMultipleBlocksMismatchingMaterials() {

        List<Block> blocks = List.of(new BuildingBlock(colorRed, materialClay),
                new BuildingBlock(colorRed, materialClay),
                new BuildingBlock(colorRed, materialConcrete));

        assertThrows(MaterialMismatchException.class, () -> {
            CompositeBlock compositeBlock = new CompositeBuildingBlock(blocks);
        });
    }

}
