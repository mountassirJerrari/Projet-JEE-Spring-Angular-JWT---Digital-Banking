import { Routes } from '@angular/router';
import { CustomersComponent } from './components/customers/customers.component';
import { AccountsComponent } from './components/accounts/accounts.component';
import { OperationsComponent } from './components/operations/operations.component';
import { NewCustomerComponent } from './components/new-customer/new-customer.component';
import { CustomerAccountsComponent } from './components/customer-accounts/customer-accounts.component';
import { LoginComponent } from './components/login/login.component';
import { UnauthorizedComponent } from './components/unauthorized/unauthorized.component';
import { AuthGuard } from './guards/auth.guard';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'unauthorized', component: UnauthorizedComponent },
    {
        path: 'customers',
        component: CustomersComponent,
        canActivate: [AuthGuard],
        data: { roles: ['SCOPE_USER'] }
    },
    {
        path: 'new-customer',
        component: NewCustomerComponent,
        canActivate: [AuthGuard],
        data: { roles: ['SCOPE_ADMIN'] }
    },
    {
        path: 'customer-accounts/:id',
        component: CustomerAccountsComponent,
        canActivate: [AuthGuard],
        data: { roles: ['SCOPE_USER'] }
    },
    {
        path: 'accounts',
        component: AccountsComponent,
        canActivate: [AuthGuard],
        data: { roles: ['SCOPE_USER'] }
    },
    {
        path: 'accounts/:id',
        component: OperationsComponent,
        canActivate: [AuthGuard],
        data: { roles: ['SCOPE_USER'] }
    },
    { path: '', redirectTo: '/customers', pathMatch: 'full' }
];
