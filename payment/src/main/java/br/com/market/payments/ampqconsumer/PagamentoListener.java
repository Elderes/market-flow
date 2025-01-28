//package br.com.market.payments.ampqconsumer;
//
//import br.com.market.payments.dto.EstoqueDTO;
//import br.com.market.payments.dto.PagamentoDto;
//import br.com.market.payments.dto.PedidoDTO;
//import br.com.market.payments.model.Estoque;
//import br.com.market.payments.model.Pagamento;
//import br.com.market.payments.repository.EstoqueRepository;
//import br.com.market.payments.repository.PagamentoRepository;
//import br.com.market.payments.repository.PedidoRepository;
//import br.com.market.payments.service.PagamentoService;
//import br.com.market.payments.service.PedidoService;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class PagamentoListener {

//    private final PedidoRepository pedidoRepository;
//    private final EstoqueRepository estoqueRepository;
//    private final PagamentoRepository pagamentoRepository;
//    private final PedidoService pedidoService;
//
//
//    public PagamentoListener(PedidoRepository pedidoRepository, EstoqueRepository estoqueRepository, PagamentoRepository pagamentoRepository
//                             ) {
//        this.pedidoRepository = pedidoRepository;
//        this.estoqueRepository = estoqueRepository;
//        this.pagamentoRepository = pagamentoRepository;
//        this.pedidoService = new PedidoService(new RabbitTemplate());
//    }
//
//    @RabbitListener(queues = "queue.payment.order")
//    public void recebePedido(PedidoDTO pedidoDTO) {
//        try {
//            System.out.println("===== Pedido Recebido =====");
//            System.out.println("ID do Pedido: " + pedidoDTO.getPedidoId());
//            System.out.println("Nome do Pedido: " + pedidoDTO.getClient());
//            System.out.println("Quantidade Total: " + pedidoDTO.getProducts());
//
//            // Verifique se o cliente é nulo
//            if (pedidoDTO.getClient() == null) {
//                System.err.println("Cliente não encontrado no pedido.");
//                return; // Interrompe o processamento se não houver cliente
//            }
//
//            // Processando os dados do cliente
//            PedidoDTO.ClientDTO client = pedidoDTO.getClient();
//            System.out.println("Cliente:");
//            // Verifique se o nome do cliente é nulo antes de imprimir
//            if (client.getName() != null) {
//                System.out.println("  Nome: " + client.getName());
//            } else {
//                System.out.println("  Nome: Não informado");
//            }
//
//            System.out.println("  Celular: " + (client.getCellphone() != null ? client.getCellphone() : "Não informado"));
//            System.out.println("  Email: " + (client.getEmail() != null ? client.getEmail() : "Não informado"));
//
//            // Processando o endereço do cliente
//            PedidoDTO.AddressDTO address = client.getAddress();
//            if (address != null) {
//                System.out.println("Endereço do Cliente:");
//                System.out.println("  Rua: " + address.getStreet() + ", Número: " + address.getNumber());
//                System.out.println("  Bairro: " + address.getNeighborhood());
//                System.out.println("  Cidade: " + address.getCity() + ", Estado: " + address.getState() + ", País: " + address.getCountry());
//            } else {
//                System.out.println("Endereço não informado.");
//            }
//
//            // Processando os produtos
//            System.out.println("Produtos:");
//            for (PedidoDTO.ProductDTO product : pedidoDTO.getProducts()) {
//                System.out.println("  - Produto: " + product.getName() + ", Quantidade: " + product.getQuantity());
//            }
//
//            System.out.println("===========================");
//
//            pedidoService.adicionaPedido(pedidoDTO);
//
//        } catch (Exception e) {
//            System.err.println("Erro ao processar o pedido: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//
//    @RabbitListener(queues = "queue.payment.stock")
//    public void recebeEstoque(EstoqueDTO estoqueDTO) {
//        System.out.println("Mensagem recebida na fila 'queue.payment.stock':");
//        System.out.println("EstoqueDTO recebido: " + estoqueDTO);
//
//        Estoque estoque = new Estoque();
//        estoque.setProdutoId(estoqueDTO.getOrder_Id());
//        estoque.setClienteID(estoqueDTO.getClient_ID());
//        estoque.setProdutosDisponiveis(estoqueDTO.isApproval());
//        estoque.setValorTotal(estoqueDTO.getTotalValue());
//
//        System.out.println("Dados convertidos para entidade Estoque:");
//        System.out.println("Produto ID: " + estoque.getProdutoId());
//        System.out.println("Cliente ID: " + estoque.getClienteID());
//        System.out.println("Produtos Disponíveis: " + estoque.isProdutosDisponiveis());
//        System.out.println("Valor Total: " + estoque.getValorTotal());
//
//        System.out.println("Estoque salvo no banco e recebido: " + estoque);
//    }
//
//    @RabbitListener(queues = "queue.payment.order.pay")
//    public void recebePagamento(PagamentoDto pagamentoDTO) {
//        if (pagamentoDTO == null) {
//            System.err.println("PagamentoDTO está nulo. Verifique o formato da mensagem enviada para a fila.");
//            return;
//        }
//
//        pedidoService.validarCompra(pagamentoDTO);
//
//        try {
//            // Converter DTO para Entidade
//            Pagamento pagamento = new Pagamento();
//            pagamento.setId(pagamentoDTO.getId());
//            pagamento.setCode(pagamentoDTO.getCode());
//            pagamento.setValue(pagamentoDTO.getValue());
//            pagamento.setDateTime(pagamentoDTO.getDateTime());
//
//            System.out.println(" ");
//            System.out.println("Testando valor do pagamento: " + pagamento.getValue());
//
//            // Salvar no banco
//            pagamentoRepository.save(pagamento);
//            System.out.println("Pagamento salvo no banco e recebido: " + pagamento);
//
//        } catch (Exception e) {
//            System.err.println("Erro ao processar o pagamento: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}
