import { Component, OnInit } from '@angular/core';
import { FoodorderService } from '../../service/foodorder.service';
import { Router } from '@angular/router';
import { take } from 'rxjs';

@Component({
  selector: 'app-admin-login-page',
  templateUrl: './admin-login-page.component.html',
  styleUrls: ['./admin-login-page.component.scss']
})
export class AdminLoginPageComponent implements OnInit {
  email: string = "";
  password: string = "";

  constructor(
    private router:Router,
    private fservice:FoodorderService
  ) { }

  ngOnInit(): void {
    
  }

  signIn(): void {
    const pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/; 
    if (!pattern.test(this.email)) {
      alert("Email is not valid.");
      return;
    }
    if (this.password === '') {
      alert("Password should not be blank");
      return;
    }
    //alert("sucess")
    const body = {
      "adminEmailId": this.email,
      "adminPassword": this.password
    }
    
    this.fservice.adminSignIn(body).pipe(take(1)).subscribe((res :any) => {
      console.log("*****",res);
      if(res && res?.adminId){
        let userName = '';
        if (res?.firstName) {
          userName+=res?.firstName;
        }
        if (res?.lastName){
          userName+=' ' + res?.lastName;
        }
        this.fservice.storeAdminUserName(userName);
        this.fservice.storeAdminAuthorization(res?.adminId);
        this.router.navigate(['/admin/home']);
       
      }
    }, err =>{
      console.log("Error  ",err);
      alert("Something going wrong in login!!pl try again");
    })
  }


}

  