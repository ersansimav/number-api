package com.ersan.assignment.number;

import com.ersan.assignment.number.model.Number;
import com.ersan.assignment.number.model.ResponseMessage;
import com.ersan.assignment.number.repository.NumberRepository;
import com.ersan.assignment.number.service.NumberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class NumberServiceTests {

    @Mock
    private NumberRepository repository;

    @InjectMocks
    private NumberServiceImpl numberService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createNumber() {
        ResponseMessage result = numberService.createNumber(99);
        assertEquals(result.getResponseCode(), numberService.SUCCESS_CODE);
    }

    @Test
    public void createExistingNumber() {
        Number number = new Number(1, new Date());
        when(repository.findByNumber(1)).thenReturn(number);

        ResponseMessage result = numberService.createNumber(1);
        assertEquals(result.getResponseCode(), numberService.NUMBER_ALREADY_EXIST_CODE);
    }

    @Test
    public void findByNumber() {
        Number number = new Number(1, new Date());
        when(repository.findByNumber(1)).thenReturn(number);

        Number result = numberService.findByNumber(1);
        assertEquals(result.getNumber(), new Integer(1));
    }

    @Test
    public void findAllNumbers() {
        List<Number> numbers = new ArrayList<Number>();
        numbers.add(new Number(3, new Date()));
        numbers.add(new Number(5, new Date()));
        numbers.add(new Number(7, new Date()));
        numbers.add(new Number(10, new Date()));
        when(repository.findAll()).thenReturn(numbers);

        List<Number> result = numberService.findAllNumbers("ascending");
        assertEquals(4, result.size());
    }

    @Test
    public void findMaxNumber() {
        List<Number> numbers = new ArrayList<Number>();
        numbers.add(new Number(5, new Date()));
        numbers.add(new Number(10, new Date()));
        numbers.add(new Number(15, new Date()));
        when(repository.findAll()).thenReturn(numbers);

        Number result = numberService.findMaxNumber();
        assertEquals(15, result.getNumber());
    }

    @Test
    public void findMinNumber() {
        List<Number> numbers = new ArrayList<Number>();
        numbers.add(new Number(5, new Date()));
        numbers.add(new Number(10, new Date()));
        numbers.add(new Number(15, new Date()));
        when(repository.findAll()).thenReturn(numbers);

        Number result = numberService.findMinNumber();
        assertEquals(5, result.getNumber());
    }

    @Test
    public void deleteNumber() {
        Number number = new Number(10, new Date());
        when(repository.findByNumber(10)).thenReturn(number);
        doNothing().when(repository).delete(number);

        ResponseMessage result = numberService.deleteNumber(10);
        assertEquals(result.getResponseCode(), numberService.SUCCESS_CODE);
    }

    @Test
    public void deleteNotExistingNumber() {
        ResponseMessage result = numberService.deleteNumber(20);
        assertEquals(result.getResponseCode(), numberService.NUMBER_NOT_EXIST_CODE);
    }

}

