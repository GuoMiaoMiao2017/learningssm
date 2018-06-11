package com.guomiaomiao.learningssm.controller.backend;

import com.guomiaomiao.learningssm.common.Const;
import com.guomiaomiao.learningssm.common.ResponseCode;
import com.guomiaomiao.learningssm.common.ServerResponse;
import com.guomiaomiao.learningssm.pojo.Product;
import com.guomiaomiao.learningssm.pojo.User;
import com.guomiaomiao.learningssm.service.IProductService;
import com.guomiaomiao.learningssm.service.IUserService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 15295 on 2018/6/1.
 */

@Controller
@RequestMapping("/manage/product/")
public class ProductManageController {
    @Autowired
    private IProductService iProductService;
    @Autowired
    private IUserService iUserService;

    @RequestMapping("list")
    @ResponseBody
    public List<Product> list(HttpSession session) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
            System.out.println("请输入账号与密码");
            return null;
        } else {
            List<Product> list = iProductService.getProductList();
            createExcel(list);
            List<Product> listProduct = readExcel();
            System.out.println(listProduct.toString());
            return list;
        }
    }


    @RequestMapping("add")
    @ResponseBody
    public void add(HttpSession session, Product product) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
            System.out.println("请输入账号与密码");
        }
        if (iUserService.checkAdminRole(user).isSuccess())
            iProductService.add(product);
        else
            System.out.println("无权限操作");
    }

    @RequestMapping("update")
    @ResponseBody
    public void update(HttpSession session, Product product) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
            System.out.println("请输入账号与密码");
        }
        if (iUserService.checkAdminRole(user).isSuccess())
            iProductService.update(product);
        else
            System.out.println("无权限操作");
    }
    @RequestMapping("delete")
    @ResponseBody
    public ServerResponse delete(HttpSession session, Integer id) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            return iProductService.delete(id);
        }
        else {
            System.out.println("--------------------");
            return ServerResponse.createByErrorMessage("无权限操作");
        }

    }

    @RequestMapping("get")
    @ResponseBody
    public Product get(HttpSession session, Integer id) {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
            System.out.println("请输入账号与密码");
        }
        if (iUserService.checkAdminRole(user).isSuccess())
            return iProductService.get(id);
        else {
            System.out.println("无权限操作");
            return null;
        }
    }

    @RequestMapping("fileupload")
    @ResponseBody
    public ModelAndView fileUpload(@RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("/web-resources/upload");
        File filenew = new File(path);
        if (!filenew.exists()) {
            System.out.println(path+"目录不存在，需要创建");
            filenew.mkdir();
        }
//        MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest)request;
//        MultipartFile file = mhsr.getFile("upload_file");
        ModelAndView mv = new ModelAndView();
        mv.setView(new MappingJackson2JsonView());
        String fileName = file.getOriginalFilename();
        File dest = new File(path,fileName);
        try {
            file.transferTo(dest);
            mv.addObject("success", true);
            mv.addObject("msg","上传文件成功");
        } catch (IllegalStateException | IOException e) {
            mv.addObject("success", false);
            mv.addObject("msg","上传文件失败");
            e.printStackTrace();
        }
        return mv;
    }




    //excel文件导入导出
    private void createExcel (List<Product> list) {
        // 创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作表
        HSSFSheet sheet = workbook.createSheet("产品表一");
        // 添加表头行
        HSSFRow hssfRow = sheet.createRow(0);
        // 设置单元格格式居中
        HSSFCellStyle cellStyle = workbook.createCellStyle();
//        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 添加表头内容
        HSSFCell headCell = hssfRow.createCell(0);
        headCell.setCellValue("id");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(1);
        headCell.setCellValue("名称");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(2);
        headCell.setCellValue("价格");
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(3);
        headCell.setCellValue("库存");
        headCell.setCellStyle(cellStyle);

        // 添加数据内容
        for (int i = 0; i < list.size(); i++) {
            hssfRow = sheet.createRow((int) i + 1);
            Product product = list.get(i);

            // 创建单元格，并设置值
            HSSFCell cell = hssfRow.createCell(0);
            cell.setCellValue(product.getId());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(1);
            cell.setCellValue(product.getName());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(2);
            cell.setCellValue(product.getPrice());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(3);
            cell.setCellValue(product.getStock());
            cell.setCellStyle(cellStyle);
        }

        // 保存Excel文件
        try {
            OutputStream outputStream = new FileOutputStream("C:/Users/15295/Desktop/java/products.xls");
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Product> readExcel() {
        List<Product> list = new ArrayList<Product>();
        HSSFWorkbook workbook = null;

        try {
            // 读取Excel文件
            InputStream inputStream = new FileInputStream("C:/Users/15295/Desktop/java/products.xls");
            workbook = new HSSFWorkbook(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 循环工作表
        for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = workbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }

                // 将单元格中的内容存入集合
                Product product = new Product();

                HSSFCell cell = hssfRow.getCell(0);
                if (cell == null) {
                    continue;
                }
                product.setId((int)cell.getNumericCellValue());

                cell = hssfRow.getCell(1);
                if (cell == null) {
                    continue;
                }
                product.setName(cell.getStringCellValue());

                cell = hssfRow.getCell(2);
                if (cell == null) {
                    continue;
                }
                product.setPrice((int)cell.getNumericCellValue());

                cell = hssfRow.getCell(3);
                if (cell == null) {
                    continue;
                }
                product.setStock((int)cell.getNumericCellValue());
                list.add(product);
            }
        }
        return list;
    }
}
