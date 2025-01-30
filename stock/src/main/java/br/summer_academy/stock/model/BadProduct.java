package br.summer_academy.stock.model;

// import org.hibernate.annotations.JdbcTypeCode;
// import org.hibernate.type.SqlTypes;

// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
// @Entity
// @Table(name = "tb_product")
public class BadProduct {
    private String name;
    private Integer quantity;
}
