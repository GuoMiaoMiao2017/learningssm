package com.guomiaomiao.learningssm.service.impl;

import com.guomiaomiao.learningssm.common.ServerResponse;
import com.guomiaomiao.learningssm.dao.ProductMapper;
import com.guomiaomiao.learningssm.pojo.Product;
import com.guomiaomiao.learningssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15295 on 2018/6/2.
 */
@Service("iProductService")
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    public List<Product> getProductList() {
        return productMapper.selectList();
    }
    public ServerResponse add(Product product) {
        if (product != null) {
            int rowCount = productMapper.insert(product);
            if (rowCount > 0)
                return ServerResponse.createBySuccess("新增产品成功");
            return ServerResponse.createByErrorMessage("新增产品失败");
        }
        return ServerResponse.createByErrorMessage("新增产品为空");
    }

    public ServerResponse delete(Integer id) {
        if (id != null) {
            int rowCount = productMapper.deleteByPrimaryKey(id);
            if (rowCount > 0)
                return ServerResponse.createBySuccess("删除产品成功");
            return ServerResponse.createByErrorMessage("删除产品失败");
        }
        return ServerResponse.createByErrorMessage("要删除产品为空");
    }

    public Product get(Integer id) {
        if (id != null) {
            Product product = productMapper.selectByPrimaryKey(id);
            if (product != null)
                return product;
        }
        return null;
    }

    public ServerResponse update(Product product) {
        if (product != null) {
            int rowCount = productMapper.updateByPrimaryKey(product);
            if (rowCount > 0)
                return ServerResponse.createBySuccess("更新产品成功");
            return ServerResponse.createByErrorMessage("更新产品失败");
        }
        return ServerResponse.createByErrorMessage("要更新的产品为空");
    }
}
