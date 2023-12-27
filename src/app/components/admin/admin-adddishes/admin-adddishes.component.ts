import { Component, OnInit } from '@angular/core';
import { FoodorderService } from '../../service/foodorder.service';
import { ActivatedRoute, Router } from '@angular/router';
import { take } from 'rxjs';
import { Dish } from '../../model/dish.model';

@Component({
  selector: 'app-admin-adddishes',
  templateUrl: './admin-adddishes.component.html',
  styleUrls: ['./admin-adddishes.component.scss']
})
export class AdminAdddishesComponent implements OnInit {

  dishname:string='';
  image:string='';
  description: string='';
  mrpPrice:number=0;
  dishId:any;
  isEdit:boolean=false;
  getCategoryList: any[]=[];
  category: number=0;

  constructor(
  private fService: FoodorderService,
  private router:Router,
  private activeRouter:ActivatedRoute
  ){
    this.activeRouter.queryParams.subscribe((params:any) =>{
      if(params?.id){
        this.isEdit=true;
        this.fService.getDishById(params?.id).pipe(take(1)).subscribe((res:any) =>{
          if(!!res && res?.dishId){

            const dish :Dish=res;
            console.log('>>>>', dish);
            this.dishname=dish?.dishname;
            this.image=dish?.image;
            this.mrpPrice=dish?.mrpPrice;
            this.description=dish?.description;
            this.dishId=res?.dishId ;
            const categoryName = this.getCategoryList.find((cate: any) => cate?.name.toString() === dish?.category)?.value;
            this.category = categoryName;
            console.log("****",this.category);
          }
          console.log(">>>>>>",res);
        });
      }
    })
  }

  ngOnInit(): void {
    this.fService.isAdminLoginPresent();
    this.getCategoryList=this.fService.getCategoryList();
  }

  onAddDish():void{
    if(this.dishname===''){
      alert("Dish name is required");
      return;
    }
    if(this.image===''){
      alert("Image should not be blank");
      return;
    }

    if(this.description===''){
      alert("Description should not be blank");
    }


    console.log("******MRP price",this.mrpPrice);
    if (this.mrpPrice === 0 || this.mrpPrice===null) {
      alert("MRP Price should not be zero/blank");
      return;
    }

    const body:any={
      dishname:this.dishname,
      image:this.image,
      mrpPrice:this.mrpPrice,
      description:this.description,
      category:this.category,
    }
    //console.log("&&&",this.category);
   // console.log("=======",body);
    //return ;
    if(this.isEdit){
      console.log("=======",body);
      this.fService.editDish(body, this.dishId).pipe(take(1)).subscribe((res:any) => {
        console.log("*****",res);
        if(res && res?.dishId) {
          alert("Dish updated sucessfully");
          this.router.navigate(["/admin/listdish"]);
        }
      },err =>{
        console.log("Error ",err);
        alert("Something going wrong!! pl try again");
      })
    }else{
      console.log("=======",body);
      this.fService.addDish(body).pipe(take(1)).subscribe((res: any) =>{
        console.log("*****", res);
        if (res && res?.dishId) {
          alert("Dish added sucessfully");
          this.router.navigate(["/admin/listdish"]);
        }
      },err => {
        console.log("Error  ", err);
        alert("Something going wrong!!pl try again");
      })
    }
  }
}
