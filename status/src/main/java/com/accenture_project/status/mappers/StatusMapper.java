package com.accenture_project.status.mappers;

import com.accenture_project.status.dtos.StatusDTO;
import com.accenture_project.status.models.StatusModel;
import org.springframework.stereotype.Component;

@Component
public class StatusMapper {
    public StatusModel toStatusModel(StatusModel statusModel, StatusDTO statusDTO) {
        statusModel.setWasPaid(statusDTO.wasPaid());
        statusModel.setLastUpdate(statusDTO.lastUpdate());

        return statusModel;
    }
}
