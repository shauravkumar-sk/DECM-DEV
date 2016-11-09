package com.practise.dfc.object;

import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.documentum.fc.common.DfLogger;
import com.practise.dfc.connection.Connection;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Administrator on 09-09-2016.
 */
public class ByteArrayInpuStreamToExcel {
    public static Logger logger= DfLogger.getLogger(ByteArrayInpuStreamToExcel.class);
    public static void main(String[] args) throws DfException, IOException, InvalidFormatException {
        Connection connection=new Connection();
        IDfSession iDfSession=connection.obtainASession();
        try {
            IDfDocument document = (IDfDocument) iDfSession.getObject(new DfId("090000018001ea38"));
            ByteArrayInputStream inputStream = document.getContent();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            Row row=firstSheet.getRow(0);
            Iterator<Row> iterator = firstSheet.iterator();
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
                while (cellIterator.hasNext()) {
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
            inputStream.close();


        }finally {
            connection.releaseSession();
        }
    }
}
