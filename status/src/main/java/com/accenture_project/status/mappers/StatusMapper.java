package com.accenture_project.status.mappers;

import com.accenture_project.status.dtos.StatusDTO;
import com.accenture_project.status.models.StatusModel;
import org.springframework.stereotype.Component;

@Component
public class StatusMapper {
    public StatusDTO toStatusModel(StatusModel statusModel) {
        return new StatusDTO(statusModel.getEmailClient(), statusModel.getNameClient());
    }
}