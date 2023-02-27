package com.epam.taxi_service.controller.actions.implementation.base;

import com.epam.taxi_service.Exception.IncorrectFormatException;
import com.epam.taxi_service.Exception.ServiceException;
import com.epam.taxi_service.controller.actions.Action;
import com.epam.taxi_service.controller.context.AppContext;
import com.epam.taxi_service.dto.CarDTO;
import com.epam.taxi_service.dto.OrderDTO;
import com.epam.taxi_service.dto.UserDTO;
import com.epam.taxi_service.models.entities.State;
import com.epam.taxi_service.models.services.CarServices;
import com.epam.taxi_service.models.services.OrderService;
import com.epam.taxi_service.utils.QueryBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

import static com.epam.taxi_service.controller.actions.implementation.Page.CREATE_ORDER_PAGE;
import static com.epam.taxi_service.controller.actions.implementation.Page.INDEX_PAGE;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;
import static com.epam.taxi_service.utils.QueryBuilderUtil.orderQueryBuilder;

public class UnableToCreateAnOrder implements Action {

    private CarServices carServices;
    private OrderService orderServices;

    public UnableToCreateAnOrder(AppContext appContext) {
        carServices = appContext.getCarService();
        orderServices = appContext.getOrderService();
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String path = INDEX_PAGE;
        switch (request.getParameter(SELECT_AN_ORDER_OPTION)) {
            case "correctCapacity":
                QueryBuilder queryBuilderWithCapacity = getQueryBuilderWithCapacity(request);
                List<CarDTO> carDTOsWithCorrectCapacity = carServices.getSortedCars(queryBuilderWithCapacity.getQuery());
                if (!carDTOsWithCorrectCapacity.isEmpty()) {
                    OrderDTO order = getOrderDTO(request, carDTOsWithCorrectCapacity.get(0).getId());
                    orderServices.add(order);
                    path=INDEX_PAGE;
                }
                break;
            case "correctCategory":
                QueryBuilder queryBuilderWithCategory = getQueryBuilderWithCategory(request);
                List<CarDTO> carDTOsWithCorrectCategory = carServices.getSortedCars(queryBuilderWithCategory.getQuery());
                if (!carDTOsWithCorrectCategory.isEmpty()) {
                    int numOfCars = numberOfCarsToOrder(request,carDTOsWithCorrectCategory);
                    for (int i=0;i<numOfCars;i++){
                        OrderDTO order = getOrderDTO(request,carDTOsWithCorrectCategory.get(i).getId());
                        orderServices.add(order);
                        path = INDEX_PAGE;
                    }
                }
                break;
            case "cancel":
                path = CREATE_ORDER_PAGE;
                break;
        }
        return path;
    }

    private OrderDTO getOrderDTO(HttpServletRequest request, long car_id){
        UserDTO user = (UserDTO) request.getSession().getAttribute(USER);
        return OrderDTO.builder()
                .addressOfDeparture(request.getParameter(ADDRESS_OF_DEPARTURE))
                .addressOfDestination(request.getParameter(ADDRESS_OF_DESTINATION))
                .user_id(Long.parseLong(request.getParameter(String.valueOf(user.getId()))))
                .car_id(car_id)
                .build();
    }

    private int numberOfCarsToOrder(HttpServletRequest request, List<CarDTO> carDTOsWithCorrectCategory){
        int numberOfPeople = Integer.parseInt(request.getParameter(NUMBER_OF_PEOPLE));
        int capacityOfSelectedCars=0;
        for (int i = 0;i<carDTOsWithCorrectCategory.size()-1;i++){
            capacityOfSelectedCars = capacityOfSelectedCars + carDTOsWithCorrectCategory.get(i).getCapacity();
            if(capacityOfSelectedCars>=numberOfPeople)return i;
        }
        return 0;
    }

    private QueryBuilder getQueryBuilderWithCapacity(HttpServletRequest request) {
        return orderQueryBuilder()
                .setCapacityFilter(request.getParameter(NUMBER_OF_PEOPLE))
                .setStateFilter(String.valueOf(State.AVAILABLE.getValue()));
    }
    private QueryBuilder getQueryBuilderWithCategory(HttpServletRequest request) {
        return orderQueryBuilder()
                .setCategory_IdFilter(request.getParameter(CATEGORY_ID))
                .setStateFilter(String.valueOf(State.AVAILABLE.getValue()));
    }

}
