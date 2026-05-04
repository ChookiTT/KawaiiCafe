import type {User} from "./User.tsx";
import type {MenuItem} from "./MenuItem.tsx";


export interface OrderItem{
    item:MenuItem
    qty: number;
}
export interface OrderingInterface {

    orderNote: string;
    items: OrderItem[];
    orderPrice: number;
    orderDate: string;
    user: User;
}
