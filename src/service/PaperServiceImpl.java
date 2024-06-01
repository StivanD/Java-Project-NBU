package service;

import data.Paper;
import data.PrintingHouse;

public class PaperServiceImpl implements PaperService {
    @Override
    public double calculatePrice(Paper paper, PrintingHouse printingHouse) {
        double price = printingHouse.getBasePaperPrice();
        switch (paper.getSize()) {
            case A1:
                price *= 4;
                break;
            case A2:
                price *= 3;
                break;
            case A3:
                price *= 2;
                break;
            case A4:
                price *= 1.5;
                break;
            case A5:
                price *= 1;
                break;
        }
        return price;
    }
}