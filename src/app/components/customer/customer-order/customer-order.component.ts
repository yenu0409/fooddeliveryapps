import { Component, OnInit } from '@angular/core';
import { FoodorderService } from '../../service/foodorder.service';
import { Order } from '../../model/order.model';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { take } from 'rxjs';

@Component({
  selector: 'app-customer-order',
  templateUrl: './customer-order.component.html',
  styleUrls: ['./customer-order.component.scss']
})
export class CustomerOrderComponent implements OnInit {
  orderList: Order[]=[];
  
  constructor(
    private fService: FoodorderService,
    private router: Router,
    private datePipe : DatePipe
  ) { 
    this.fService.isCustomerLoginPresent();
  }

  ngOnInit(): void {
    this.getOrderList();
  }
  getOrderList():void{
    this.fService.orderList(this.fService.getCustomerAuthorization()).pipe(take(1)).subscribe(
      (res: any) => {
        console.log("************",res);
        if(!!res && Array.isArray(res)){
          this.orderList=res;
        }
        
      }, err => {
        console.log("Error");
      }
    )
  }
  getDate(d:string|undefined):any{
    //return  !!d ? this.datePipe.transform(new Date(d),"" )?.toString(): "";
    //return this.datePipe.transform(d,"").toString();
    let ans :any;
    console.log("DDDDDD",d);
    if(!!d && d!== null){
      ans=this.datePipe.transform(d,"shortDate")||null;
      console.log("@@@@@@@@",ans);
    }
    return ans;
  }

  deleteOrder(order: Order): void {
    this.fService.deleteOrder(order?.orderId).pipe(take(1)).subscribe(
      (res: any) => {
        alert('Order deleted successfully');
        this.getOrderList();
      },
      (err: any) => {
        console.error(err);
        alert('Failed to delete order');
      }
    );
  }
  
  addPayment(order: Order): void {
    this.router.navigate([`/customer/payment/${order?.orderId}/${order?.totalPrice}`])
  }

}