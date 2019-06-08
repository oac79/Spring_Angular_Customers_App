import { Component, OnInit } from '@angular/core';
import { Customer } from './customer';
import { CustomerService } from './customer.service';


@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html'
})
export class CustomersComponent implements OnInit {

  customers: Customer[];

  constructor(private customerService: CustomerService) {
    this.customerService = customerService;
  }

  ngOnInit() {
    this.customerService.getCustomers().subscribe(
      customers => this.customers = customers
    );
  }

}
