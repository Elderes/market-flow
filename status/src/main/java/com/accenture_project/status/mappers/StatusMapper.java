package com.accenture_project.status.mappers;

import com.accenture_project.status.dtos.StatusDTO;
import com.accenture_project.status.models.StatusModel;
import org.springframework.stereotype.Component;

/**
 * Mapper class that converts a StatusModel to a StatusDTO.
 * It is used to transform the model data into a data transfer object for easier handling in other layers.
 */

@Component
public class StatusMapper {
    public StatusDTO toStatusModel(StatusModel statusModel) {
        return new StatusDTO(statusModel.getEmailClient(), statusModel.getOrderId());
    }
}