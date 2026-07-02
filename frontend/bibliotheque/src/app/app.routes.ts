import { Routes } from '@angular/router';
import { LoginPage } from './page/login/login';
import { CollectionPage } from './page/collection-page/collection-page';

export const routes: Routes = [
  {
    path: 'collection',
    component: CollectionPage
  },
  {
    path: 'login',
    component: LoginPage
  }
];
