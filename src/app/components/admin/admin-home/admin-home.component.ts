import { Component, OnInit } from '@angular/core';
import { FoodorderService } from '../../service/foodorder.service';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.scss']
})
export class AdminHomeComponent implements OnInit {
  userName:string='';

  constructor(
    private fService:FoodorderService
  ){
    if(this.fService.getAdminName() !==null){
      this.userName=this.fService.getAdminName();
    }
    this.fService.isAdminLoginPresent();
  }
  ngOnInit(): void {
    
  }

}
