import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';
import { BankAccount } from '../../models/account.model';
import { Customer } from '../../models/customer.model';
import { AccountService } from '../../services/account.service';
import { CustomerService } from '../../services/customer.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-customer-accounts',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './customer-accounts.component.html',
  styleUrl: './customer-accounts.component.css'
})
export class CustomerAccountsComponent implements OnInit {
  customerId!: number;
  customer!: Customer;
  accounts$!: Observable<Array<BankAccount>>;
  errorMessage!: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private accountService: AccountService,
    private customerService: CustomerService
  ) {
    this.customer = this.router.getCurrentNavigation()?.extras.state as Customer;
  }

  ngOnInit(): void {
    this.customerId = this.route.snapshot.params['id'];
    if (!this.customer) {
      this.customerService.getCustomer(this.customerId).subscribe({
        next: (customer) => {
          this.customer = customer;
        },
        error: (err) => {
          this.errorMessage = err.message;
        }
      });
    }
    this.loadCustomerAccounts();
  }

  loadCustomerAccounts() {
    this.accounts$ = this.accountService.getCustomerAccounts(this.customerId).pipe(
      catchError(err => {
        this.errorMessage = err.message;
        return throwError(() => err);
      })
    );
  }

  handleAccountOperations(account: BankAccount) {
    this.router.navigateByUrl('/accounts/' + account.id, { state: account });
  }
}
