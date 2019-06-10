import { Component, OnInit } from '@angular/core';
import { Customer } from './customer';
import { CustomerService } from './customer.service';
import Swal from 'sweetalert2';


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

  delete(customer: Customer): void{
    const swalWithBootstrapButtons = Swal.mixin({
  customClass: {
    confirmButton: 'btn btn-success',
    cancelButton: 'btn btn-danger'
  },
  buttonsStyling: false,
})

swalWithBootstrapButtons.fire({
  title: 'Are you sure?',
  text: "You won't be able to revert this!",
  type: 'warning',
  showCancelButton: true,
  confirmButtonText: 'Yes, delete it!',
  cancelButtonText: 'No, cancel!',
  reverseButtons: true
}).then((result) => {
  if (result.value) {

    this.customerService.delete(customer.id).subscribe(
      response => {
        this.customers = this.customers.filter(cus => cus !== customer)
        swalWithBootstrapButtons.fire(
          'Deleted!',
          'Your Customer has been deleted',
          'success'
        )
      }
    )

  }
})
  }

}
