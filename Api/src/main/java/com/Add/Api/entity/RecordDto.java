package com.Add.Api.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RecordDto {

    private int firstNumber;

    private int secondNumber;

    private int result;

    public int getFirstNumber(){
        return firstNumber;
    }

    public int getSecondNumber(){
        return secondNumber;
    }

    public int getResult(){
        return result;
    }
}
