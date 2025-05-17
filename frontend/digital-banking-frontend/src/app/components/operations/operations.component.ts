import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';
import { AccountHistory } from '../../models/operation.model';
import { AccountService } from '../../services/account.service';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-operations',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './operations.component.html',
  styleUrls: ['./operations.component.css']
})
export class OperationsComponent implements OnInit {
  accountId!: string;
  operationFormGroup!: FormGroup;
  currentPage: number = 0;
  pageSize: number = 5;
  accountHistory$!: Observable<AccountHistory>;
  errorMessage!: string;
  operationType: string = 'DEBIT';

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private accountService: AccountService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.accountId = this.route.snapshot.params['id'];
    this.operationFormGroup = this.fb.group({
      operationType: this.fb.control(null),
      amount: this.fb.control(0),
      description: this.fb.control(null),
      accountDestination: this.fb.control(null)
    });
    this.loadAccountHistory();
  }

  loadAccountHistory() {
    this.accountHistory$ = this.accountService
      .getAccountOperations(this.accountId, this.currentPage, this.pageSize)
      .pipe(
        catchError(err => {
          this.errorMessage = err.message;
          return throwError(() => err);
        })
      );
  }

  gotoPage(page: number) {
    this.currentPage = page;
    this.loadAccountHistory();
  }

  handleOperation() {
    const operationType = this.operationFormGroup.value.operationType;
    if (operationType === 'DEBIT') {
      this.debit();
    } else if (operationType === 'CREDIT') {
      this.credit();
    } else if (operationType === 'TRANSFER') {
      this.transfer();
    }
  }

  debit() {
    const amount = this.operationFormGroup.value.amount;
    const description = this.operationFormGroup.value.description;
    const debitOperation = { accountId: this.accountId, amount, description };
    this.accountService.debit(debitOperation).subscribe({
      next: data => {
        alert('Debit operation has been successfully processed!');
        this.operationFormGroup.reset();
        this.loadAccountHistory();
      },
      error: err => {
        this.errorMessage = err.message;
      }
    });
  }

  credit() {
    const amount = this.operationFormGroup.value.amount;
    const description = this.operationFormGroup.value.description;
    const creditOperation = { accountId: this.accountId, amount, description };
    this.accountService.credit(creditOperation).subscribe({
      next: data => {
        alert('Credit operation has been successfully processed!');
        this.operationFormGroup.reset();
        this.loadAccountHistory();
      },
      error: err => {
        this.errorMessage = err.message;
      }
    });
  }

  transfer() {
    const amount = this.operationFormGroup.value.amount;
    const description = this.operationFormGroup.value.description;
    const accountDestination = this.operationFormGroup.value.accountDestination;
    const transferOperation = {
      accountSource: this.accountId,
      accountDestination,
      amount,
      description
    };
    this.accountService.transfer(transferOperation).subscribe({
      next: data => {
        alert('Transfer operation has been successfully processed!');
        this.operationFormGroup.reset();
        this.loadAccountHistory();
      },
      error: err => {
        this.errorMessage = err.message;
      }
    });
  }
}
