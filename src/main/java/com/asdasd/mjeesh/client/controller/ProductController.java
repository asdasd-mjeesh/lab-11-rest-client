package com.asdasd.mjeesh.client.controller;

import com.asdasd.mjeesh.client.communication.ProducerCommunication;
import com.asdasd.mjeesh.client.communication.ProductCommunication;
import com.asdasd.mjeesh.client.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class ProductController {
    private final ProductCommunication productCommunication;
    private final ProducerCommunication producerCommunication;

    @Autowired
    public ProductController(ProductCommunication productCommunication, ProducerCommunication producerCommunication) {
        this.productCommunication = productCommunication;
        this.producerCommunication = producerCommunication;
    }

    @RequestMapping("/{pageNo}")
    public String showFirstView(@PathVariable Integer pageNo,
                                Model model) {
        model.addAttribute("currentPageProducts", productCommunication.getAllProducts(pageNo));
        model.addAttribute("pageNo", pageNo);

        return "mainly-view";
    }

    @RequestMapping("/producers/{id}")
    public String producerView(@PathVariable Long id,
                               Model model) {
        model.addAttribute("pickedProducer", producerCommunication.getById(id));

        return "producer-info-view";
    }

    @RequestMapping("/producers/allProducts/id{id}/page{pageNo}")
    public String showAllProductsOfPickedProducer(@PathVariable Long id,
                                                  @PathVariable Integer pageNo,
                                                  Model model) {
        model.addAttribute(
                "productsOfCurrentProducer", productCommunication.getAllProductsByProducerId(id, pageNo));
        return "current-producer-products-info-view";
    }

    @RequestMapping("/newProduct")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("allProducers", producerCommunication.getAllProducers(0));
        return "add-new-product-view";
    }

    @RequestMapping("/saveProduct")
    public String saveProduct(@Valid @ModelAttribute("product") Product product,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("producerCommunication", producerCommunication);
            model.addAttribute("allProducers", producerCommunication.getAllProducers(0));
            System.out.println(bindingResult.getFieldErrors());
            return "add-new-product-view";
        }
        productCommunication.saveOrUpdateProduct(product);
        return "redirect:/0";
    }
}
