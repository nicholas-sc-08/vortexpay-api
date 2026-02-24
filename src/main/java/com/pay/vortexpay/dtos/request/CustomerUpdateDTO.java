package com.pay.vortexpay.dtos.request;

public record CustomerUpdateDTO(
    String fullName,
    String document,
    String phoneNumber
) {}
