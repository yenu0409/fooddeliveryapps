import { Component, OnInit } from '@angular/core';
import { Dish } from '../../model/dish.model';
import { FoodorderService } from '../../service/foodorder.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { take } from 'rxjs';

@Component({
  selector: 'app-customer-home',
  templateUrl: './customer-home.component.html',
  styleUrls: ['./customer-home.component.scss']
})
export class CustomerHomeComponent implements OnInit {

  dishList: Array<Dish> = [];
  getCategoryList: any[] = [];
  customer: any = {};
  category: any = 100;
  allDishList: Array<Dish> = [];
  offset: number = 0;
  pageSize: number = 5;
  totalItem: number = 1;

  constructor(
    private fService: FoodorderService,
    private router: Router,
    private snakbar: MatSnackBar
  ) {
    this.fService.isCustomerLoginPresent();
    this.getDishList(true);
    this.getCustomerDetail();
  }


  ngOnInit(): void {
    this.getCategoryList = this.fService.getCategoryList();
  }

  getCustomerDetail(): void {
    const cid = this.fService.getCustomerAuthorization();
    this.fService.getCustomerById(cid).pipe(take(1)).subscribe(
      (res: any) => {
        console.log("Customer******", res);
        if (!!res && res?.customerId) {
          this.customer = res;
        }
      }, err => {
        console.log("Error");

      })
  }

  getDishList(isAllDish:boolean=false):void{
    let dish: any =this.fService.getAllDishes(this.offset - 1 < 0 ? 0 : this.offset - 1, this.pageSize);
    if(!isAllDish){
      dish = this.fService.getDishByCategory(this.category, this.offset - 1 < 0 ? 0 : this.offset - 1, this.pageSize);
    }
    dish.pipe(take(1)).subscribe((res: any)=>{
      if (res && res?.dishes && Array.isArray(res?.dishes)) {
        this.dishList=res?.dishes;
        this.allDishList=res?.dishes;
        this.totalItem=res?.totalDishes;
        console.log('>>>>>>', res)
      }
    },(err:any)=>{
      console.log("Error");
    });
  }

  addToCart(dish: Dish): void {
  const element: any = document.getElementById(dish?.dishId.toString());
  let qty:any= element!==null ? element.value : 0; 
  if(qty ===""){
    element.value=0;
    qty=0;
  }
    if (qty === 0 || qty === "0") {
      alert("Qunatity should not be zero");
      return ;
    }
    

    const body: any = {
      quantity: qty,
      mrpPrice: dish?.mrpPrice,
      dish: dish,
      customer: this.customer
    };
    console.log("add to cart", body);
    this.fService.addToCart(body, dish?.dishId, this.customer?.customerId).pipe(take(1)).subscribe(
      (res: any) => {
        console.log(res);
        if (!!res && res?.cartId) {
        alert("Item added sucessfully");
          this.getDishList(true);
        }
      }, err => {
        console.log("Error");
      })
  }

  getDishByCategory():void{
    this.offset = 0;
    this.totalItem = 1;
    if (this.category === "100") {
      this.getDishList(true);
    } else {
      this.getDishList(false);
    }
  }

  onNextPageClick(pageOffSet: any): void {
    this.offset = pageOffSet;
    this.getDishList(this.category === 100 || this.category === "100");
  }

  onPreviousPageClick(pageOffSet: any): void {
    this.offset -= 1;
    this.getDishList(this.category === 100 || this.category === "100");
  }

  onFirstPageClick(pageOffSet: any): void {
    this.offset = 0;
    this.getDishList(this.category === 100 || this.category === "100");
  }

  onLastPageClick(pageOffSet: any): void {
    const lastPage = Math.ceil(this.totalItem / this.pageSize);
    this.offset = lastPage;
    this.getDishList(this.category === 100 || this.category === "100");
  }
}
