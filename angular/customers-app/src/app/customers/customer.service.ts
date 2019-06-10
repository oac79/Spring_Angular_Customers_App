import { Injectable } from '@angular/core';
import { CUSTOMERS } from './customers.json';
import { Customer } from './customer';
import { of, Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import   Swal  from 'sweetalert2';
import { Router } from '@angular/router';

@Injectable()
export class CustomerService {
  private urlEndPoint: string = 'http://localhost:8080/api/customers';
  private httpHeaders = new HttpHeaders({'Contnet-Type': 'application/json'})

  constructor(private http: HttpClient, private router: Router) { }

  getCustomers(): Observable<Customer[]> {
  //  return of(CUSTOMERS);
  return this.http.get<Customer[]>(this.urlEndPoint);
  }

  create(customer: Customer ): Observable<Customer>{
    return this.http.post<Customer>(this.urlEndPoint, customer, {headers: this.httpHeaders} ).pipe(
      catchError(e => {
        console.error(e.error.message);
        Swal.fire('ERROR', e.error.message, 'error');
        return throwError(e);
      })
    )
  }

  getCustomer(id: number): Observable<Customer>{
    return this.http.get<Customer>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        this.router.navigate(['/customers']);
        console.error(e.error.message);
        Swal.fire('ERROR', e.error.message, 'error');
        return throwError(e);
      })
    )
  }

  update(customer: Customer): Observable <Customer>{
    return this.http.put<Customer>(`${this.urlEndPoint}/${customer.id}`, customer, {headers: this.httpHeaders} ).pipe(
      catchError(e => {
        console.error(e.error.message);
        Swal.fire('Error Update Customer', e.error.message, 'error');
        return throwError(e);
      })
    )
  }

  delete(id: number): Observable<Customer>{
    return this.http.delete<Customer>(`${this.urlEndPoint}/${id}`, {headers: this.httpHeaders}).pipe(
      catchError(e => {
        console.error(e.error.message);
        Swal.fire('Error Delete Customer', e.error.message, 'error');
        return throwError(e);
      })
    )
  }
}
