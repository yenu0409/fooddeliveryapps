import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AboutusComponent } from './components/aboutus/aboutus.component';
import { AppHeaderComponent } from './components/app-header/app-header.component';
import { ContactusComponent } from './components/contactus/contactus.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { PagingComponent } from './components/paging/paging.component';
import { AdminLoginPageComponent } from './components/admin/admin-login-page/admin-login-page.component';
import { AdminAdddishesComponent } from './components/admin/admin-adddishes/admin-adddishes.component';
import { AdminHeaderComponent } from './components/admin/admin-header/admin-header.component';
import { AdminHomeComponent } from './components/admin/admin-home/admin-home.component';
import { DishesListComponent } from './components/admin/dishes-list/dishes-list.component';
import { CustomerCartComponent } from './components/customer/customer-cart/customer-cart.component';
import { CustomerHeaderComponent } from './components/customer/customer-header/customer-header.component';
import { CustomerHomeComponent } from './components/customer/customer-home/customer-home.component';
import { CustomerOrderComponent } from './components/customer/customer-order/customer-order.component';
import { CustomerPaymentComponent } from './components/customer/customer-payment/customer-payment.component';
import { CustomerSignupComponent } from './components/customer/customer-signup/customer-signup.component';
import { DatePipe } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { CustomerLoginPageComponent } from './components/customer/customer-login-page/customer-login-page.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    AboutusComponent,
    AppHeaderComponent,
    ContactusComponent,
    ForgotPasswordComponent,
    HomePageComponent,
    PagingComponent,
    AdminLoginPageComponent,
    AdminAdddishesComponent,
    AdminHeaderComponent,
    AdminHomeComponent,
    DishesListComponent,
    CustomerCartComponent,
    CustomerHeaderComponent,
    CustomerHomeComponent,
    CustomerHomeComponent,
    CustomerOrderComponent,
    CustomerPaymentComponent,
    CustomerSignupComponent,
    CustomerLoginPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatMenuModule,
    MatIconModule,
    MatSnackBarModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [
    DatePipe
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
