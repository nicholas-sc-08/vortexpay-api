package com.pay.vortexpay.dtos.request;

import java.util.UUID;

public record CustomerUpdateDTO(
    String fullName,
    String document,
    String phoneNumber,
    UUID userId
) {}
