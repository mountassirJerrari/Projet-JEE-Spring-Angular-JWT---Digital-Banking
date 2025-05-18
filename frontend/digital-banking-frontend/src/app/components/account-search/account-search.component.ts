import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AccountService } from '../../services/account.service';
import { BankAccount } from '../../models/account.model';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-account-search',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './account-search.component.html',
  styleUrl: './account-search.component.css'
})
export class AccountSearchComponent implements OnInit {
  searchFormGroup!: FormGroup;
  accounts: BankAccount[] = [];
  searchType: string = 'balance';
  errorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private accountService: AccountService,
    private router: Router,
    public authService: AuthService
  ) { }

  ngOnInit(): void {
    this.searchFormGroup = this.fb.group({
      searchType: ['balance'],
      minBalance: [0],
      maxBalance: [1000000],
      customerName: ['']
    });

    // Listen for changes to the search type
    this.searchFormGroup.get('searchType')?.valueChanges.subscribe(value => {
      this.searchType = value;
    });
  }

  handleSearchAccounts() {
    this.errorMessage = '';
    const searchType = this.searchFormGroup.value.searchType;

    if (searchType === 'balance') {
      const minBalance = this.searchFormGroup.value.minBalance;
      const maxBalance = this.searchFormGroup.value.maxBalance;

      this.accountService.searchAccountsByBalanceRange(minBalance, maxBalance).subscribe({
        next: (data) => {
          this.accounts = data;
          if (this.accounts.length === 0) {
            this.errorMessage = 'No accounts found in this balance range';
          }
        },
        error: (err) => {
          this.errorMessage = err.message;
        }
      });
    } else if (searchType === 'customer') {
      const customerName = this.searchFormGroup.value.customerName;

      if (!customerName || customerName.trim() === '') {
        this.errorMessage = 'Please enter a customer name';
        return;
      }

      this.accountService.searchAccountsByCustomerName(customerName).subscribe({
        next: (data) => {
          this.accounts = data;
          if (this.accounts.length === 0) {
            this.errorMessage = 'No accounts found for this customer name';
          }
        },
        error: (err) => {
          this.errorMessage = err.message;
        }
      });
    }
  }

  handleAccountOperations(account: BankAccount) {
    this.router.navigateByUrl('/accounts/' + account.id, { state: account });
  }
}
