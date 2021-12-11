package by.allahverdiev.finaltask.controller;

import by.allahverdiev.finaltask.dao.DaoFactoryTest;
import by.allahverdiev.finaltask.dao.testDatabaseConnection.ConnectionCreator;
import by.allahverdiev.finaltask.entity.Archive;
import by.allahverdiev.finaltask.service.BookkeepingService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        BookkeepingService service = new BookkeepingService(DaoFactoryTest.getInstance());
        try {
            Connection connection = ConnectionCreator.createConnection();
            List<Archive> list = service.findAllArchive(connection);
            for (Archive archive : list) {
                System.out.println(archive.getProduct().getId() + " " + archive.getCount() + " " + archive.getDate());
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}



