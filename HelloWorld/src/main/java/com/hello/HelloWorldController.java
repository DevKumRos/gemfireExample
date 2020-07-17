package com.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloWorldController {

    @Autowired
    HelloWorldService helloWorldService;

    @RequestMapping(value="/getAll")
    public Map<String, String> getAllValue() {
        return helloWorldService.getRegionDetails();
    }


    @RequestMapping(value="/hello/{key}", produces = "text/html")
    public String getHelloValue(@PathVariable("key") String key) {

        long timeBeforeQuery = System.currentTimeMillis();

        Customer helloValue = helloWorldService.getHelloValue(key);

        long timeElapsed = System.currentTimeMillis() - timeBeforeQuery;

        return "<html><body>"
                + "<i>key:</i> " + helloValue.getCustomerNumber() + "<br>"
                + "<i>value:</i> " + helloValue.getFirstName() + "<br>"
                + "<i>time to look up:</i> <b>" + helloValue.getLastName() + "ms</b>"
                + "</body></html>";
    }

}
