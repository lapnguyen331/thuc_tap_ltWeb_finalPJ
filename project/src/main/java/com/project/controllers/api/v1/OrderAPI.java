package com.project.controllers.api.v1;

import com.project.dao_rework.OrderDetailsDAO;
import com.project.dao_rework.ProductDAO;
import com.project.db.JDBIConnector;
import com.project.dto.mapper.product.ProductCardDTOMapper;
import com.project.dto.response.product.ProductCardDTO;
import com.project.filters.RestResponseDTOApiFilter;
import com.project.models_rework.enums.OrderDetailsStatus;
import com.project.service_rework.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jdbi.v3.core.Handle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderAPI_v1", value = "/api/v1/order/*")
public class OrderAPI extends HttpServlet {
    private Handle handle;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.handle = JDBIConnector.get().open();
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        this.handle.close();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        switch (action) {
            case "/getHandled":
                doGetHandled(request, response);
                break;
        }
    }

    private void doGetHandled(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        List<Integer> productIds = handle.attach(OrderDetailsDAO.class)
                .getByIdAndStatus_productId(id, OrderDetailsStatus.CHUA_XU_LY);
        List<ProductCardDTO> dto = new ArrayList<>();
        for (Integer productId : productIds) {
            var product = handle.attach(ProductDAO.class).getById_all(productId).get(0);
            dto.add(ProductCardDTOMapper.INSTANCE.mapToDTO(handle, product));
        }
        request.setAttribute(RestResponseDTOApiFilter.PUT_KEY, dto);
    }
}
