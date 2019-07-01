package com.guns21.example.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RecalculationAllFormulas {

    public static void main(String[] args) throws IOException {
//        FileInputStream fis = new FileInputStream("私户易鑫计算器.xlsx" );
        try (InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("私户易鑫计算器.xlsx")) {
            Workbook wb = WorkbookFactory.create(resourceAsStream);
            FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
            for (Sheet sheet : wb) {
                for (Row r : sheet) {
                    for (Cell cell : r) {
                        if (cell.getCellType() == CellType.FORMULA) {
                            CellType cellType = evaluator.evaluateFormulaCell(cell);

                            if (CellType.BOOLEAN == cellType) {
                                System.out.println(cell.getBooleanCellValue());
                            } else if (CellType.NUMERIC == cellType) {
                                System.out.println(cell.getNumericCellValue());
                            } else if (CellType.STRING == cellType) {
                                System.out.println(cell.getStringCellValue());
                            } else if (CellType.BLANK == cellType) {
                            } else if (CellType.ERROR == cellType) {
                                System.out.println(cell.getErrorCellValue());
                            }

                                // CELL_TYPE_FORMULA will never occur
                            }

                    }
                }
            }
        }

    }
}
