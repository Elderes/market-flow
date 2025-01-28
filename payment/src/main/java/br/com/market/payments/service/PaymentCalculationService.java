package br.com.market.payments.service;

import br.com.market.payments.dto.ProductDTO;
import br.com.market.payments.dto.SendDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PaymentCalculationService {

    /**
     * Calcula o valor total com base na quantidade e preço dos produtos.
     *
     * @param products A lista de objetos {@link ProductDTO} contendo os preços e quantidades dos produtos.
     * @return O valor total calculado como {@link BigDecimal}.
     */
    public BigDecimal calculateTotal(List<ProductDTO> products) {
        BigDecimal total = BigDecimal.ZERO;

        for (ProductDTO product : products) {
            if (product.unitPrice() != null && product.quantity() > 0) {
                BigDecimal productTotal = product.unitPrice().multiply(BigDecimal.valueOf(product.quantity()));
                total = total.add(productTotal);
            }
        }
        System.out.println("Calculo feito");
        return total;
    }




    /**
     * Envia um objeto com os dados informados.
     *
     * @param value    Valor total a ser enviado.
     * @param orderId  ID do pedido relacionado.
     * @return Um objeto contendo os dados a serem enviados.
     */
    public SendDTO send(BigDecimal value, UUID orderId) {
        if (value == null || orderId == null) {
            throw new IllegalArgumentException("Valor e Order ID devem ser informados.");
        }

        SendDTO sendDTO = new SendDTO();
        sendDTO.setId(UUID.randomUUID());
        sendDTO.setValue(value);
        sendDTO.setTimestamp(LocalDateTime.now());
        sendDTO.setOrderId(orderId);

        // Aqui você pode realizar alguma lógica de envio, como salvar no banco ou enviar para outro serviço.
        // Exemplo: mensagem em fila ou API externa.

        return sendDTO; // Retornando para validação ou logging.
    }
}
