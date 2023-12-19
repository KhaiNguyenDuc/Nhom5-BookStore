package com.metis.book;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.metis.book.serviceImpl.BookRequestImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.metis.book.model.BookRequest;
import com.metis.book.repository.BookRequestRepository;

@ExtendWith(MockitoExtension.class)
class BookRequestImplTest {

    @Mock
    private BookRequestRepository bookRequestRepository;

    @InjectMocks
    private BookRequestImpl bookRequestService;

    @Test
    void testGetAllRequest() {
        // Mock data
        BookRequest request1 = new BookRequest();
        request1.setId(1L);
        request1.setName("Nguyen Van Thai");

        BookRequest request2 = new BookRequest();
        request2.setId(2L);
        request2.setName("Nguyen Tien Hung");

        when(bookRequestRepository.findAll()).thenReturn(Arrays.asList(request1, request2));

        // Perform the test
        List<BookRequest> result = bookRequestService.getAllRequest();
        assertEquals(2, result.size());
        assertEquals("Nguyen Van Thai", result.get(0).getName());
        assertEquals("Nguyen Tien Hung", result.get(1).getName());
    }

    @Test
    void testInsertBookRequest() {
        // Mock data
        BookRequest request = new BookRequest();
        request.setId(1L);
        request.setName("Nguyen Van Thai");

        // Perform the test
        bookRequestService.insertBookRequest(request);

        // Verify that the save method was called with the correct BookRequest
        verify(bookRequestRepository, times(1)).save(request);
    }

    @Test
    void testDeleteById() {
        // Mock data
        long bookRequestId = 1L;

        // Perform the test
        bookRequestService.deleteById(bookRequestId);

        // Verify that the deleteById method was called with the correct bookRequestId
        verify(bookRequestRepository, times(1)).deleteById(bookRequestId);
    }

    @Test
    void testGetById() {
        // Mock data
        long bookRequestId = 1L;
        BookRequest request = new BookRequest();
        request.setId(bookRequestId);
        request.setName("Nguyen Van Thai");

        when(bookRequestRepository.findById(bookRequestId)).thenReturn(Optional.of(request));

        // Perform the test
        BookRequest result = bookRequestService.getById(bookRequestId);
        assertNotNull(result);
        assertEquals("Nguyen Van Thai", result.getName());
    }

    @Test
    void testEditRequest() {
        // Mock data
        BookRequest bookRequest = new BookRequest();
        bookRequest.setId(1L);
        bookRequest.setName("Nguyen Van Thai");

        BookRequest existingRequest = new BookRequest();
        existingRequest.setId(1L);
        existingRequest.setName("Nguyen Tien Hung");

        when(bookRequestRepository.findById(1L)).thenReturn(Optional.of(existingRequest));

        // Perform the test
        bookRequestService.editRequest(bookRequest);

        // Verify that the save method was called with the correct BookRequest
        verify(bookRequestRepository, times(1)).save(existingRequest);
        assertEquals("Nguyen Van Thai", existingRequest.getName());
    }
}
