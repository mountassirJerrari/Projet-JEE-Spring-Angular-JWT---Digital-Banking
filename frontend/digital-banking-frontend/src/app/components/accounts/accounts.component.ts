import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';
import { BankAccount } from '../../models/account.model';
import { AccountService } from '../../services/account.service';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-accounts',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.css'
})
export class AccountsComponent implements OnInit {
  accounts$!: Observable<Array<BankAccount>>;
  errorMessage!: string;
  searchFormGroup!: FormGroup;

  constructor(
    private accountService: AccountService,
    private fb: FormBuilder,
    private router: Router,
    public authService: AuthService
  ) { }

  ngOnInit(): void {
    this.searchFormGroup = this.fb.group({
      keyword: this.fb.control('')
    });
    this.loadAccounts();
  }

  loadAccounts() {
    this.accounts$ = this.accountService.getAccounts().pipe(
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
