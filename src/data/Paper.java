package data;

public class Paper {
    private PaperSize size;
    private PaperType type;
    private double basePrice;

    public Paper(PaperSize size, PaperType type, double basePrice) {
        if (size == null) {
            throw new IllegalArgumentException("Paper size cannot be null");
        }
        if (type == null) {
            throw new IllegalArgumentException("Paper type cannot be null");
        }
        if (basePrice < 0) {
            throw new IllegalArgumentException("Base price cannot be negative");
        }
        this.size = size;
        this.type = type;
        this.basePrice = basePrice;
    }

    public PaperSize getSize() {
        return size;
    }

    public PaperType getType() {
        return type;
    }

    public double getBasePrice() {
        return basePrice;
    }


}