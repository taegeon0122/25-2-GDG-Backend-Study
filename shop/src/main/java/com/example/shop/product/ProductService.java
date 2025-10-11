package com.example.shop.product;

import com.example.shop.member.Member;
import com.example.shop.member.MemberRepository;
import com.example.shop.member.dto.MemberCreateRequest;
import com.example.shop.member.dto.MemberUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Long createProduct(ProductCreateRequest request) {
//        getLoginId 대신 getProductId 사용
        Product existingProduct = productRepository.findByProductId(request.getProductId());
        if (existingProduct != null) {
            throw new RuntimeException("Product already exists" + request.getProductId());
        }

        Product product = new Product(
                request.getProductId(),
                request.getName(),
                request.getNum()
        );

        productRepository.save(product);
        return product.getId();
    }

    // get은 조회이므로 정보 상의 수정을 막기 위해 "읽기 전용으로 설정"
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id);
        // 분기 처리
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        return product;
    }

    // @Transactional
    public void updateProduct(Long id, ProductUpdateRequest request) {
        Product product = productRepository.findById(id);

        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        // 회원 정보 수정(도메인 객체의 메서드 사용)
        product.updateInfo(request.getName(), request.getNum());
    }

    // @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id);

        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        // 회원 정보 삭제
        productRepository.deleteById(id);
    }
}
