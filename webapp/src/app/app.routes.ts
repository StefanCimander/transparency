import { Routes } from '@angular/router';

import {
  AnalysisDetailsComponent,
  DashboardComponent,
  DependenciesDetailsComponent,
  DistributionDetailsComponent
} from './dashboard';

export const APP_ROUTES: Routes = [
  {
    path: 'analysis',
    component: AnalysisDetailsComponent,
  },
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
