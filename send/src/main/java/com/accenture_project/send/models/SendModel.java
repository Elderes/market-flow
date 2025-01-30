package com.accenture_project.send.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/*
 * SendModel Class
 *
 * Represents the entity for the "Send" table in the database.
 * Stores information related to the sending status, including the orderId, email, and a flag indicating if the order has been sent.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_send")
public class SendModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private boolean hasSend;

    private UUID orderId;

    private String email;
}
