import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FoodorderService } from '../../service/foodorder.service';
import { take } from 'rxjs';


@Component({
  selector: 'app-customer-login-page',
  templateUrl: './customer-login-page.component.html',
  styleUrls: ['./customer-login-page.component.scss']
})
export class CustomerLoginPageComponent implements OnInit {

  email: string = "";
  password: string = "";
  customerLoginForm:any = new FormGroup({});

  constructor(
    private router: Router,
    private fservice: FoodorderService,
    private fb: FormBuilder
  ) {
    const pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
   
    this.customerLoginForm = this.fb.group({
      email: ['', [Validators.required, Validators.pattern(pattern)]],
      password: [null, Validators.compose([Validators.required, Validators.minLength(8)])]
    });
  }

  ngOnInit(): void {

  }

  
  signIn():void{
    const body={
      "emailID": this.customerLoginForm.controls['email'].value,
      "password": this.customerLoginForm.controls['password'].value
    }
console.log("======>");
this.fservice.customerSignIn(body).pipe(take(1)).subscribe((res:any)=>{
  console.log("*****",res);
  if(res && res?.customerId){
    this.fservice.storeCustomerAuthorization(res?.customerId);
    let userName='';
    if(res?.firstName){
      userName+=res?.firstName;
    }
    if(res?.lastName){
      userName+=' '+res?.lastName;
    }
    this.fservice.storeCustomerUserName(userName);
    this.router.navigate(['/customer/home']);
  }
  
}, err =>{
  console.log("Error ",err);
  alert("Something going wrong in login! please try again");
})

}
routeToNewUser():void{
  this.router.navigate(["/customer-register"]);
}

routeToForgotPassword():void{
  this.router.navigate(["/forgot-password"]);
}

}