import { Component } from '@angular/core';
import { NgModel } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ManyToOne';

  isLoggedIn:number=0;//0No 1Yes
  userid:number=0;

}
