package br.com.market.payments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
public class PedidoDTO {

    @JsonProperty("id")  // Mapeia o campo "id" do JSON para "pedidoId"
    private String pedidoId;

    private List<ProductDTO> products;  // Não tem nome e quantidade no pedido, mas nos produtos

    private ClientDTO client;


    private BigDecimal totalCompra;  // Total da compra
    private double valorPago;    // Valor pago pelo cliente

    @Getter
    @Setter
    public static class ClientDTO {
        private String name;
        private String cellphone;
        private String email;
        private AddressDTO address;


    }

    @Getter
    @Setter
    public static class AddressDTO {
        private String country;
        private String state;
        private String city;
        private String neighborhood;
        private String street;
        private int number;
    }

    @Getter
    @Setter
    public static class ProductDTO {
        private String name;    // Nome do produto
        private int quantity;   // Quantidade do produto
        private BigDecimal price;       // Preço unitário do produto

        public BigDecimal getTotalPrice() {
            return price.multiply(BigDecimal.valueOf(quantity));
        }
    }
}
