package cz.osu.swi_cafe.controllers;

import cz.osu.swi_cafe.services.OrderService;
import cz.osu.swi_cafe.tables.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Orders")
@CrossOrigin(origins = "*" ,allowedHeaders= "*" )
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody Order orderFromReact) {

     orderService.placeOrder(orderFromReact);

        return ResponseEntity.ok("Objednávka byla úspěšně vytvořena.");
    }
}
