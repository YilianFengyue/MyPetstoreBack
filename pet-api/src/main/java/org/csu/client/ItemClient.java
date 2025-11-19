package org.csu.client;

import org.csu.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("product-service")
public interface ItemClient {
    @GetMapping("/item/{id}")
    public Result getItemById(@PathVariable("id") String id);
}
