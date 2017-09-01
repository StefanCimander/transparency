import { Routes } from '@angular/router';

import {
  DashboardComponent,
  DependenciesComponent,
} from './components';

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
