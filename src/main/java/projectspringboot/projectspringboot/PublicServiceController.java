/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectspringboot.projectspringboot;

import java.util.HashMap;
import java.util.Map;
import model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author NITRO
 */
@RestController
public class PublicServiceController {
    private static Map<String, Product> productRepo = new HashMap<>();
    static{
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        //menambahkan set harga
        //set price
        honey.setPrice(20000.0);
        //menambahkan set diskon
        //set disc
        honey.setDisc(10);
        //menambahkan untuk menghitung total harga
        //set total
        honey.setTotal(honey.getPrice()-(honey.getPrice()*honey.getDisc()/100));
        productRepo.put(honey.getId(), honey);
    
        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        //menambahkan set harga
        //set price
        almond.setPrice(25000.0);
        //menambahkan set diskon
        //set disc
        almond.setDisc(10);
        //menambahkan untuk menghitung total harga
        //set total
        almond.setTotal(almond.getPrice()-(almond.getPrice()*almond.getDisc()/100));
        productRepo.put(almond.getId(), almond);
    }
    
    //DELETE
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id){
        productRepo.remove(id);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }
    
    //PUT
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product){
        //keadaan saat id tidak dapat ditemukan, maka data tidak bisa di update
        if(!productRepo.containsKey(id)){
            return new ResponseEntity<>("Tidak ada product key", HttpStatus.NOT_FOUND);
        }
        //keadaan saat id sama, maka data dapat di update
        else{
            //menghitung total diskon harga
            product.setTotal(product.getPrice()-(product.getPrice()*product.getDisc())/100);
            productRepo.remove(id);
            product.setId(id);
            productRepo.put(id, product);
            return new ResponseEntity<>("Product is update successfully", HttpStatus.OK);  
        }
    }
    
    //POST
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        //keaadan saat id yang sama, maka tidak bisa membuat id yang sama
        if(productRepo.containsKey(product.getId())){
            return new ResponseEntity<>("Produk key tidak boleh sama", HttpStatus.OK);
        }
        //keadaan saat id yang berbeda/belum pernah dibuat, maka dapat membuat id baru
        else{
            //menghitung total diskon harga
            product.setTotal(product.getPrice()-(product.getPrice()*product.getDisc())/100);
            productRepo.put(product.getId(), product);
            return new ResponseEntity<>("Product is created successfuly", HttpStatus.CREATED);
        }
    }
    
    //GET
    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct(){
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
    
    
}
