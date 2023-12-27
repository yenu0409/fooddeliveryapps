import {Dish} from "./dish.model";

    export interface Cart{
        cartId:number;
        quantity:number;
        mrpPrice:number;
        customer:any;
        dish:Dish;
    }