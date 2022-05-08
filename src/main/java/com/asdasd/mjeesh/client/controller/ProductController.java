package com.asdasd.mjeesh.client.controller;

import com.asdasd.mjeesh.client.communication.ProducerCommunication;
import com.asdasd.mjeesh.client.communication.ProductCommunication;
import com.asdasd.mjeesh.client.dto.ProductFilter;
import com.asdasd.mjeesh.client.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductCommunication productCommunication;
    private final ProducerCommunication producerCommunication;

    @Autowired
    public ProductController(ProductCommunication productCommunication, ProducerCommunication producerCommunication) {
        this.productCommunication = productCommunication;
        this.producerCommunication = producerCommunication;
    }

    @GetMapping("/filter/{pageNo}")
    public String showProductsByFilter(@PathVariable("pageNo") Integer pageNo,
                                       @RequestParam(required = false, name = "titleParam") String title,
                                       @RequestParam(required = false , name = "producerNameParam") String producerName,
                                       @RequestParam(defaultValue = "0", name = "minCostParam") BigDecimal minCost,
                                       @RequestParam(defaultValue = "999999", name = "maxCostParam") BigDecimal maxCost,
                                       Model model) {
        ProductFilter filter = new ProductFilter(title, producerName, minCost, maxCost);
        model.addAttribute("products", productCommunication.getAllProductsByFilter(filter, pageNo));
        return "product/product-page-view";
    }

    @GetMapping("/{pageNo}")
    public String showAllInPage(@PathVariable Integer pageNo,
                               Model model) {
        model.addAttribute("products", productCommunication.getAllProducts(pageNo));
        model.addAttribute("pageNo", pageNo);
        return "product/product-page-view";
    }

    @GetMapping("/id/{id}")
    public String showById(@PathVariable("id") Long id,
                          Model model) {
        model.addAttribute("product", productCommunication.getById(id));
        return "product/show-product-view";
    }

    @GetMapping("/productsOfProducer/{id}/{pageNo}")
    public String showAllProductsByProducerId(@PathVariable("id") Long id,
                                              @PathVariable("pageNo") Integer pageNo,
                                              Model model) {
        model.addAttribute(
                "products", productCommunication.getAllProductsByProducerId(id, pageNo));
        return "product/product-page-view";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("producers", producerCommunication.getAllProducers(null));

        return "product/new-product-view";
    }

    @PostMapping("")
    public String saveProduct(@RequestParam("title") String title,
                              @RequestParam("cost") BigDecimal cost,
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("shelfLife") LocalDate shelfLife,
                              @RequestParam("pickedProducerId") Long producerId) {
        productCommunication.saveOrUpdateProduct
                (new Product(null, title, cost, shelfLife, producerCommunication.getById(producerId)));
        return "redirect:/products/0";
    }

    @GetMapping("/{id}/edit")
    public String editProduct(Model model,
                         @PathVariable Long id) {
        model.addAttribute("product", productCommunication.getById(id));
        model.addAttribute("producers", producerCommunication.getAllProducers(null));
        return "product/edit-product-view";
    }

    @PostMapping("/id/{id}")
    public String updateProduct(@PathVariable("id") Long id,
                                @RequestParam("title") String title,
                                @RequestParam("cost") BigDecimal cost,
                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("shelfLife") LocalDate shelfLife,
                                @RequestParam("pickedProducerId") Long producerId) {
        var product = productCommunication.getById(id);
        product.setTitle(title);
        product.setCost(cost);
        product.setShelfLife(shelfLife);
        product.setProducer(producerCommunication.getById(producerId));
        productCommunication.saveOrUpdateProduct(product);

        return "redirect:/products/0";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") Long id) {
        productCommunication.deleteProduct(id);
        return "redirect:/products/0";
    }

}
