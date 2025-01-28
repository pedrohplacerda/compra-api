package com.interview.purchase_api.service;

import com.interview.purchase_api.dto.ProductDTO;
import com.interview.purchase_api.dto.UserDTO;
import com.interview.purchase_api.entities.Purchase;
import com.interview.purchase_api.exception.NotEnoughBalanceException;
import com.interview.purchase_api.repository.BuyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.interview.purchase_api.TestConstants.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class BuyServiceTest {
    @InjectMocks
    private BuyService unit;
    @Mock
    private ProductService productService;
    @Mock
    private UserService userService;
    @Mock
    private BuyUserService buyUserService;
    @Mock
    private BuyRepository buyRepository;

    @Test
    @DisplayName("Tests if the result is equal to the remaining balance of user.")
    void buyProduct_testCase1() throws URISyntaxException, IOException, InterruptedException {
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2025, 1, 28), LocalTime.of(10, 30));
        try (MockedStatic<LocalDateTime> localDateTimeMocked = Mockito.mockStatic(LocalDateTime.class)) {
            localDateTimeMocked.when(LocalDateTime::now).thenReturn(dateTime);
            Mockito.when(productService.searchProduct(PRODUCT_ID)).thenReturn(new ProductDTO(PRODUCT_ID, PRODUCT_NAME, PRODUCT_VALUE, QTY));
            Mockito.when(userService.getUser(USER_ID)).thenReturn(new UserDTO(USER_ID, USER_NAME, EMAIL_COM, DOCUMENT, BALANCE, null, null));
            Purchase purchase = new Purchase();
            purchase.setPago(Boolean.TRUE);
            purchase.setData(dateTime);
            Mockito.when(buyRepository.save(any())).thenReturn(purchase);
            Mockito.doNothing().when(buyUserService).save(any());

            Double expected = 4200.0;

            Double actual = unit.buyProduct(PRODUCT_ID, USER_ID, QUANTITY);

            Assertions.assertEquals(expected, actual);
        }
    }

    @Test
    @DisplayName("Tests if the exception of type NotEnoughBalanceException is thrown when user has not enough balance to buy.")
    void buyProduct_testCase2() throws URISyntaxException, IOException, InterruptedException {
        Mockito.when(productService.searchProduct(PRODUCT_ID)).thenReturn(new ProductDTO(PRODUCT_ID, PRODUCT_NAME, PRODUCT_VALUE, QTY));
        Mockito.when(userService.getUser(USER_ID)).thenReturn(new UserDTO(USER_ID, USER_NAME, EMAIL_COM, DOCUMENT, 40.0, null, null));

        assertThatExceptionOfType(NotEnoughBalanceException.class)
                .isThrownBy(() -> unit.buyProduct(PRODUCT_ID, USER_ID, QUANTITY));

    }
}