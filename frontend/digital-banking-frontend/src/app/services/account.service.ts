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
    return this.http.get<Array<BankAccount>>(`${this.host}/accounts`);
  }

  public getAccount(accountId: string): Observable<BankAccount> {
    return this.http.get<BankAccount>(`${this.host}/accounts/${accountId}`);
  }

  public getAccountHistory(accountId: string): Observable<Array<any>> {
    return this.http.get<Array<any>>(`${this.host}/accounts/${accountId}/operations`);
  }

  public getAccountOperations(accountId: string, page: number, size: number): Observable<AccountHistory> {
    return this.http.get<AccountHistory>(`${this.host}/accounts/${accountId}/pageOperations?page=${page}&size=${size}`);
  }

  public debit(debitOperation: DebitOperation): Observable<DebitOperation> {
    return this.http.post<DebitOperation>(`${this.host}/accounts/debit`, debitOperation);
  }

  public credit(creditOperation: CreditOperation): Observable<CreditOperation> {
    return this.http.post<CreditOperation>(`${this.host}/accounts/credit`, creditOperation);
  }

  public transfer(transferOperation: TransferOperation): Observable<TransferOperation> {
    return this.http.post<TransferOperation>(`${this.host}/accounts/transfer`, transferOperation);
  }

  public getCustomerAccounts(customerId: number): Observable<Array<BankAccount>> {
    return this.http.get<Array<BankAccount>>(`${this.host}/accounts/customer/${customerId}`);
  }

  public searchAccountsByBalanceRange(minBalance: number, maxBalance: number): Observable<Array<BankAccount>> {
    return this.http.get<Array<BankAccount>>(`${this.host}/accounts/search/balance?min=${minBalance}&max=${maxBalance}`);
  }

  public searchAccountsByCustomerName(customerName: string): Observable<Array<BankAccount>> {
    return this.http.get<Array<BankAccount>>(`${this.host}/accounts/search/customer?name=${customerName}`);
  }

  public saveCurrentAccount(customerId: number, initialBalance: number, overdraft: number): Observable<any> {
    return this.http.post(`${this.host}/accounts/current`, {
      customerId,
      initialBalance,
      overdraft
    });
  }

  public saveSavingAccount(customerId: number, initialBalance: number, interestRate: number): Observable<any> {
    return this.http.post(`${this.host}/accounts/saving`, {
      customerId,
      initialBalance,
      interestRate
    });
  }
}
