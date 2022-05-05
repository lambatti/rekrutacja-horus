package org.rekrutacjahorus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall() {
        this.blocks = new ArrayList<>();
    }

    public Wall(Block block) {
        blocks = List.of(block);
    }

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional findBlockByColor(String color) {

        for (Block block : blocks) {
            if (block.getColor().equals(color)) {
                return Optional.of(block);
            }
        }

        return Optional.empty();
    }

    @Override
    public List findBlocksByMaterial(String material) {

        List<Block> blocksWithGivenMaterial = new ArrayList<>();

        for (Block block : this.blocks) {

            if (block.getMaterial().equals(material)) {

                if (block instanceof CompositeBlock) {
                    List compositeBlock = ((CompositeBlock) block).getBlocks();
                    blocksWithGivenMaterial.addAll(compositeBlock);
                } else {
                    blocksWithGivenMaterial.add(block);
                }

            }
        }

        return blocksWithGivenMaterial;
    }

    @Override
    public int count() {

        int count = 0;

        for (Block block : blocks) {

            if (block instanceof CompositeBlock) {
                count += ((CompositeBlock) block).getBlocks().size();
            } else {
                count++;
            }

        }

        return count;
    }
}


