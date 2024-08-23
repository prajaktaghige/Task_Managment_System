import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  constructor(public http:HttpClient,public app:AppComponent)
  {

  }

  username:string='abc';
  password:string='abc';
  login()
  {
    let url='http://localhost:8081/login'+this.username;
    this.http.post(url,this.password).subscribe((data:any)=>
    {
      if(data>0){
        this.app.isLoggedIn=1;
        this.app.userid=data;
      }
      else if(data==-1){
        window.alert('Exception on server');
      }
      else if(data==-2){
        window.alert('username wrong');
      }
      else if(data==-3){
        window.alert('multiple accounts with same username');
      }
      else if(data==-4){
        window.alert('wrong password');
      }
      else{
        window.alert('something is wrong');
      }
    });
  }

}