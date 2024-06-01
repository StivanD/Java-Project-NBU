package data;

import java.util.Objects;
import java.util.UUID;

public abstract class Employee implements Comparable<Employee> {
    protected UUID uuid;
    protected String name;
    protected double basePay;

    public Employee(String name, double basePay) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (basePay < 0) {
            throw new IllegalArgumentException("Base pay cannot be negative");
        }
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.basePay = basePay;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public double getBasePay() {
        return basePay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(uuid, employee.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public int compareTo(Employee other) {
        return this.uuid.compareTo(other.uuid);
    }
}