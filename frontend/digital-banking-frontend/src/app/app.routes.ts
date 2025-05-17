import { Routes } from '@angular/router';
import { CustomersComponent } from './components/customers/customers.component';
import { AccountsComponent } from './components/accounts/accounts.component';
import { OperationsComponent } from './components/operations/operations.component';
import { NewCustomerComponent } from './components/new-customer/new-customer.component';
import { CustomerAccountsComponent } from './components/customer-accounts/customer-accounts.component';

export const routes: Routes = [
    { path: 'customers', component: CustomersComponent },
    { path: 'new-customer', component: NewCustomerComponent },
    { path: 'customer-accounts/:id', component: CustomerAccountsComponent },
    { path: 'accounts', component: AccountsComponent },
    { path: 'accounts/:id', component: OperationsComponent },
    { path: '', redirectTo: '/customers', pathMatch: 'full' }
];
