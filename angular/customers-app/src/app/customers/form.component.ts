import { Component, OnInit } from '@angular/core';
import { Customer } from './customer';
import { CustomerService } from './customer.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
})
export class FormComponent implements OnInit {

  private customer: Customer = new Customer()
  private title: string = "Create Customer"

  constructor(private customerService: CustomerService,
  private router: Router) { }

  ngOnInit() {
  }

  public create(): void{
    this.customerService.create(this.customer)
    .subscribe(customer => {
      this.router.navigate(['/customers'])
      Swal.fire('New Customer', `Customer ${customer.name} exit!`, 'success')
  }
);
}
}
