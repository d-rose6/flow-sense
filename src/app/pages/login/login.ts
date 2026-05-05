import { Component } from '@angular/core';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [NgIf],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
  isLogin: boolean = true;

  toggleMode(): void {
    this.isLogin = !this.isLogin;
  }
}