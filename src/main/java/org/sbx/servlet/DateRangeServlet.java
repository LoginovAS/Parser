package org.sbx.servlet;

import org.sbx.managers.DataManager;
import org.sbx.objects.ESK363DBRecord;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by aloginov on 27.10.16.
 */
public class DateRangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        DataManager dataManager = new DataManager();

        List<ESK363DBRecord> resultList = dataManager.getByDateRange(startDate, endDate);

        request.setAttribute("records", resultList);

        RequestDispatcher view = request.getRequestDispatcher("records.jsp");

        view.forward(request, response);
    }
}
