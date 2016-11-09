package com.practise.utility;

import com.documentum.fc.common.DfLogger;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 05-09-2016.
 */
public class Utility {
    public static Logger logger = DfLogger.getLogger(Utility.class);

    public static ByteArrayOutputStream readFile(String fileName) throws IOException {
        File file = new File(fileName);
        ByteArrayOutputStream ous = null;
        InputStream ios = null;
        byte[] buffer = new byte[(int) file.length()];
        ous = new ByteArrayOutputStream();
        ios = new FileInputStream(file);
        int read = 0;
        while ((read = ios.read(buffer)) != -1) {
            ous.write(buffer, 0, read);
        }
        return ous;
    }

    public static ByteArrayOutputStream readFile(File file) throws IOException {
        ByteArrayOutputStream ous = null;
        InputStream ios = null;
        byte[] buffer = new byte[(int) file.length()];
        ous = new ByteArrayOutputStream();
        ios = new FileInputStream(file);
        int read = 0;
        while ((read = ios.read(buffer)) != -1) {
            ous.write(buffer, 0, read);
        }
        return ous;
    }

    public static void main(String[] args) throws IOException {
//        ByteArrayOutputStream baos=readFile("E:\\test.txt");
//        System.out.println(baos.toString());
        getAttributes("select r_object_id,r_object_type as objectType,object_name,title as testTitle from project_doc");

    }

    public static boolean generateCSVReport(String filename, String fieldSeparator, Map<String, List<String>> data) throws IOException {
        File f = new File(filename);
        if (f.exists() && !f.isDirectory()) {
            f.delete();
        }
        FileWriter fileWriter = new FileWriter(filename);
        int rows = 0;
        for (String key : data.keySet()) {
            fileWriter.append(key);
            fileWriter.append(fieldSeparator);
            rows = data.get(key).size();
        }
        fileWriter.append("\n");
        for (int i = 0; i < rows; i++) {
            for (String key : data.keySet()) {
                fileWriter.append(data.get(key).get(i) != null ? data.get(key).get(i).toString() : "");
                fileWriter.append(fieldSeparator);
            }
            fileWriter.append("\n");
        }
        fileWriter.close();
        logger.info("Report file " + filename + " created successfully.");
        return true;

    }

    public static StringBuffer convertToStringBuffer(Map<String, List<String>> data, String fieldSeparator) {
        StringBuffer ouputBuffer = new StringBuffer();
        int rows = 0;
        for (String key : data.keySet()) {
            ouputBuffer.append(key);
            ouputBuffer.append(fieldSeparator);
            rows = data.get(key).size();
        }
        ouputBuffer.append(System.getProperty("line.separator"));
        for (int i = 0; i < rows; i++) {
            for (String key : data.keySet()) {
                ouputBuffer.append(data.get(key).get(i) != null ? data.get(key).get(i).toString() : "");
                ouputBuffer.append(fieldSeparator);
            }
            ouputBuffer.append(System.getProperty("line.separator"));
        }

        return ouputBuffer;
    }

    public static List<String> getAttributes(String query) {
        // String query="select r_object_id,r_object_type as objectType,object_name,title from project_doc";
        List<String> attributes = new ArrayList<>();
        String[] temp = query.split(",");
        for (int i = 0; i < temp.length - 1; i++) {
            String[] temp2 = temp[i].trim().split(" ");
            attributes.add(temp2[temp2.length - 1]);
        }
        attributes.add(temp[temp.length - 1].trim().split(" ")[0]);
        logger.info(attributes.toString());
        return attributes;
    }

    public static Map<String, List<String>> getMapFromExcelStream(ByteArrayInputStream inputStream) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        //sheet.
        Row row = sheet.getRow(0);
       // row.get
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while (cellIterator.hasNext()) {
                //reading column header
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        break;
                }
                System.out.print(" - ");
            }
            System.out.println();
        }

        workbook.close();

        return null;
    }

    public static File convertByteArrayOutputStreamToFile(ByteArrayOutputStream outputStream){
        //File file=new File();

        return null;
    }
}
