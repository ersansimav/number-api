package com.ersan.assignment.number.service;

import com.ersan.assignment.number.model.ResponseMessage;
import com.ersan.assignment.number.model.Number;

import java.util.List;

public interface NumberService {

    public ResponseMessage createNumber(Integer numberVal);

    public Number findByNumber(Integer numberVal);

    public Number findMaxNumber();

    public Number findMinNumber();

    public ResponseMessage deleteNumber(Integer numberVal);

    public List<Number> findAllNumbers(String orderingType);
}
