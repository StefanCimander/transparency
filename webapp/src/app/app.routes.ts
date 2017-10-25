import { Routes } from '@angular/router';

import {
  DashboardComponent,
  DependenciesDetailsComponent,
  DistributionDetailsComponent
} from './dashboard';

export const AppRoutes: Routes = [
  {
    path: 'dashboard',
    component: DashboardComponent,
  },
  {
    path: 'dependencies',
    component: DependenciesDetailsComponent,
  },
  {
    path: 'distribution',
    component: DistributionDetailsComponent,
  },
  { path: '',
    redirectTo: '/dashboard',
    pathMatch: 'full'
  },
];
