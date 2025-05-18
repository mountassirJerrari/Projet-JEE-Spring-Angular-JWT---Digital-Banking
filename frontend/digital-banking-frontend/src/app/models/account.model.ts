import { Customer } from './customer.model';

export interface BankAccount {
  id: string;
  balance: number;
  createdAt: Date;
  status: string;
  type: string;
  createdBy: string;
  customerDTO: Customer;
}

export interface CurrentBankAccount extends BankAccount {
  overdraft: number;
}

export interface SavingBankAccount extends BankAccount {
  interestRate: number;
}
