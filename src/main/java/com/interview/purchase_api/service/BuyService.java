package com.interview.purchase_api.service;

import com.interview.purchase_api.dto.ProductDTO;
import com.interview.purchase_api.dto.PurchaseDTO;
import com.interview.purchase_api.dto.UserDTO;
import com.interview.purchase_api.dto.UserPurchaseDTO;
import com.interview.purchase_api.entities.Purchase;
import com.interview.purchase_api.entities.UserPurchaseId;
import com.interview.purchase_api.exception.NotEnoughBalanceException;
import com.interview.purchase_api.mapper.PurchaseMapper;
import com.interview.purchase_api.repository.BuyRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@Service
public class BuyService {

    private final ProductService productService;
    private final UserService userService;
    private final BuyUserService buyUserService;
    private final BuyRepository buyRepository;


    public BuyService(ProductService productService, UserService userService, BuyUserService compraUsuarioService, BuyRepository buyRepository) {
        this.productService = productService;
        this.userService = userService;
        this.buyUserService = compraUsuarioService;
        this.buyRepository = buyRepository;
    }

    public Double buyProduct(Integer productId, Integer userId, Integer qty) throws URISyntaxException, IOException, InterruptedException {
        LocalDateTime data = LocalDateTime.now();
        ProductDTO productDTO = productService.searchProduct(productId);
        UserDTO userDTO = userService.getUser(userId);
        Integer valorFinal = Math.multiplyExact(productDTO.getValor(), qty);
        if (userDTO.getSaldo() < valorFinal) {
            throw new NotEnoughBalanceException();
        }
        Double saldoAtualizado = userDTO.getSaldo() - valorFinal;
        userService.updateUser(userId, saldoAtualizado);
        Purchase savedPurchase = buyRepository.save(PurchaseMapper.INSTANCE.toCompra(getCompraDTO(data)));
        UserPurchaseDTO compraUsuario = getCompraUsuarioDTO(getCompraUsuarioId(userId, savedPurchase), valorFinal, data);
        buyUserService.save(compraUsuario);
        return saldoAtualizado;
    }

    private UserPurchaseDTO getCompraUsuarioDTO(UserPurchaseId userPurchaseId, Integer valorFinal, LocalDateTime data) {
        UserPurchaseDTO userPurchaseDTO = new UserPurchaseDTO();
        userPurchaseDTO.setId(userPurchaseId);
        userPurchaseDTO.setValor(BigDecimal.valueOf(valorFinal));
        userPurchaseDTO.setData(data);
        userPurchaseDTO.setPago(Boolean.TRUE);
        return userPurchaseDTO;
    }

    private UserPurchaseId getCompraUsuarioId(Integer idUsuario, Purchase savedPurchase) {
        UserPurchaseId userPurchaseId = new UserPurchaseId();
        userPurchaseId.setIdUsuario(idUsuario);
        userPurchaseId.setIdCompra(savedPurchase.getId());
        return userPurchaseId;
    }

    private PurchaseDTO getCompraDTO(LocalDateTime data) {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setData(data);
        purchaseDTO.setPago(Boolean.TRUE);
        return purchaseDTO;
    }
}
