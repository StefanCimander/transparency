import { Routes } from '@angular/router';

import { DashboardComponent, DependenciesDetailsComponent } from './dashboard';


export const AppRoutes: Routes = [
    {
        path: 'dashboard',
        component: DashboardComponent,
    },
    {
        path: 'dependencies',
        component: DependenciesDetailsComponent,
    },
    { path: '',
        redirectTo: '/dashboard',
        pathMatch: 'full'
    },
];
