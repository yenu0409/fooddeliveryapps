import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { FoodorderService } from '../../service/foodorder.service';
import { take } from 'rxjs';


@Component({
  selector: 'app-customer-signup',
  templateUrl: './customer-signup.component.html',
  styleUrls: ['./customer-signup.component.scss']
})
export class CustomerSignupComponent implements OnInit{

  firstName: string = "";
  lastName: string = "";
  email: string = "";
  password: string = "";
  phone: string = "";
  zipcode: string = "";
  address:string="";

  constructor(
    private router:Router,
    private fservice:FoodorderService
  ){}

  ngOnInit(): void {
    
  }

  signup(): void {
    if (this.firstName === '' || this.firstName.length < 3) {
      alert('FirstName must contain atleast 3 characters');
      return;
    }
    if (this.lastName === '' || this.lastName.length < 3) {
      alert('LastName must contain atleast 3 characters');
      return;
    }

    if (this.phone === '' || this.phone.length < 10 || this.phone.length > 10) {
      alert('Phone must contain atleast 10 characters');
      return;
    }
    
    if (this.zipcode === '' || this.zipcode.length < 6) {
      alert('Zipcode must contain atleast 6 characters');
      return;
    }

    if(this.address==='' || this.address.length < 3){
      alert('Addres must contain atleat 3 characters')
    }
  
    const body: any = {
      firstName : this.firstName,
      lastName : this.lastName,
      phoneNumber : this.phone,
      zipCode :this.zipcode,
      emailID :this.email,
      password:this.password,
      address:this.address

    }
    console.log("=======>",body);
    this.fservice.signUp(body).pipe(take(1)).subscribe((res :any) => {
      console.log("*****",res);
      if(res && res?.customerId){
        alert("Registration sucessful");
        this.router.navigate(["/customer-login"]);
      }
    }, err =>{
      console.log("Error  ",err);
      alert("Something going wrong!!pl try again");
    })

  }
}



  