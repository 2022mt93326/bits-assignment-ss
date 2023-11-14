package com.bits.qms.client;

import com.bits.qms.models.ProductDto;
import com.bits.qms.models.QuotationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient("api-gateway")
public interface GatewayClient {
    @RequestMapping(method = RequestMethod.GET, value = "/product-service/products/{id}")
    Optional<ProductDto> getProductById(@PathVariable("id") String id, @RequestHeader("Authorization") String token);

    @RequestMapping(method = RequestMethod.POST, value = "/rule-engine/rules/{ruleCode}/execute")
    QuotationDto executeRule(@RequestBody QuotationDto quotationDto,
                             @PathVariable(name = "ruleCode") String ruleCode,
                             @RequestHeader("Authorization") String token);
}
