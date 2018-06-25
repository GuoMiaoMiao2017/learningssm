package com.guomiaomiao.learningssm.dao;

import com.guomiaomiao.learningssm.pojo.Product;
import com.guomiaomiao.learningssm.vo.ProductPriceVo;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectList();

    List<Product> getProductListByPrice(ProductPriceVo productPriceVo);
}