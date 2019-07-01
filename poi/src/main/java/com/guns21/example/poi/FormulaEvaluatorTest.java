package com.guns21.example.poi;

import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.POILogger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

public class FormulaEvaluatorTest {

    public static void main(String[] args) {
        // activate logging to console
        System.setProperty("org.apache.poi.util.POILogger", "org.apache.poi.util.SystemOutLogger");
        System.setProperty("poi.log.level", POILogger.INFO + "");

//        try (InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("私户易鑫计算器.xlsx")) {
           try (Workbook wb1 = WorkbookFactory.create(new File("/Volumes/data/Develop/workspace/21guns/example/poi/src/main/resources/私户易鑫计算器.xlsx"))) {
           Workbook wb = WorkbookFactory.create(new File("/Volumes/data/Develop/workspace/21guns/example/poi/src/main/resources/私户易鑫计算器.xlsx"));
//            Workbook wb1 = WorkbookFactory.create(resourceAsStream);
            CreationHelper creationHelper = wb.getCreationHelper();

            Sheet sheet = wb.getSheetAt(0);
            Sheet sheet1 = wb1.getSheetAt(0);
            //
            for (DataValidation dataValidation : sheet.getDataValidations()) {
                CellRangeAddressList regions = dataValidation.getRegions();
                //空值判断
//                if(null == regions || regions.getSize() == 0){
//                    continue;
//                }

                DataValidationConstraint validationConstraint = dataValidation.getValidationConstraint();

                switch (validationConstraint.getValidationType()) {
                    case DataValidationConstraint.ValidationType
                            .LIST:
                        System.out.println(validationConstraint);
                    case DataValidationConstraint.ValidationType.FORMULA:
                        System.out.println(regions.getCellRangeAddresses());
                }
//                System.out.println(Arrays.toString(validationConstraint.getExplicitListValues()));

            }
            //
            // list of functions that POI can evaluate
            Collection<String> supportedFuncs = WorkbookEvaluator.getSupportedFunctionNames();

            // list of functions that are not supported by POI
            Collection<String> unsupportedFuncs = WorkbookEvaluator.getNotSupportedFunctionNames();


            Cell  d = getCell(sheet,"D9");
            Cell  d1 = getCell(sheet,"D9");
//            d.setCellValue(3);
//            System.out.println(d.getCellType());
            Cell input = getCell(sheet,"G11");
            input.setCellValue(4700);
            String[] cells = new String[]{"K12","K13","K14","K15","K16"};
            for (String s : cells) {
                getValue(creationHelper, getCell(sheet,s));
            }
            System.out.println("-------------------------------");
            input.setCellValue(5000);
            System.out.println("-------------------------------" + input.getNumericCellValue());
            for (String s : cells) {
                getValue(creationHelper, getCell(sheet,s));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Cell getCell(Sheet sheet,String cellRef) {
        CellReference cellReference = new CellReference(cellRef);
        Row row = sheet.getRow(cellReference.getRow());
        return row.getCell(cellReference.getCol());
    }

    public static void getValue( CreationHelper creationHelper, Cell cell) {
        FormulaEvaluator evaluator =creationHelper.createFormulaEvaluator();
//        evaluator.setDebugEvaluationOutputForNextEval(true);

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
    }
}
