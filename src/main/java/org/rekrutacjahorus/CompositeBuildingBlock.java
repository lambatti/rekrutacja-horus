package org.rekrutacjahorus;

import org.rekrutacjahorus.exception.ColorMismatchException;
import org.rekrutacjahorus.exception.MaterialMismatchException;

import java.util.ArrayList;
import java.util.List;

class CompositeBuildingBlock extends BuildingBlock implements CompositeBlock {

    private final List<Block> blocks;

    public CompositeBuildingBlock() {
        blocks = new ArrayList<>();
    }

    public CompositeBuildingBlock(Block block) {
        this.blocks = List.of(block);
        setColorAndMaterial(block);
    }

    public CompositeBuildingBlock(List<Block> blocks) throws ColorMismatchException, MaterialMismatchException {

        setColorAndMaterial(blocks.get(0));

        for (int i = 1; i < blocks.size(); i++) {

            Block currentBlock = blocks.get(i);

            if (!currentBlock.getColor().equals(this.color)) {
                throw new ColorMismatchException("The colors of the composite block do not match.");
            } else if (!currentBlock.getMaterial().equals(this.material)) {
                throw new MaterialMismatchException("The materials of the composite block do not match.");
            }
        }

        this.blocks = blocks;
    }

    private void setColorAndMaterial(Block block) {
        this.color = block.getColor();
        this.material = block.getMaterial();
    }

    @Override
    public List getBlocks() {
        return this.blocks;
    }
}
