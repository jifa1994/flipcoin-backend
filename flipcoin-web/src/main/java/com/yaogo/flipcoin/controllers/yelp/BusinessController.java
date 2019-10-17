package com.yaogo.flipcoin.controllers.yelp;

import com.yaogo.flipcoin.clients.BusinessClient;
import com.yaogo.flipcoin.models.Business;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/business")
@CrossOrigin
public class BusinessController {

    private BusinessClient businessClient;

    public BusinessController(BusinessClient businessClient) {
        this.businessClient = businessClient;
    }

    @GetMapping("/all/{term}={termClass}&{location}={locationClass}")
    public Iterable<Business> getAllBusiness(@PathVariable String term,
                                             @PathVariable String termClass,
                                             @PathVariable String location,
                                             @PathVariable String locationClass) throws Exception {
        String[] params = {term+","+termClass, location+","+locationClass};
        return businessClient.sendGet(params);

    }
}
