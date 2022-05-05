package org.rekrutacjahorus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rekrutacjahorus.exception.ColorMismatchException;
import org.rekrutacjahorus.exception.MaterialMismatchException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WallConstructorTest {

    private final String colorRed = "red";
    private final String colorBlue = "blue";
    private final String materialClay = "clay";
    private final String materialConcrete = "concrete";


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
    @DisplayName("Create an empty wall")
    void createEmptyWall() {

        Wall wall = new Wall();

        assertEquals(0, wall.count());
    }

    @Test
    @DisplayName("Create a wall with one simple block")
    void createWallWithSimpleBlock() {

        Block block = createSimpleBlock(colorBlue, materialClay);

        Wall wall = new Wall(block);

        assertEquals(1, wall.count());
    }

    @Test
    @DisplayName("Create a wall with one composite block")
    void createWallWithCompositeBlock() throws MaterialMismatchException, ColorMismatchException {

        CompositeBlock block = createCompositeBlock(colorBlue, materialClay);

        Wall wall = new Wall(block);

        assertEquals(block.getBlocks().size(), wall.count());
    }

    @Test
    @DisplayName("Create a wall with mixed type blocks")
    void createWallWithMixedBlocks() throws MaterialMismatchException, ColorMismatchException {

        Block buildingBlock = createSimpleBlock(colorRed, materialClay);

        CompositeBlock compositeBlock = createCompositeBlock(colorBlue, materialConcrete);

        List<Block> wallBlock = List.of(buildingBlock, compositeBlock);

        Wall wall = new Wall(wallBlock);

        assertEquals(1 + compositeBlock.getBlocks().size(), wall.count());
    }

}
