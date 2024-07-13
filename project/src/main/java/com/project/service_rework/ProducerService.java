package com.project.service_rework;

import com.project.dao_rework.ProducerDAO;
import com.project.dao_rework.ProductDAO;
import com.project.dto.mapper.producer.ProducerIDNameDTOMapper;
import com.project.dto.mapper.product.ProductCardDTOMapper;
import com.project.dto.mapper.product.ProductDetailsDTOMapper;
import com.project.dto.response.producer.ProducerIDNameDTO;
import com.project.dto.response.product.ProductCardDTO;
import com.project.dto.response.product.ProductDetailsDTO;

import java.util.List;

public class ProducerService extends AbstractService {
    public List<ProducerIDNameDTO> getAllProducerIDName() {
        var list = handle.attach(ProducerDAO.class).getAll_id_name();
        return ProducerIDNameDTOMapper.INSTANCE.mapToDTO(handle, list);
    }
}
