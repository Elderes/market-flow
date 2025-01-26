package br.com.market.payments.ampqconsumer;

import br.com.market.payments.dto.EstoqueDTO;
import br.com.market.payments.dto.PagamentoDto;
import br.com.market.payments.dto.PedidoDTO;
import br.com.market.payments.model.Estoque;
import br.com.market.payments.model.Pagamento;
import br.com.market.payments.model.Pedido;
import br.com.market.payments.repository.EstoqueRepository;
import br.com.market.payments.repository.PagamentoRepository;
import br.com.market.payments.repository.PedidoRepository;
import br.com.market.payments.service.PedidoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PagamentoListener {

    private final PedidoRepository pedidoRepository;
    private final EstoqueRepository estoqueRepository;
    private final PagamentoRepository pagamentoRepository;

    private final PedidoService pedidoService;

    // Injeção dos repositórios via construtor
    public PagamentoListener(PedidoRepository pedidoRepository, EstoqueRepository estoqueRepository, PagamentoRepository pagamentoRepository,
                             PedidoService pedidoService) {
        this.pedidoRepository = pedidoRepository;
        this.estoqueRepository = estoqueRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.pedidoService = pedidoService;
    }

    @RabbitListener(queues = "queue.payment.order")
    public void recebePedido(PedidoDTO pedidoDTO) {
        try {
            System.out.println("===== Pedido Recebido =====");
            System.out.println("ID do Pedido: " + pedidoDTO.getPedidoId());
            System.out.println("Nome do Pedido: " + pedidoDTO.getClient());
            System.out.println("Quantidade Total: " + pedidoDTO.getProducts());

            // Processando os dados do cliente
            PedidoDTO.ClientDTO client = pedidoDTO.getClient();
            System.out.println("Cliente:");
            System.out.println("  Nome: " + client.getName());
            System.out.println("  Celular: " + client.getCellphone());
            System.out.println("  Email: " + client.getEmail());

            // Processando o endereço do cliente
            PedidoDTO.AddressDTO address = client.getAddress();
            System.out.println("Endereço do Cliente:");
            System.out.println("  Rua: " + address.getStreet() + ", Número: " + address.getNumber());
            System.out.println("  Bairro: " + address.getNeighborhood());
            System.out.println("  Cidade: " + address.getCity() + ", Estado: " + address.getState() + ", País: " + address.getCountry());


            // Processando os produtos
            System.out.println("Produtos:");
            for (PedidoDTO.ProductDTO product : pedidoDTO.getProducts()) {
                System.out.println("  - Produto: " + product.getName() + ", Quantidade: " + product.getQuantity());
            }

            System.out.println("===========================");

        } catch (Exception e) {
            System.err.println("Erro ao processar o pedido: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @RabbitListener(queues = "queue.payment.stock")
    public void recebeEstoque(EstoqueDTO estoqueDTO) {
        // Exibir no terminal os dados recebidos
        System.out.println("Mensagem recebida na fila 'queue.payment.stock':");
        System.out.println("EstoqueDTO recebido: " + estoqueDTO);

        // Converter DTO para Entidade
        Estoque estoque = new Estoque();
        estoque.setProdutoId(estoqueDTO.getOrder_Id());
        estoque.setClienteID(estoqueDTO.getClient_ID());
        estoque.setProdutosDisponiveis(estoqueDTO.isApproval());
        estoque.setValorTotal(estoqueDTO.getTotalValue());

        // Exibir no terminal os dados convertidos para entidade
        System.out.println("Dados convertidos para entidade Estoque:");
        System.out.println("Produto ID: " + estoque.getProdutoId());
        System.out.println("Cliente ID: " + estoque.getClienteID());
        System.out.println("Produtos Disponíveis: " + estoque.isProdutosDisponiveis());
        System.out.println("Valor Total: " + estoque.getValorTotal());

        // Salvar no banco
        // estoqueRepository.save(estoque);

        System.out.println("Estoque salvo no banco e recebido: " + estoque);
    }


    @RabbitListener(queues = "queue.payment.order.pay")
    public void recebePagamento(PagamentoDto pagamentoDTO, PedidoDTO pedidoDTO) {
        // Converter DTO para Entidade
        Pagamento pagamento = new Pagamento();
        pagamento.setId(pagamentoDTO.getId());
        pagamento.setCode(pagamentoDTO.getCode());
        pagamento.setValue(pagamentoDTO.getValue());
        pagamento.setDateTime(pagamentoDTO.getDateTime());

        System.out.println(" ");
        System.out.println("Testando valor do pagamento: " + pagamento.getValue());

        pedidoService.validarCompra(pedidoDTO,pagamentoDTO);

        // Salvar no banco
        pagamentoRepository.save(pagamento);
        System.out.println("Pagamento salvo no banco e recebido de lucas: " + pagamento);



    }
}
