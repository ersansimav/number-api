package com.ersan.assignment.number.service;

import com.ersan.assignment.number.model.ResponseMessage;
import com.ersan.assignment.number.model.Number;
import com.ersan.assignment.number.repository.NumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NumberServiceImpl implements NumberService {

    public final String SUCCESS_CODE = "0";
    public final String SUCCESS_DESC = "Success";
    public final String NUMBER_ALREADY_EXIST_CODE = "1001";
    public final String NUMBER_ALREADY_EXIST_DESC = "Number is already exists!";
    public final String NUMBER_NOT_EXIST_CODE = "1002";
    public final String NUMBER_NOT_EXIST_DESC = "Number does not exist!";

    @Autowired
    private NumberRepository numberRepository;

    public ResponseMessage createNumber(Integer numberVal) {
        Number recordedNumber = numberRepository.findByNumber(numberVal);

        if (recordedNumber != null) {
            return new ResponseMessage(NUMBER_ALREADY_EXIST_CODE, NUMBER_ALREADY_EXIST_DESC);
        } else {
            Number number = new Number();
            number.setNumber(numberVal);
            number.setCreateDate(new Date());
            numberRepository.save(number);

            return new ResponseMessage(SUCCESS_CODE, SUCCESS_DESC);
        }
    }

    public Number findByNumber(Integer numberVal) {
        return numberRepository.findByNumber(numberVal);
    }

    public Number findMaxNumber() {
        Optional<Number> optionalNumber = numberRepository.findAll().stream().max(Comparator.comparingInt(Number::getNumber));
        if (optionalNumber.isPresent()) {
            return optionalNumber.get();
        }
        return null;
    }

    public Number findMinNumber() {
        Optional<Number> optionalNumber = numberRepository.findAll().stream().min(Comparator.comparingInt(Number::getNumber));
        if (optionalNumber.isPresent()) {
            return optionalNumber.get();
        }
        return null;
    }

    public ResponseMessage deleteNumber(Integer numberVal) {
        Number recordedNumber = numberRepository.findByNumber(numberVal);

        if (recordedNumber == null) {
            return new ResponseMessage(NUMBER_NOT_EXIST_CODE, NUMBER_NOT_EXIST_DESC);
        } else {
            numberRepository.delete(recordedNumber);
            return new ResponseMessage(SUCCESS_CODE, SUCCESS_DESC);
        }
    }

    public List<Number> findAllNumbers(String orderingType) {

        Comparator<Number> numberComparator = Comparator.comparingInt(Number::getNumber);
        if ("descending".equals(orderingType)) {
            numberComparator = numberComparator.reversed();
        }
        List<Number> numbers = numberRepository.findAll().stream().collect(Collectors.toList());
        numbers.sort(numberComparator);
        return numbers;
    }
}
