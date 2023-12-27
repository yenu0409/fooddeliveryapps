import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import { ContactusComponent } from './components/contactus/contactus.component';
import { AboutusComponent } from './components/aboutus/aboutus.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { AdminLoginPageComponent } from './components/admin/admin-login-page/admin-login-page.component';
import { AdminHomeComponent } from './components/admin/admin-home/admin-home.component';
import { AdminAdddishesComponent } from './components/admin/admin-adddishes/admin-adddishes.component';
import { DishesListComponent } from './components/admin/dishes-list/dishes-list.component';
import { CustomerPaymentComponent } from './components/customer/customer-payment/customer-payment.component';
import { CustomerOrderComponent } from './components/customer/customer-order/customer-order.component';
import { CustomerHomeComponent } from './components/customer/customer-home/customer-home.component';
import { CustomerCartComponent } from './components/customer/customer-cart/customer-cart.component';
import { CustomerSignupComponent } from './components/customer/customer-signup/customer-signup.component';
import { CustomerLoginPageComponent } from './components/customer/customer-login-page/customer-login-page.component';

const routes: Routes = [
  {path:'',component:HomePageComponent},
  {path:'contact-us',component:ContactusComponent},
  {path:'about-us',component:AboutusComponent},
  {path: 'forgot-password', component: ForgotPasswordComponent},
  {path:'customer-register',component: CustomerSignupComponent},
  {path:'customer-login',component:CustomerLoginPageComponent},
  {path:'admin-login',component:AdminLoginPageComponent},
  {path:'customer',children:[
    {path:'home',component:CustomerHomeComponent},
    {path:'cart',component:CustomerCartComponent},
    {path:'order',component:CustomerOrderComponent},
    {path:'payment/:orderId/:totalPrice',component:CustomerPaymentComponent}
  ]},
  {path:'admin',children:[
    {path:'home',component:AdminHomeComponent},
    {path: 'adddish', component:AdminAdddishesComponent },
    {path:'listdish',component:DishesListComponent}
  ]}

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
