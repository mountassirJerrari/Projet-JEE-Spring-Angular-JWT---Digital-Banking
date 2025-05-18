import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private host = environment.backendHost;

  constructor(private http: HttpClient) { }

  public getCustomers(): Observable<Array<Customer>> {
    return this.http.get<Array<Customer>>(`${this.host}/customers`);
  }

  public searchCustomers(keyword: string): Observable<Array<Customer>> {
    return this.http.get<Array<Customer>>(`${this.host}/customers/search?keyword=${keyword}`);
  }

  public saveCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(`${this.host}/customers`, customer);
  }

  public deleteCustomer(id: number): Observable<void> {
    return this.http.delete<void>(`${this.host}/customers/${id}`);
  }

  public getCustomer(id: number): Observable<Customer> {
    return this.http.get<Customer>(`${this.host}/customers/${id}`);
  }
}
