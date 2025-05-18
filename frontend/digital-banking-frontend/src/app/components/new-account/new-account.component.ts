import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CustomerService } from '../../services/customer.service';
import { AccountService } from '../../services/account.service';
import { Customer } from '../../models/customer.model';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-new-account',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './new-account.component.html',
  styleUrl: './new-account.component.css'
})
export class NewAccountComponent implements OnInit {
  accountFormGroup!: FormGroup;
  customers: Customer[] = [];
  accountType: string = 'CURRENT';
  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private customerService: CustomerService,
    private accountService: AccountService,
    private router: Router,
    public authService: AuthService
  ) { }

  ngOnInit(): void {
    this.accountFormGroup = this.fb.group({
      customerId: ['', Validators.required],
      accountType: ['CURRENT'],
      initialBalance: [0, [Validators.required, Validators.min(0)]],
      overdraft: [0, [Validators.required, Validators.min(0)]],
      interestRate: [0, [Validators.required, Validators.min(0), Validators.max(100)]]
    });

    // Load customers
    this.customerService.getCustomers().subscribe({
      next: (data) => {
        this.customers = data;
      },
      error: (err) => {
        this.errorMessage = err.message;
      }
    });

    // Listen for changes to the account type
    this.accountFormGroup.get('accountType')?.valueChanges.subscribe(value => {
      this.accountType = value;
    });
  }

  handleSaveAccount() {
    this.errorMessage = '';
    this.successMessage = '';

    const customerId = this.accountFormGroup.value.customerId;
    const initialBalance = this.accountFormGroup.value.initialBalance;

    if (this.accountType === 'CURRENT') {
      const overdraft = this.accountFormGroup.value.overdraft;

      this.accountService.saveCurrentAccount(customerId, initialBalance, overdraft).subscribe({
        next: (data) => {
          this.successMessage = 'Current account created successfully!';
          this.accountFormGroup.reset();
          this.accountFormGroup.patchValue({
            accountType: 'CURRENT',
            initialBalance: 0,
            overdraft: 0,
            interestRate: 0
          });
        },
        error: (err) => {
          this.errorMessage = err.message;
        }
      });
    } else if (this.accountType === 'SAVING') {
      const interestRate = this.accountFormGroup.value.interestRate;

      this.accountService.saveSavingAccount(customerId, initialBalance, interestRate).subscribe({
        next: (data) => {
          this.successMessage = 'Saving account created successfully!';
          this.accountFormGroup.reset();
          this.accountFormGroup.patchValue({
            accountType: 'CURRENT',
            initialBalance: 0,
            overdraft: 0,
            interestRate: 0
          });
        },
        error: (err) => {
          this.errorMessage = err.message;
        }
      });
    }
  }
}
