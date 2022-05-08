package com.asdasd.mjeesh.client.controller;

import com.asdasd.mjeesh.client.communication.ProducerCommunication;
import com.asdasd.mjeesh.client.model.Contact;
import com.asdasd.mjeesh.client.model.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/producers")
public class ProducerController {
    private final ProducerCommunication producerCommunication;

    @Autowired
    public ProducerController(ProducerCommunication producerCommunication) {
        this.producerCommunication = producerCommunication;
    }

    @GetMapping("/{pageNo}")
    public String showAll(@PathVariable("pageNo") Integer pageNo,
                          Model model) {
        model.addAttribute("allProducers", producerCommunication.getAllProducers(pageNo));
        return "producer/producer-page-view";
    }

    @GetMapping("/id/{id}")
    public String showById(@PathVariable("id") Long id,
                          Model model) {
        model.addAttribute("producer", producerCommunication.getById(id));
        return "producer/show-producer-view";
    }
    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("producer", new Producer());
        return "producer/new-producer-view";
    }

    @PostMapping("")
    public String saveProducer(@RequestParam("name") String name,
                               @RequestParam("firstPhone") Long firstPhone,
                               @RequestParam("secondPhone") Long secondPhone) {
        var producer = new Producer(null, name,
                List.of(new Contact(null, firstPhone),
                        new Contact(null, secondPhone)));

        producerCommunication.saveOrUpdateProducer(producer);
        return "redirect:/producers/0";
    }

    @GetMapping("/{id}/edit")
    public String editProducer(Model model,
                              @PathVariable("id") Long id) {
        model.addAttribute("producer", producerCommunication.getById(id));
        return "producer/edit-producer-view";
    }

    @PostMapping("/id/{id}")
    public String updateProduct(@PathVariable("id") Long id,
                                @RequestParam("name") String name,
                                @RequestParam("firstPhone") Long firstPhone,
                                @RequestParam("secondPhone") Long secondPhone) {
        var producer = producerCommunication.getById(id);
        producer.setName(name);

        if (!firstPhone.equals(producer.getContacts().get(0).getNumber())) {
            producer.getContacts().set(0, new Contact(null, firstPhone));
        }
        if (!secondPhone.equals(producer.getContacts().get(1).getNumber())) {
            producer.getContacts().set(1, new Contact(null, secondPhone));
        }

        producerCommunication.saveOrUpdateProducer(producer);

        return "redirect:/producers/0";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") Long id) {
        producerCommunication.deleteProducer(id);
        return "redirect:/producers/0";
    }

}
