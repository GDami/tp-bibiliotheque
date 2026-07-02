import { Routes } from '@angular/router';
import { HomePage } from './page/home-page/home-page';
import { LoginPage } from './page/login-page/login-page';
import { authGuard } from './guard/auth-guard';
import { LivrePage } from './page/livre-page/livre-page';
import { AuteurPage } from './page/auteur-page/auteur-page';
import { CollectionPage } from './page/collection-page/collection-page';
import { EditeurPage } from './page/editeur-page/editeur-page';

export const routes: Routes = [
  {
    path: 'home',
    component: HomePage,
    canActivate: [ authGuard ]
  },

  {
    path: 'livre',
    component: LivrePage,
    canActivate: [ authGuard ]
  },

  {
    path: 'auteur',
    component: AuteurPage,
    canActivate: [ authGuard ]
  },

    {
    path: 'collection',
    component: CollectionPage,
    canActivate: [ authGuard ]
  },

    {
    path: 'editeur',
    component: EditeurPage,
    canActivate: [ authGuard ]
  },

  { path: 'login', component: LoginPage },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];
