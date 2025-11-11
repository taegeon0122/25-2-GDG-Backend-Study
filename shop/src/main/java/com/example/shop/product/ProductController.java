package com.example.shop.product;

import com.example.shop.product.dto.ProductCreateRequest;
import com.example.shop.product.dto.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
// @Controller, @ResponseBody 두 개를 묶음

@RequiredArgsConstructor
// 모든 필드값을 파라미터로 받는 생성자 생성

@RequestMapping("/products")

public class ProductController {
    private final ProductService productService;
    //    회원 등록
    @PostMapping//("/members") // endpoint 명시 > @RequestMapping("/members") 애노테이션으로 생략 가능
    public ResponseEntity<Void> createProduct(@RequestBody ProductCreateRequest request) {
        Long productId = productService.createProduct(request);
        return ResponseEntity.created(URI.create("/products/" + productId)).build();
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct() {
//        Service 계층에서 회원 목록을 가져온다.
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);  // ok : 바디 안에 members를 넣겠다.
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getMember(@PathVariable("productId") Long productId) { // @PathVariable : 위의 {memberId}를 가져오겠다
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(
            @PathVariable Long productId,
            ProductUpdateRequest request) {
        productService.updateProduct(productId, request);
        return ResponseEntity.ok().build();
//        200 ok 형태로 return 하고, body가 비어있기 때문에 <Void> 형태임.
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build(); // 284 no content
    }
}
