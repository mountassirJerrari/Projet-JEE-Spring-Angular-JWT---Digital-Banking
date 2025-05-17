import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BankAccount } from '../models/account.model';
import { environment } from '../../environments/environment';
import { AccountHistory, CreditOperation, DebitOperation, TransferOperation } from '../models/operation.model';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private host = environment.backendHost;

  constructor(private http: HttpClient) { }

  public getAccounts(): Observable<Array<BankAccount>> {
    return this.http.get<Array<BankAccount>>(`${this.host}/api/accounts`);
  }

  public getAccount(accountId: string): Observable<BankAccount> {
    return this.http.get<BankAccount>(`${this.host}/api/accounts/${accountId}`);
  }

  public getAccountHistory(accountId: string): Observable<Array<any>> {
    return this.http.get<Array<any>>(`${this.host}/api/accounts/${accountId}/operations`);
  }

  public getAccountOperations(accountId: string, page: number, size: number): Observable<AccountHistory> {
    return this.http.get<AccountHistory>(`${this.host}/api/accounts/${accountId}/pageOperations?page=${page}&size=${size}`);
  }

  public debit(debitOperation: DebitOperation): Observable<DebitOperation> {
    return this.http.post<DebitOperation>(`${this.host}/api/accounts/debit`, debitOperation);
  }

  public credit(creditOperation: CreditOperation): Observable<CreditOperation> {
    return this.http.post<CreditOperation>(`${this.host}/api/accounts/credit`, creditOperation);
  }

  public transfer(transferOperation: TransferOperation): Observable<TransferOperation> {
    return this.http.post<TransferOperation>(`${this.host}/api/accounts/transfer`, transferOperation);
  }

  public getCustomerAccounts(customerId: number): Observable<Array<BankAccount>> {
    return this.http.get<Array<BankAccount>>(`${this.host}/api/accounts/customer/${customerId}`);
  }
}
