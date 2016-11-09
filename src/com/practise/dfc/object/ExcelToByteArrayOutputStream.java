package com.practise.dfc.object;

import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLogger;
import com.practise.dfc.connection.Connection;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Administrator on 09-09-2016.
 */
public class ExcelToByteArrayOutputStream {
    public static Logger logger = DfLogger.getLogger(ExcelToByteArrayOutputStream.class);

    public static void main(String[] args) throws DfException {
//        Workbook workbook= new XSSFWorkbook();
//        FileOutputStream fileOut = new FileOutputStream("poi-test.xls");
       try{ Workbook workbook = new HSSFWorkbook();
        Sheet worksheet = workbook.createSheet("POI Worksheet");

        // index from 0,0... cell A1 is cell(0,0)
        Row row1 = worksheet.createRow((short) 0);

        Cell cellA1 = row1.createCell((short) 0);
        cellA1.setCellValue("Hello");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellA1.setCellStyle(cellStyle);

        Cell cellB1 = row1.createCell((short) 1);
        cellB1.setCellValue("Goodbye");
        cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellB1.setCellStyle(cellStyle);

        Cell cellC1 = row1.createCell((short) 2);
        cellC1.setCellValue(true);

        Cell cellD1 = row1.createCell((short) 3);
        cellD1.setCellValue(new Date());
        cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat
                .getBuiltinFormat("m/d/yy h:mm"));
        cellD1.setCellStyle(cellStyle);
           ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        workbook.write(outputStream);
           Connection connection=new Connection();
           IDfSession session=connection.obtainASession();
           IDfDocument document=(IDfDocument)session.newObject("dm_document");
           document.setObjectName("Excel from Java");
           document.setContentType("excel8book");
           document.setContent(outputStream);
           document.link("/Administrator");
           document.save();
           logger.info(document.getObjectId());
           outputStream.flush();
           outputStream.close();
           connection.releaseSession();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
       }
}

}
