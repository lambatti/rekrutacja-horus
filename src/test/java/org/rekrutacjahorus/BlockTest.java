package org.rekrutacjahorus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlockTest {

    @Test
    @DisplayName("Create a simple block")
    void createBlock() {

        String color = "red";
        String material = "clay";
        Block block = new BuildingBlock(color, material);

        assertEquals(color, block.getColor());
        assertEquals(material, block.getMaterial());
    }

}
