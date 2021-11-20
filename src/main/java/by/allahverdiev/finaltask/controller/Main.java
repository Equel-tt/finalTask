package by.allahverdiev.finaltask.controller;

import by.allahverdiev.finaltask.dao.postgres.ProductDaoPg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(ProductDaoPg.class);

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list.contains(6));
    }
}



