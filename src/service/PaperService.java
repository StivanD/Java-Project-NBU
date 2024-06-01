package service;

import data.Paper;
import data.PrintingHouse;

public interface PaperService {
    double calculatePrice(Paper paper, PrintingHouse printingHouse);
}


