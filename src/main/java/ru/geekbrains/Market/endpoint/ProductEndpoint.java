package ru.geekbrains.Market.endpoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.Market.services.ProductService;
import ru.geekbrains.Market.soap.product.GetAllProductsRequest;
import ru.geekbrains.Market.soap.product.GetAllProductsResponse;

@Endpoint
@RequiredArgsConstructor
@Slf4j
public class ProductEndpoint {
    private final static String NAMESPACE_URI = "https://www.zaripov.com/ws/products";
    private final ProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProductsRequest(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.getAllProducts().forEach(response.getProducts()::add);
        return response;
    }
}
