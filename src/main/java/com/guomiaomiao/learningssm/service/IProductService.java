package com.guomiaomiao.learningssm.service;

import com.guomiaomiao.learningssm.common.ServerResponse;
import com.guomiaomiao.learningssm.pojo.Product;
import com.guomiaomiao.learningssm.vo.ProductPriceVo;

import java.util.List;

/**
 * Created by 15295 on 2018/6/2.
 */
public interface IProductService {
    List<Product> getProductList();
    ServerResponse add(Product product);
    ServerResponse delete(Integer id);
    Product get(Integer id);
    ServerResponse update(Product product);
    List<Product> getProductListByPrice(ProductPriceVo productPriceVo);
}
