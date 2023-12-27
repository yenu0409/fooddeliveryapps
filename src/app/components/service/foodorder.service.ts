import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FoodorderService {
  url: string='http://localhost:8080';

  category:any =[{
    name: "STARTERS", value:0,
  },
  {
    name: "VEG", value:1,
  },
  {
    name: "NONVEG", value:2,
  },
  {
    name: "DESERTS", value:3,
  },
  {
    name: "BEVERAGES", value:4,
  }
];


  constructor(
    private http: HttpClient,
    private router:Router
  ) { }

  /*Customer Registration*/

  signUp(body: any):Observable<any>{
    return this.http.post(this.url + "/api/customers/register",body);
  }

  //Cutomer login
  customerSignIn(body: any):Observable<any>{
    return this.http.post(this.url +"/api/customers/login",body);
  }

  //After logged in Storing customer id into token
  storeCustomerAuthorization(token:string): void {
    localStorage.setItem("token", token);
  }

  getCustomerAuthorization():any{
    const token=localStorage.getItem("token");
    return token;
  }

  storeCustomerUserName(name:string):void{
    localStorage.setItem("userName",name);
  }

  getCustomerName():any{
    const name=localStorage.getItem("userName");
    return name;
  }

  customerLogout():void{
    localStorage.clear();
    this.router.navigate(['']);
  }

  //Admin Login
  adminSignIn(body: any):Observable<any>{
    return this.http.post(this.url + "/api/admin/login",body);
  }

  storeAdminAuthorization(token:string):void{
    localStorage.setItem("admin", token);
  }
  getAdminAuthorization():any{
    const token=localStorage.getItem("admin");
    return  token;
  }

  storeAdminUserName(name: string):void{
    localStorage.setItem("adminName", name);
  }

  getAdminName():any{
    const name=localStorage.getItem("adminName");
    return name;
  }

  admingLogout():void{
    localStorage.clear();
    this.router.navigate(['/']);
  }

  addDish(body: any):Observable<any>{
    return this.http.post(this.url + "/api/dishes/adddishes",body);
  }

  getDishList():Observable<any>{
    return this.http.get(this.url + "/api/dishes")
  }

  deleteDish(id:any):Observable<any>{
    return this.http.delete(`${this.url}/api/dishes/${id}`);
  }

  //delete cart
  deleteOrder(id:any):Observable<any>{
    return this.http.delete(`${this.url}/api/orders/${id}`);
  }

  getDishById(id:any):Observable<any>{
    return this.http.get(this.url + "/api/dishes/dishes/"+id);
  }

  editDish(body: any, id:any):Observable<any>{
    return this.http.put(this.url + "/api/dishes/"+id, body);
  }

  addToCart(body: any, did:any, cid:any):Observable<any>{
    return this.http.post(this.url + "/api/cart/"+cid+"/"+did, body);
  }

  getCustomerById(id:any):Observable<any>{
    return this.http.get(this.url + "/api/customers/customer/"+id);
  }

  cartList():Observable<any>{
    return  this.http.get(this.url + "/api/cart/list");
  }

  placeOrder(cid:any, cartid:any, body:any):Observable<any>{
    return this.http.post(this.url + "/api/orders/"+cid+"/"+cartid, body);
  }

  deleteCart(id :any):Observable<any> {
      return this.http.delete(`${this.url}/api/cart/${id}`);
  }

  orderList(id:any):Observable<any>{
    return this.http.get(this.url+"/api/orders/"+id);
  }

  getCategoryList(): any {
    return this.category;
  }

  addPayment(body:any,orderid:any,cid:any):Observable<any> {
    return this.http.post(this.url + "/api/payements/"+orderid+"/"+cid, body);
  }

  isCustomerLoginPresent(): void {
    if (this.getCustomerAuthorization() === null) {
      this.router.navigate(['/customer-login']);
    }
  }

  isAdminLoginPresent(): void {
    if (this.getAdminAuthorization() === null) {
      this.router.navigate(['/admin-login']);
    }
  }

  forgotPassword(body: any):Observable<any> {
    return this.http.post(this.url + "/api/customers/forgotpassword", body);
  }

  updateCustomerInformation(body: any):Observable<any> {
    return this.http.put(this.url + "/api/customers/customer/"+body?.customerId, body);
  }

  changePassword(cid: any,password:any):Observable<any> {
    return this.http.post(this.url + "/api/customers/"+cid+"/"+password,{});
  }

  getDishByCategory(cid: any, offset: any, limit: any):Observable<any>{
    return this.http.get(this.url+"/api/dishes/" + cid + "/"+ offset + "/" + limit);
  }
  
  getAllDishes(offset: any, limit: any):Observable<any>{
    return this.http.get(this.url+"/api/dishes/" + offset + "/" + limit);
  }
}
