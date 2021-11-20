package by.allahverdiev.finaltask.controller.command.imp;

import by.allahverdiev.finaltask.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

public class FindDeficitCommand implements Command {
    @Override
    public HttpServletRequest execute(HttpServletRequest request, Connection connection) {
//        int length = end.lengthOfMonth();
//        int montNum = end.getMonthValue();
//        int yearNum = end.getYear();
//        LocalDate startDate = LocalDate.of(yearNum,montNum,1);
//        LocalDate endDate = LocalDate.of(yearNum,montNum,length);
        return null;
    }
}
