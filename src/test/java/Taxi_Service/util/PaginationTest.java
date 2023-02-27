package Taxi_Service.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;
import static com.epam.taxi_service.utils.PaginationUtil.paginate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PaginationTest {
    private final HttpServletRequest request = mock(HttpServletRequest.class);

    @Test
    void testPaginate() {
        when(request.getParameter(OFFSET)).thenReturn(String.valueOf(0));
        when(request.getParameter(RECORDS)).thenReturn(String.valueOf(5));
        MyRequest myRequest = new MyRequest(request);
        paginate(100, myRequest);
        assertEquals(0, myRequest.getAttribute(OFFSET));
        assertEquals(5, myRequest.getAttribute(RECORDS));
        assertEquals(20, myRequest.getAttribute(PAGES));
        assertEquals(1, myRequest.getAttribute(CURRENT_PAGE));
        assertEquals(1, myRequest.getAttribute(START));
        assertEquals(3, myRequest.getAttribute(END));
    }
    @Test
    void testPaginateZeroTotalRecords() {
        when(request.getParameter(OFFSET)).thenReturn(String.valueOf(0));
        when(request.getParameter(RECORDS)).thenReturn(String.valueOf(5));
        MyRequest myRequest = new MyRequest(request);
        paginate(0, myRequest);
        assertEquals(0, myRequest.getAttribute(OFFSET));
        assertEquals(5, myRequest.getAttribute(RECORDS));
        assertEquals(0, myRequest.getAttribute(PAGES));
        assertEquals(1, myRequest.getAttribute(CURRENT_PAGE));
        assertEquals(1, myRequest.getAttribute(START));
        assertEquals(0, myRequest.getAttribute(END));
    }
    @Test
    void testPaginateWrongOffset() {
        when(request.getParameter(OFFSET)).thenReturn("a");
        when(request.getParameter(RECORDS)).thenReturn(String.valueOf(5));
        MyRequest myRequest = new MyRequest(request);
        paginate(100, myRequest);
        assertEquals(0, myRequest.getAttribute(OFFSET));
        assertEquals(5, myRequest.getAttribute(RECORDS));
        assertEquals(20, myRequest.getAttribute(PAGES));
        assertEquals(1, myRequest.getAttribute(CURRENT_PAGE));
        assertEquals(1, myRequest.getAttribute(START));
        assertEquals(3, myRequest.getAttribute(END));
    }
    @Test
    void testPaginateWrongRecords() {
        when(request.getParameter(OFFSET)).thenReturn(String.valueOf(0));
        when(request.getParameter(RECORDS)).thenReturn("a");
        MyRequest myRequest = new MyRequest(request);
        paginate(100, myRequest);
        assertEquals(0, myRequest.getAttribute(OFFSET));
        assertEquals(5, myRequest.getAttribute(RECORDS));
        assertEquals(20, myRequest.getAttribute(PAGES));
        assertEquals(1, myRequest.getAttribute(CURRENT_PAGE));
        assertEquals(1, myRequest.getAttribute(START));
        assertEquals(3, myRequest.getAttribute(END));
    }
    @Test
    void testPaginateNegativeOffset() {
        when(request.getParameter(OFFSET)).thenReturn(String.valueOf(-3));
        when(request.getParameter(RECORDS)).thenReturn(String.valueOf(5));
        MyRequest myRequest = new MyRequest(request);
        paginate(100, myRequest);
        assertEquals(0, myRequest.getAttribute(OFFSET));
        assertEquals(5, myRequest.getAttribute(RECORDS));
        assertEquals(20, myRequest.getAttribute(PAGES));
        assertEquals(1, myRequest.getAttribute(CURRENT_PAGE));
        assertEquals(1, myRequest.getAttribute(START));
        assertEquals(3, myRequest.getAttribute(END));
    }
    @Test
    void testPaginateNegativeRecords() {
        when(request.getParameter(OFFSET)).thenReturn(String.valueOf(0));
        when(request.getParameter(RECORDS)).thenReturn(String.valueOf(-3));
        MyRequest myRequest = new MyRequest(request);
        paginate(100, myRequest);
        assertEquals(0, myRequest.getAttribute(OFFSET));
        assertEquals(5, myRequest.getAttribute(RECORDS));
        assertEquals(20, myRequest.getAttribute(PAGES));
        assertEquals(1, myRequest.getAttribute(CURRENT_PAGE));
        assertEquals(1, myRequest.getAttribute(START));
        assertEquals(3, myRequest.getAttribute(END));
    }
    private static class MyRequest extends HttpServletRequestWrapper {
        private final Map<String, Object> attributes = new HashMap<>();
        public MyRequest(HttpServletRequest request) {
            super(request);
        }

        @Override
        public void setAttribute(String name, Object object) {
            attributes.put(name, object);
        }

        @Override
        public Object getAttribute(String name) {
            return attributes.get(name);
        }
    }
}
