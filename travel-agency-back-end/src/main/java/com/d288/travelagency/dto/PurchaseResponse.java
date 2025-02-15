package com.d288.travelagency.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


// After purchase request is processed, this response is generated
@Data
public class PurchaseResponse {

    private final String orderTrackingNumber;

}
