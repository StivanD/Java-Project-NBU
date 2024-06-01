package data;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class PrintingMachine implements Comparable<PrintingMachine>{
    private UUID uuid;
    private int maxSheets;
    private PrintType printType;
    private int pagesPerMinute;
    private int loadedSheets;
    private Map<String, Integer> printedEditions;

    public PrintingMachine(int maxSheets, PrintType printType, int pagesPerMinute) {
        if (maxSheets < 0) {
            throw new IllegalArgumentException("Max sheets cannot be negative");
        }
        if (pagesPerMinute < 0) {
            throw new IllegalArgumentException("Pages per minute cannot be negative");
        }
        if (printType == null) {
            throw new IllegalArgumentException("Print type cannot be null");
        }

        this.uuid = UUID.randomUUID();
        this.maxSheets = maxSheets;
        this.printType = printType;
        this.pagesPerMinute = pagesPerMinute;
        this.loadedSheets = 0;
        this.printedEditions = new HashMap<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getMaxSheets() {
        return maxSheets;
    }

    public PrintType getPrintType() {
        return printType;
    }

    public int getPagesPerMinute() {
        return pagesPerMinute;
    }

    public int getLoadedSheets() {
        return loadedSheets;
    }

    public void setLoadedSheets(int loadedSheets) {
        this.loadedSheets = loadedSheets;
    }

    public Map<String, Integer> getPrintedEditions() {
        return printedEditions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrintingMachine that = (PrintingMachine) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public int compareTo(PrintingMachine other) {
        return this.uuid.compareTo(other.uuid);
    }
}