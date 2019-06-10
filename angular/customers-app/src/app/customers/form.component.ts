import { Component, OnInit } from '@angular/core';
import { Customer } from './customer';
import { CustomerService } from './customer.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
})
export class FormComponent implements OnInit {

  private customer: Customer = new Customer()
//  private title: string = "Create Customer"

  constructor(private customerService: CustomerService,
  private router: Router,
  private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.loadCustomer();
  }

  loadCustomer(): void{
    this.activatedRoute.params.subscribe(params =>{
      let id = params['id']
      if(id){
        this.customerService.getCustomer(id).subscribe((customer) => this.customer = customer)
        }
      })
  }
  public create(): void{
    this.customerService.create(this.customer)
    .subscribe(_customer => {
      this.router.navigate(['/customers'])
      Swal.fire('New Customer', `${this.customer.name} created successfully!`, 'success')
  }
);
}

  public update(): void{
    this.customerService.update(this.customer)
    .subscribe(_customer => {
      this.router.navigate(['/customers'])
      Swal.fire('New update', ` Customer ${this.customer.name} updated!`, 'success')
    })
  }
}
