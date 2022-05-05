package org.rekrutacjahorus;

class BuildingBlock implements Block {

    protected String color;
    protected String material;

    public BuildingBlock() {
    }

    public BuildingBlock(String color, String material) {
        this.color = color;
        this.material = material;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getMaterial() {
        return this.material;
    }
}
