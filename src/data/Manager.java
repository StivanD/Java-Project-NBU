package data;

public class Manager extends Employee {
    private double revenueThreshold;
    private double bonusPercentage;

    public Manager(String name, double basePay, double revenueThreshold, double bonusPercentage) {
        super(name, basePay);
        if (revenueThreshold < 0) {
            throw new IllegalArgumentException("Revenue threshold cannot be negative");
        }
        if (bonusPercentage < 0 || bonusPercentage > 100) {
            throw new IllegalArgumentException("Bonus percentage must be between 0 and 100");
        }
        this.revenueThreshold = revenueThreshold;
        this.bonusPercentage = bonusPercentage;
    }

    public double getRevenueThreshold() {
        return revenueThreshold;
    }

    public double getBonusPercentage() {
        return bonusPercentage;
    }
}