package com.metis.book;

import com.metis.book.model.Book;
import com.metis.book.model.Cart;
import com.metis.book.model.CartItem;
import com.metis.book.model.user.User;
import com.metis.book.repository.BookRepository;
import com.metis.book.repository.CartRepository;
import com.metis.book.repository.CartItemReposirory;
import com.metis.book.repository.UserRepository;
import com.metis.book.serviceImpl.CartServiceImpl;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;
import org.junit.Before;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartItemReposirory cartItemRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    private User user;
    private Cart cart;
    private Book book;
    private CartItem cartItem;

    @Before
    public void setup() {
        user = new User(); // Initialize User object as needed
        cart = new Cart(); // Initialize Cart object as needed
        book = new Book(); // Initialize Book object as needed
        cartItem = new CartItem(); // Initialize CartItem object as needed
    }

    @Test
    public void testAddToCart() {
        // Mocking behavior for cartRepository.findByUser
        when(cartRepository.findByUser(user)).thenReturn(cart);

        // Mocking behavior for cart.getCartItems
        when(cart.getCartItems()).thenReturn(new ArrayList<>());

        // Mocking behavior for bookRepository.findById
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));

        cartService.addToCart(user, 1L, 2); // Call the method

        // Verify that the save method was called on cartItemRepository
        verify(cartItemRepository, times(1)).save(any(CartItem.class));
    }

    @Test
    public void testGetCartByUser() {
        // Mocking behavior for userRepository.findById
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        // Mocking behavior for cartRepository.findByUser
        when(cartRepository.findByUser(user)).thenReturn(cart);

        Cart retrievedCart = cartService.getCartByUser(1L); // Call the method

        // Verify that the correct user ID was used in the userRepository.findById
        // method
        verify(userRepository, times(1)).findById(1L);

        // Verify that the cart returned matches the mocked cart from
        // cartRepository.findByUser
        assertEquals(cart, retrievedCart);
    }

}
