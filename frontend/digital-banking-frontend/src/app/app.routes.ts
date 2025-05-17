import { Routes } from '@angular/router';
import { CustomersComponent } from './components/customers/customers.component';
import { AccountsComponent } from './components/accounts/accounts.component';
import { OperationsComponent } from './components/operations/operations.component';

export const routes: Routes = [
    { path: 'customers', component: CustomersComponent },
    { path: 'accounts', component: AccountsComponent },
    { path: 'accounts/:id', component: OperationsComponent },
    { path: '', redirectTo: '/customers', pathMatch: 'full' }
];
