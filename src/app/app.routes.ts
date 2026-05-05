import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { QuemSomos } from './pages/quem-somos/quem-somos';
import { Login } from './pages/login/login';
import { Historico } from './pages/historico/historico';

export const routes: Routes = [
  {
    path: '',
    component: Home
  },
  {
    path: 'quem-somos',
    component: QuemSomos
  },
  {
    path: 'login',
    component: Login
  },
  {
  path: 'historico',
  component: Historico
  }
];