package com.project.Task1.Customer;

import com.project.Task1.Customer.Model.*;
import com.project.Task1.Util.FileUploadUtil;
import com.project.Task1.Util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CustomerService {

    @Autowired
    private NormalCustomerEntityRepository normalCustomerEntityRepository;

    @Autowired
    private CustomerEntityRepository customerEntityRepository;

    public boolean addCustomer(NormalSignupDTO normalSignupDTO) throws Exception {
        if(normalCustomerEntityRepository.findByEmail(normalSignupDTO.getEmail()) != null){
            return false;
        }else {
            NormalCustomerEntity normalCustomerEntity = new NormalCustomerEntity();
            normalCustomerEntity.setFirstName(normalSignupDTO.getFirstName());
            normalCustomerEntity.setLastName(normalSignupDTO.getLastName());
            normalCustomerEntity.setEmail(normalSignupDTO.getEmail());
            normalCustomerEntity.setPassword(normalSignupDTO.getPassword());

            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setNormalCustomerEntity(normalCustomerEntity);
            customerEntity.setEmail(normalSignupDTO.getEmail());

            normalCustomerEntityRepository.save(normalCustomerEntity);
            customerEntityRepository.save(customerEntity);
            return true;
        }
    }


    public CustomerGetDTO getCustomer(String email){
        CustomerEntity customerEntity = customerEntityRepository.findByEmail(email);
        if(customerEntity!= null && customerEntity.getNormalCustomerEntity() != null){
            NormalCustomerEntity normalCustomerEntity = customerEntity.getNormalCustomerEntity();
            return  ObjectMapperUtils.map(normalCustomerEntity,CustomerGetDTO.class);
        }else if(customerEntity!=null && customerEntity.getOuthCustomerEntity() != null) {
            OuthCustomerEntity outhCustomerEntity = customerEntity.getOuthCustomerEntity();
            return ObjectMapperUtils.map(outhCustomerEntity,CustomerGetDTO.class);
        }
        else return null;
    }


    public CustomerGetDTO login(LoginDTO loginDTO){
        NormalCustomerEntity normalCustomerEntity = normalCustomerEntityRepository.findByEmailAndPassword(loginDTO.getEmail(),loginDTO.getPass());
        if(normalCustomerEntity ==null) return null;

        return ObjectMapperUtils.map(normalCustomerEntity,CustomerGetDTO.class);

    }

    public boolean uploadImage(int id, MultipartFile multipartFile) throws IOException {
        CustomerEntity customerEntity = customerEntityRepository.getOne(id);
        if(customerEntity != null) {
            String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            customerEntity.setPhoto(filename);
            customerEntityRepository.save(customerEntity);

            String uploadDir = "customer-photos/" + id;
            FileUploadUtil.saveFile(uploadDir, filename, multipartFile);
            return true;
        }else return false;
    }
}