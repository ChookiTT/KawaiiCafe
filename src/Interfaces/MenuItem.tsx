export interface MenuItem {
    itemId: number;
    itemName: string;
    itemPrice: number;
    imagePath?: string;
    categories: {
        categoryId: number;
        categoryName: string;
    }[];
    alergen:{
        alergenId: number;
        alergenName: string;
    }[]
}
