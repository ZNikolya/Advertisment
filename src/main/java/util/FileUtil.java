package util;

import model.Item;
import model.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class FileUtil {

    private static final String ITEM_PATH_EXEL = "src\\main\\resources\\item.xlsx";
    private static final String USER_PATH_EXEL = "src\\main\\resources\\users.xlsx";
    private static int ROW_INDEX = 0;
    private static int ROW_IND = 0;
    private static final String USER_PATH = "src\\main\\resources\\serializeUser.obj";
    private static final String ITEM_PATH = "src\\main\\resources\\serializeItem.obj";

//    public static void serializeUser(Map<String, User> userMap) {
//        File serializeUser = new File(USER_PATH);
//        try {
//            if (!serializeUser.exists()) {
//                serializeUser.createNewFile();
//            }
//            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(USER_PATH))) {
//                objectOutputStream.writeObject(userMap);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static Map<String, User> deserializeUser() {
//        Map<String, User> result = new HashMap<>();
//        File serializeUser = new File(USER_PATH);
//        if (serializeUser.exists()) {
//            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(USER_PATH))) {
//                Object ob = objectInputStream.readObject();
//                return (Map<String, User>) ob;
//            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }

//    public static void serializeItem(List<Item> items) {
//        File serializeItem = new File(ITEM_PATH);
//        try {
//            if (!serializeItem.exists()) {
//                serializeItem.createNewFile();
//            }
//            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(ITEM_PATH))) {
//                objectOutputStream.writeObject(items);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static List<Item> deserializeItem() {
//        List<Item> result = new ArrayList<>();
//        File serializeItem = new File(ITEM_PATH);
//        if (serializeItem.exists()) {
//            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(ITEM_PATH))) {
//                Object ob = objectInputStream.readObject();
//                return (List<Item>) ob;
//            } catch (IOException | ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }

    public static void item(List<Item> items) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
                int lasRowNum = sheet.getLastRowNum();
                if (lasRowNum < 0) {
                    lasRowNum = 1;
                }
                for (Item item : items) {
                    for (int i = 1; i <= lasRowNum; i++) {
                        Row row = sheet.createRow(ROW_IND++);
                        row.createCell(0).setCellValue(item.getTitle());
                        row.createCell(1).setCellValue(item.getText());
                        row.createCell(2).setCellValue(item.getPrice());
                        row.createCell(3).setCellValue(item.getCategory().name());
                    }
                }

//        File serializeItem = new File(ITEM_PATH_EXEL);
//        try {
//        if (!serializeItem.exists()) {
//            serializeItem.createNewFile();
//        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(ITEM_PATH_EXEL)) {
            workbook.write(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void user(Map<String, User> users) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        int lasRowNum = sheet.getLastRowNum();
        if (lasRowNum < 0) {
            lasRowNum = 1;
        }
        for (User value : users.values()) {
            for (int i = 1; i <= lasRowNum; i++) {
                Row row = sheet.createRow(ROW_INDEX++);
                row.createCell(0).setCellValue(value.getName());
                row.createCell(1).setCellValue(value.getSurName());
                row.createCell(2).setCellValue(value.getGender().name());
                row.createCell(3).setCellValue(value.getAge());
                row.createCell(4).setCellValue(value.getPhoneNumber());
                row.createCell(5).setCellValue(value.getPassword());
            }
        }


//        File serializeItem = new File(USER_PATH_EXEL);
//        try {
//            if (!serializeItem.exists()) {
//                serializeItem.createNewFile();
//            }
        try (FileOutputStream fileOutputStream = new FileOutputStream(USER_PATH_EXEL)) {
            workbook.write(fileOutputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}