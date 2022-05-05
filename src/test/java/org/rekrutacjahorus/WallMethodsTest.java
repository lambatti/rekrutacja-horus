package org.rekrutacjahorus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rekrutacjahorus.exception.ColorMismatchException;
import org.rekrutacjahorus.exception.MaterialMismatchException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WallMethodsTest {

    private final String colorRed = "red";
    private final String colorBlue = "blue";
    private final String materialClay = "clay";
    private final String materialConcrete = "concrete";
    CompositeBlock compositeBlock;
    Wall wall;

    @BeforeEach
    void initializeBlocksAndWall() throws MaterialMismatchException, ColorMismatchException {

        Block buildingBlock = createSimpleBlock(colorRed, materialClay);

        compositeBlock = createCompositeBlock(colorBlue, materialConcrete);

        List<Block> wallBlock = List.of(buildingBlock, compositeBlock);

        wall = new Wall(wallBlock);
    }

    private Block createSimpleBlock(String color, String material) {
        return new BuildingBlock(color, material);
    }

    private CompositeBlock createCompositeBlock(String color, String material)
            throws MaterialMismatchException, ColorMismatchException {

        List<Block> blocks = List.of(
                new BuildingBlock(color, material),
                new BuildingBlock(color, material),
                new BuildingBlock(color, material));

        return new CompositeBuildingBlock(blocks);
    }


    @Test
    @DisplayName("Test findBlockByColor method")
    void testFindBlockByColor() {

        assertTrue(wall.findBlockByColor(colorRed).isPresent());
        assertTrue(wall.findBlockByColor(colorBlue).isPresent());
        assertFalse(wall.findBlockByColor("green").isPresent());
    }

    @Test
    @DisplayName("Test findBlocksByMaterial method")
    void testFindBlocksByMaterial() {

        assertEquals(1, wall.findBlocksByMaterial(materialClay).size());
        assertEquals(compositeBlock.getBlocks().size(), wall.findBlocksByMaterial(materialConcrete).size());
        assertEquals(0, wall.findBlocksByMaterial("dirt").size());
    }

    @Test
    @DisplayName("Test count method")
    void testCount() {

        assertEquals(1 + compositeBlock.getBlocks().size(), wall.count());
    }
}
