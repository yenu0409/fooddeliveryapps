import { Component, OnInit } from '@angular/core';
import { Dish } from '../../model/dish.model';
import { FoodorderService } from '../../service/foodorder.service';
import { Router } from '@angular/router';
import { take } from 'rxjs';

@Component({
  selector: 'app-dishes-list',
  templateUrl: './dishes-list.component.html',
  styleUrls: ['./dishes-list.component.scss']
})
export class DishesListComponent implements OnInit{

  dishList:Array<Dish> =[];
  getCategoryList: any[]=[];
  category:any=100;
  allDishList:Array<Dish>=[];
  offset:number=0;
  pageSize:number=5;
  totalItem:number=1;

  constructor(
    private fService:FoodorderService,
    private router:Router
  ){
    this.fService.isAdminLoginPresent();
    this.getDishList(true);
  }

  ngOnInit():void{
    this.getCategoryList=this.fService.getCategoryList();
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
      }
    },(err:any)=>{
      console.log("Error");
    });
  }

  delDish(dish: Dish): void{
    this.fService.deleteDish(dish?.dishId).pipe(take(1)).subscribe(
      (res:any)=>{
        alert("Dish deleted sucessfully");
        this.getDishList(this.category===100 || this.category==="100");
      },err =>{
        console.log("Error");
        
      }
    )
  }

  editDish(dish: Dish): void {
    this.router.navigate(['/admin/adddish'], {
      queryParams: {
        id: dish?.dishId
      }
    });
  }

  getDishByCategory(): void {
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
