import { Routes } from '@angular/router';

import { DashboardComponent } from './dashboard';
import { DependenciesComponent } from './dependencies';


export const AppRoutes: Routes = [
  {
    path: 'dashboard',
    component: DashboardComponent,
  },
  {
    path: 'dependencies',
    component: DependenciesComponent,
  },
  { path: '',
    redirectTo: '/dashboard',
    pathMatch: 'full'
  },
];
