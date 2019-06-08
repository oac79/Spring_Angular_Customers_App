import { Injectable } from '@angular/core';
import { CUSTOMERS } from './customers.json';
import { Customer } from './customer';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class CustomerService {
  private urlEndPoint: string = 'http://localhost:8080/api/customers';
  private httpHeaders = new HttpHeaders({'Contnet-Type': 'application/json'})

  constructor(private http: HttpClient) { }

  getCustomers(): Observable<Customer[]> {
  //  return of(CUSTOMERS);
  return this.http.get<Customer[]>(this.urlEndPoint);
  }

  create(customer: Customer ): Observable<Customer>{
    return this.http.post<Customer>(this.urlEndPoint, customer, {headers: this.httpHeaders} )
  }

  getCustomer(id): Observable<Customer>{
    return this.http.get<Customer>(`${this.urlEndPoint}/${id}`)
  }
}
