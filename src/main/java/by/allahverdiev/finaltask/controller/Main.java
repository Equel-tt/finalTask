package by.allahverdiev.finaltask.controller;

public class Main {

    public static void main(String[] args) {
//        BookkeepingService service = new BookkeepingService(DaoFactoryTest.getInstance());
//        try {
//            Connection connection = ConnectionCreator.createConnection();
//            List<Archive> list = service.findAllArchive(connection);
//            for (Archive archive : list) {
//                System.out.println(archive.getProduct().getId() + " " + archive.getCount() + " " + archive.getDate());
//                System.out.println();
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
        String source = "The less than sign (<) and ampersand (&) must be escaped before using them in HTML";
        String escaped = source.replace("&", "&amp;").replace("<", "&lt;");
        System.out.println(escaped);
    }
}



