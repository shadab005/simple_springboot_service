package com.simple_springboot_service.controller;

import com.simple_springboot_service.model.Person;
import com.simple_springboot_service.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello from simple controller";
    }

    /*
     * http://localhost:8080/person/detail?firstName=shadab&lastName=hassan
     */
    @GetMapping("/detail")
    public Person getDetail(String firstName, String lastName) {
        System.out.println("/person/detail invoked with firstName : " + firstName + " and lastName = " + lastName);
        return personService.getDetail(firstName, lastName);
    }

    /**
     * http://localhost:8080/person/echo/firstName/John/lastName/Doe/show
     * output:
     * {"firstName":"John","lastName":"Doe","age":0,"address":null}
     */
    @GetMapping("/echo/firstName/{firstName}/lastName/{lastName}/show")
    public Person echo(@ModelAttribute Person p) {
        System.out.println("Echo called");
        return p;
    }

    /**
     * http://localhost:8080/person/echoWithStatus/firstName/John/lastName/Doe/show
     * output:
     * {"firstName":"John","lastName":"Doe","age":0,"address":null}
     */
    //ResponseEntity represents an HTTP response, including headers, body, and status code.
    @RequestMapping("/echoWithStatus/firstName/{firstName}/lastName/{lastName}/show")
    public ResponseEntity<Person> echoWithStatus(@ModelAttribute Person p) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", "237623823092");
        return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers).body(p);
    }


    /**
     * POST CALL
     *
     * http://localhost:8080/person/save
     * Body
     * {
     * 	"firstName":"Shadab",
     * 	"age":25,
     * 	"address":"zzzz"
     * }
     * Headers
     * Content-Type : application/json
     *
     */
    @PostMapping("/save")
    public ResponseEntity<Person> save(@RequestBody  Person p) {
        System.out.println("Post called with : " + p);
        return ResponseEntity.ok(p);
    }
}
