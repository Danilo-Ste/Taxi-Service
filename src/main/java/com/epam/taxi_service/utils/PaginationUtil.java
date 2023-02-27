package com.epam.taxi_service.utils;

import jakarta.servlet.http.HttpServletRequest;

import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;

/**
 * Configure request to set all required for page surfing attributes
 * @author Danylo Stetsiuk
 */

public class PaginationUtil {


    private static int getInt(String value, int min, int defaultValue) {
        try {
            int records = Integer.parseInt(value);
            if (records >= min) {
                return records;
            }
        } catch (NumberFormatException e) {
            return defaultValue;
        }
        return defaultValue;
    }

    /**
     * Calculates required attributes and sets them to request. In case of wrong offset and/or records - sets
     * @param totalRecords - total number of records
     */

    public static void paginate(int totalRecords, HttpServletRequest request) {
        int offset = getInt(request.getParameter(OFFSET), 0, 0);
        int records = getInt(request.getParameter(RECORDS), 1, 5);
        setAttributes(request, totalRecords, records, offset);
    }


    private static void setAttributes(HttpServletRequest request, int totalRecords, int records, int offset) {


        int pages = totalRecords / records + (totalRecords % records == 0 ? 0 : 1);
        int currentPage = offset / records + 1;
        int startPage = currentPage == pages ? Math.max(currentPage - 2, 1) : Math.max(currentPage - 1, 1);
        int endPage = Math.min(startPage + 2, pages);


        request.setAttribute(OFFSET, offset);
        request.setAttribute(RECORDS, records);
        request.setAttribute(PAGES, pages);
        request.setAttribute(CURRENT_PAGE, currentPage);
        request.setAttribute(START, startPage);
        request.setAttribute(END, endPage);
    }

    private PaginationUtil() {}
}
