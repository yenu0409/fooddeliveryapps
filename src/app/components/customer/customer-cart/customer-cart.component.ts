import { Component, OnInit } from '@angular/core';
import { FoodorderService } from '../../service/foodorder.service';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { forkJoin, take } from 'rxjs';
import { Cart } from '../../model/cart.model';
import * as _ from "lodash";

@Component({
  selector: 'app-customer-cart',
  templateUrl: './customer-cart.component.html',
  styleUrls: ['./customer-cart.component.scss']
})
export class CustomerCartComponent implements OnInit {
  cartList: Cart[] = [];
  cartListBackup: Cart[] = [];
  grandTotal: number = 0;
  customer: any = {};
  


  constructor(
    private fService: FoodorderService,
    private router: Router,
    private datePipe: DatePipe
  ) {
    this.fService.isCustomerLoginPresent();
    this.getCartList();
    this.getCustomerDetail();
  }

  ngOnInit(): void {
  }
  getCartList(): void {
    this.fService.cartList().pipe(take(1)).subscribe(
      (res: any) => {
        console.log("********", res);
        if (!!res && Array.isArray(res)) {
          const customerFilter = res.filter((item: Cart)=> item?.customer?.customerId === parseInt(this.fService.getCustomerAuthorization()));
          console.log("customer filter::::::",customerFilter);
          this.cartList = customerFilter;
          this.cartListBackup =  _.cloneDeep(customerFilter);
          if (this.cartList.length > 0) {
            this.cartList.map((item: Cart) => {
              this.grandTotal += (item?.mrpPrice * item?.quantity);
            })
          }
        }
      }, _err => {
        console.log("error");
      }

    );
  }
  getTotal(quantity: number = 0, mrpPrice: number = 0): number {
    return quantity * mrpPrice;
  }
  placeOrder(): void {
    const req:any[]=[];
    this.cartList.map((item: Cart) => {
      const body: any = {
        mrpPrice: item?.mrpPrice,
        quantity: item?.quantity,
        totalPrice: item?.mrpPrice * item?.quantity,
        orderStatus: "success",
        paymentStatus: "success",
        orderedDate: this.datePipe.transform(new Date(), 'yyyy-MM-dd'),
        customer: this.customer,
        cart: item,
        dishname: item?.dish?.dishname,
        image: item?.dish?.image
      };
     
      console.log("add to order", body);
      req.push(this.fService.placeOrder(this.customer?.customerId, item?.cartId, body));
    
    });

     forkJoin(req).pipe(take(1)).subscribe(
        (res: any) => {
          console.log("PLaceorder$$$$$$$$",res);
          alert("Place order Sucessfully");
          this.router.navigate(["/customer/order"])

        }, _err => {
          console.log("Error");
        });


  }

  getCustomerDetail(): void {
    const cid = this.fService.getCustomerAuthorization();
    this.fService.getCustomerById(cid).pipe(take(1)).subscribe(
      (res: any) => {
        console.log("Customer*****", res);
        if (!!res && res?.customerId) {
          this.customer = res;
        }
      }, _err => {
        console.log("Err");
      }
    )
  }

  deleteCart(cart:Cart, showAlert: boolean = true):void{
    this.fService.deleteCart(cart?.cartId).pipe(take(1)).subscribe(
      (res: any) => {
        if (showAlert) {
          alert("Dish deleted sucessfully");
        }
       
        this.getCartList();
      }, _err => {
        console.log("Err");
      }
    )
  }

  onIncreaseQunatity(cart: Cart): void {
    const index = this.cartList.findIndex((item: Cart) => item.cartId === cart?.cartId);
    // const bac = Object.assign(this.cartListBackup);
    const qty = cart.quantity + 1;
    this.cartList[index].quantity = qty;
    this.updateGrantTotal();
    return ;
  }

  onDecreaseQunatity(cart: Cart): void {
    const index = this.cartList.findIndex((item: Cart) => item.cartId === cart?.cartId);
    const qty = cart.quantity - 1;
    if (qty === 0) {
      this.deleteCart(cart, false);
    }
    this.cartList[index].quantity = qty;
    this.updateGrantTotal();
  }

  updateGrantTotal(): void {
    let total = 0;
    this.cartList.map((item: Cart) => {
      total+= (item?.mrpPrice * item?.quantity);
     
    })
    this.grandTotal = total;
  }

}
