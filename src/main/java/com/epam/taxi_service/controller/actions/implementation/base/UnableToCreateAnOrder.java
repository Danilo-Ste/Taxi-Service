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
import com.epam.taxi_service.utils.QueryBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

import static com.epam.taxi_service.controller.actions.implementation.Page.INDEX_PAGE;
import static com.epam.taxi_service.controller.actions.implementation.Parameters.*;
import static com.epam.taxi_service.utils.QueryBuilderUtil.orderQueryBuilder;

public class UnableToCreateAnOrder implements Action {

    private CarServices carServices;

    public UnableToCreateAnOrder(AppContext appContext) {
        carServices = appContext.getCarService();
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String path = INDEX_PAGE;

            if(SELECT_AN_ORDER_OPTION=="correctCapacity"){
                QueryBuilder queryBuilder = getQueryBuilderWithCapacity(request);
                List<CarDTO> carDTOsWithCorrectCapacity = carServices.getSortedCars(queryBuilder.getQuery());
                if(!carDTOsWithCorrectCapacity.isEmpty()){
                    OrderDTO order = getOrderDTO(request,carDTOsWithCorrectCapacity.get(0).getId());
                }
            }
        return INDEX_PAGE;
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

    private QueryBuilder getQueryBuilderWithCapacity(HttpServletRequest request) {
        System.out.println(request.getParameter(NUMBER_OF_PEOPLE));
        return orderQueryBuilder()
                .setCategory_IdFilter(request.getParameter(NUMBER_OF_PEOPLE))
                .setStateFilter(String.valueOf(State.AVAILABLE.getValue()));
    }

}
