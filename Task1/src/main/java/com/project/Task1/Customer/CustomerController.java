package com.project.Task1.Customer;

import com.project.Task1.Customer.Model.CustomerGetDTO;
import com.project.Task1.Customer.Model.LoginDTO;
import com.project.Task1.Customer.Model.NormalCustomerEntityRepository;
import com.project.Task1.Customer.Model.NormalSignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/")
@ResponseBody
public class CustomerController {

    @Autowired
    private NormalCustomerEntityRepository normalCustomerEntityRepository;

    @Autowired
    private CustomerService customerService;

    @GetMapping("normal-customer")
    public ResponseEntity<Object> getAllGuest() {
        return ResponseEntity.ok(normalCustomerEntityRepository.findAll());

    }

    @PostMapping("normal-signup")
    public ResponseEntity<Object> normalSignup(@RequestBody NormalSignupDTO normalSignupDTO) throws Exception{
        boolean out = customerService.addCustomer(normalSignupDTO);
        if (out) return ResponseEntity.ok("< Customer Record >");
        return ResponseEntity.badRequest().body("Already signup with this email!!");
    }

    @PostMapping("normal-login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO){
        CustomerGetDTO out=  customerService.login(loginDTO);
        if(out!=null) return ResponseEntity.status(200).body(out);
        return ResponseEntity.badRequest().body("Incorrect credentials");
    }

    @PostMapping("customer/image/{id}")
    public ResponseEntity<Object> guestCheckOut(@PathVariable("id") int id, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        boolean out = customerService.uploadImage(id,multipartFile);
        if(out) return ResponseEntity.ok().body("<PPhoto Uploaded Successfully>");
        return ResponseEntity.status(404).body("Customer doesn't exist");
    }


    @GetMapping("customer/{email}/")
    public ResponseEntity<Object> getCustomer(@PathVariable("email") String email){
        System.out.println(email);
        CustomerGetDTO customerGetDTO = customerService.getCustomer(email);
        if(customerGetDTO == null) return ResponseEntity.status(404).body("Customer doesn't exist");
        else return ResponseEntity.status(200).body(customerGetDTO);
    }




}
